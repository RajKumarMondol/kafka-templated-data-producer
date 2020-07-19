package org.rkm.ktdp;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rkm.ktdp.datasource.FromFile;
import org.rkm.ktdp.datasource.FromValues;
import org.rkm.ktdp.generators.UUIDGenerator;
import org.rkm.ktdp.templates.DateTemplate;
import org.rkm.ktdp.templates.DatetimeTemplate;
import org.rkm.ktdp.templates.DoubleTemplate;
import org.rkm.ktdp.templates.FromSourceTemplate;
import org.rkm.ktdp.templates.IntegerTemplate;
import org.rkm.ktdp.templates.StringTemplate;
import org.rkm.ktdp.templates.TimeTemplate;
import org.rkm.ktdp.templates.UUIDTemplate;
import org.rkm.ktdp.templates.specification.BaseTemplate;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class FileReaderTest {

    Logger mockedLogger = Mockito.mock(Logger.class);

    @Test
    public void shouldReturnTemplateConfigurationFromJsonFile() throws IOException {
        String configFilePath = getClass().getClassLoader().getResource("TemplateConfiguration.json").getFile();
        List<BaseTemplate> templates = new FileReader(mockedLogger)
                .templateConfigurations(configFilePath);

        List<BaseTemplate> expectedTemplates = Arrays.asList(
                new DateTemplate("dateRandom", false,
                        new org.rkm.ktdp.generators.date.Random("yyyy-MM-dd")),
                new DateTemplate("dateSequence", false,
                        new org.rkm.ktdp.generators.date.Sequence("yyyy-MM-dd")),

                new DatetimeTemplate("datetimeRandom", false,
                        new org.rkm.ktdp.generators.datetime.Random("yyyy-MM-dd'T'HH:mm:ss.SSSxx")),
                new DatetimeTemplate("datetimeSequence", false,
                        new org.rkm.ktdp.generators.datetime.Sequence("yyyy-MM-dd'T'HH:mm:ss.SSSxx")),

                new DoubleTemplate("doubleRandom", false,
                        new org.rkm.ktdp.generators.number.Random("000000.00")),
                new DoubleTemplate("doubleSequence", false,
                        new org.rkm.ktdp.generators.number.Sequence("000000.00")),

                new FromSourceTemplate("sourceRandom", false,
                        new org.rkm.ktdp.generators.fromsource.Random(new FromFile("filename.txt"))),
                new FromSourceTemplate("sourceSequence", false,
                        new org.rkm.ktdp.generators.fromsource.Sequence(new FromValues(Arrays.asList("value1",
                                "value2")))),

                new IntegerTemplate("integerRandom", false,
                        new org.rkm.ktdp.generators.wholenumber.Random("000000")),
                new IntegerTemplate("integerSequence", false,
                        new org.rkm.ktdp.generators.wholenumber.Sequence("000000")),

                new TimeTemplate("timeRandom", false,
                        new org.rkm.ktdp.generators.time.Random("HH:mm:ss.SSSxx")),
                new TimeTemplate("timeSequence", false,
                        new org.rkm.ktdp.generators.time.Sequence("HH:mm:ss.SSSxx")),

                new StringTemplate("stringRandom", false,
                        new org.rkm.ktdp.generators.characters.Random(), "\\w+"),
                new StringTemplate("stringSequence", false,
                        new org.rkm.ktdp.generators.characters.Sequence(), "\\d+"),

                new UUIDTemplate("uuid", true, new UUIDGenerator())
        );

        assertThat(templates)
                .containsAll(expectedTemplates);
    }

    @Test
    public void shouldLogErrorWhileReadingTemplateConfigurationFromJsonFile() {
        String configFilePath = getClass().getClassLoader().getResource("InvalidTemplateConfiguration.json").getFile();

        assertThrows(MismatchedInputException.class,
                () -> new FileReader(mockedLogger).templateConfigurations(configFilePath));

        verify(mockedLogger)
                .error(eq("Error while reading template configuration."), any(MismatchedInputException.class));
    }
}