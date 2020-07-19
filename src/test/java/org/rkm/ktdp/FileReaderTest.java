package org.rkm.ktdp;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rkm.ktdp.templates.DateTemplate;
import org.rkm.ktdp.templates.DatetimeTemplate;
import org.rkm.ktdp.templates.DoubleTemplate;
import org.rkm.ktdp.templates.FromSourceTemplate;
import org.rkm.ktdp.templates.IntegerTemplate;
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

    @Test
    public void shouldReturnTemplateConfigurationFromJsonFile() throws IOException {
        String configFilePath = getClass().getClassLoader().getResource("TemplateConfiguration.json").getFile();
        List<BaseTemplate> templates = new FileReader(null)
                .templateConfigurations(configFilePath);

        List<BaseTemplate> expectedTemplates = Arrays.asList(
                new DateTemplate("date", false),
                new DatetimeTemplate("datetime", false),
                new DoubleTemplate("double", false),
                new FromSourceTemplate("source", false),
                new IntegerTemplate("integer", false),
                new TimeTemplate("time", false),
                new UUIDTemplate("uuid", true)
        );

        assertThat(templates)
                .containsAll(expectedTemplates);
    }

    @Test
    public void shouldLogErrorWhileReadingTemplateConfigurationFromJsonFile() {
        String configFilePath = getClass().getClassLoader().getResource("InvalidTemplateConfiguration.json").getFile();
        Logger mockedLogger = Mockito.mock(Logger.class);

        assertThrows(MismatchedInputException.class,
                () -> new FileReader(mockedLogger).templateConfigurations(configFilePath));

        verify(mockedLogger)
                .error(eq("Error while reading template configuration."), any(MismatchedInputException.class));
    }
}