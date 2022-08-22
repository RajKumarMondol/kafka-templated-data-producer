package org.rkm.ktdp.io;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rkm.ktdp.datasource.FromFile;
import org.rkm.ktdp.datasource.FromValues;
import org.rkm.ktdp.generators.characters.UUIDGenerator;
import org.rkm.ktdp.templates.*;
import org.rkm.ktdp.templates.specification.BaseTemplate;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

public class TemplateFileReaderTest {

    Logger mockedLogger = Mockito.mock(Logger.class);

    @Test
    public void shouldReturnTemplateConfigurationFromJsonFile() throws IOException {
        String configFilePath = getClass().getClassLoader().getResource("TemplateConfiguration.json").getFile();
        List<BaseTemplate> templates = new TemplateFileReader(mockedLogger)
                .configurationFile(configFilePath);

        List<BaseTemplate> expectedTemplates = Arrays.asList(
                new DateTemplate("dateRandom", false,
                        new org.rkm.ktdp.generators.date.Random("yyyy-MM-dd", "2022-08-24", "2022-09-24")),
                new DateTemplate("dateSequence", false,
                        new org.rkm.ktdp.generators.date.Sequence("yyyy-MM-dd", "2022-08-24", 5)),
                new DateTemplate("dateCurrent", false,
                        new org.rkm.ktdp.generators.date.Current("yyyy-MM-dd", "UTC")),

                new DatetimeTemplate("datetimeRandom", false,
                        new org.rkm.ktdp.generators.datetime.Random("yyyy-MM-dd'T'HH:mm:ss.SSSxx", "2022-08-24T10:00:00", "2022-09-24T18:00:00")),
                new DatetimeTemplate("datetimeSequence", false,
                        new org.rkm.ktdp.generators.datetime.Sequence("yyyy-MM-dd'T'HH:mm:ss.SSSxx")),
                new DatetimeTemplate("datetimeCurrent", false,
                        new org.rkm.ktdp.generators.datetime.Current("yyyy-MM-dd'T'HH:mm:ss.SSSxx", "UTC")),

                new DoubleTemplate("doubleRandom", false,
                        new org.rkm.ktdp.generators.number.Random("%5.2f", -10.0, 10.0)),
                new DoubleTemplate("doubleSequence", false,
                        new org.rkm.ktdp.generators.number.Sequence("%5.2f", 100.01, 0.01)),

                new FromSourceTemplate("sourceRandom", false,
                        new org.rkm.ktdp.generators.fromsource.Random(
                                new FromFile("filename.txt"))),
                new FromSourceTemplate("sourceSequence", false,
                        new org.rkm.ktdp.generators.fromsource.Sequence(
                                new FromValues(Arrays.asList("value1", "value2")))),

                new IntegerTemplate("integerRandom", false,
                        new org.rkm.ktdp.generators.wholenumber.Random("%05d", -100, 100)),
                new IntegerTemplate("integerSequence", false,
                        new org.rkm.ktdp.generators.wholenumber.Sequence("%05d", 100, 5)),

                new TimeTemplate("timeRandom", false,
                        new org.rkm.ktdp.generators.time.Random("HH:mm:ss", "10:00:00", "18:00:00")),
                new TimeTemplate("timeSequence", false,
                        new org.rkm.ktdp.generators.time.Sequence("HH:mm:ss", "10:00:00", 10)),
                new TimeTemplate("timeCurrent", false,
                        new org.rkm.ktdp.generators.time.Current("HH:mm:ss.SSSxx", "UTC")),

                new StringTemplate("stringRandom", false,
                        new org.rkm.ktdp.generators.characters.Random("[^0-9]*[12]?[0-9]{1,2}[^0-9]*")),

                new StringTemplate("uuid", true, new UUIDGenerator())
        );

        assertThat(templates)
                .containsAll(expectedTemplates);
    }

    @Test
    public void shouldLogErrorWhileReadingTemplateConfigurationFromJsonFile() {
        String configFilePath = getClass().getClassLoader().getResource("InvalidTemplateConfiguration.json").getFile();

        assertThrows(MismatchedInputException.class,
                () -> new TemplateFileReader(mockedLogger).configurationFile(configFilePath));

        verify(mockedLogger)
                .error(startsWith("Error while reading template configuration."), any(MismatchedInputException.class));
    }
}