package org.rkm.ktdp.io;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rkm.ktdp.datasource.FromFile;
import org.rkm.ktdp.datasource.FromValues;
import org.rkm.ktdp.templates.characters.RandomString;
import org.rkm.ktdp.templates.characters.UUIDString;
import org.rkm.ktdp.templates.date.CurrentDate;
import org.rkm.ktdp.templates.date.DateSequence;
import org.rkm.ktdp.templates.date.RandomDate;
import org.rkm.ktdp.templates.datetime.CurrentDateTime;
import org.rkm.ktdp.templates.datetime.DateTimeSequence;
import org.rkm.ktdp.templates.datetime.RandomDateTime;
import org.rkm.ktdp.templates.fromsource.RandomFromSource;
import org.rkm.ktdp.templates.fromsource.SequenceFromSource;
import org.rkm.ktdp.templates.number.DoubleSequence;
import org.rkm.ktdp.templates.number.RandomDouble;
import org.rkm.ktdp.templates.specification.BaseTemplate;
import org.rkm.ktdp.templates.time.CurrentTime;
import org.rkm.ktdp.templates.time.RandomTime;
import org.rkm.ktdp.templates.time.TimeSequence;
import org.rkm.ktdp.templates.wholenumber.IntegerSequence;
import org.rkm.ktdp.templates.wholenumber.RandomInteger;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.verify;

public class TemplateFileReaderTest {

    Logger mockedLogger = Mockito.mock(Logger.class);

    @Test
    public void shouldReturnTemplateConfigurationFromJsonFile() throws IOException {
        String configFilePath = getClass().getClassLoader().getResource("TemplateConfiguration.json").getFile();
        List<BaseTemplate> templates = new TemplateFileReader(mockedLogger)
                .configurationFile(configFilePath);

        List<BaseTemplate> expectedTemplates = Arrays.asList(
                new RandomDate("dateRandom", "yyyy-MM-dd", "2022-08-24", "2022-09-24"),
                new DateSequence("dateSequence", "yyyy-MM-dd", "2022-08-24", 5),
                new CurrentDate("dateCurrent", "yyyy-MM-dd", "UTC"),

                new RandomDateTime("datetimeRandom", "yyyy-MM-dd'T'HH:mm:ss.SSSxx", "UTC", "2022-08-24T10:00:00", "2022-09-24T18:00:00"),
                new DateTimeSequence("datetimeSequence", "yyyy-MM-dd'T'HH:mm:ss.SSSxx", "UTC"),
                new CurrentDateTime("datetimeCurrent", "yyyy-MM-dd'T'HH:mm:ss.SSSxx", "UTC"),

                new RandomDouble("doubleRandom", "%5.2f", -10.0, 10.0),
                new DoubleSequence("doubleSequence", "%5.2f", 100.01, 0.01),

                new RandomFromSource("sourceRandom", new FromFile("filename.txt")),
                new SequenceFromSource("sourceSequence", new FromValues(Arrays.asList("value1", "value2"))),

                new RandomInteger("integerRandom", "%05d", -100, 100),
                new IntegerSequence("integerSequence", "%05d", 100, 5),

                new RandomTime("timeRandom", "HH:mm:ss", "10:00:00", "18:00:00"),
                new TimeSequence("timeSequence", "HH:mm:ss", "10:00:00", 10),
                new CurrentTime("timeCurrent", "HH:mm:ss.SSSxx", "UTC"),

                new RandomString("stringRandom", "[^0-9]*[12]?[0-9]{1,2}[^0-9]*"),
                new UUIDString("uuid")
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