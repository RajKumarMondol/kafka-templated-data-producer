package org.rkm.ktdp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rkm.ktdp.configs.ApplicationSettings;
import org.rkm.ktdp.io.TemplateFileReader;
import org.rkm.ktdp.templates.specification.BaseTemplate;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class MessageGeneratorTest {
    @Test
    public void shouldGenerateMessagesFromFile() throws IOException {
        ApplicationSettings mockedApplicationSettings = Mockito.mock(ApplicationSettings.class);
        TemplateFileReader mockedTemplateFileReader = Mockito.mock(TemplateFileReader.class);
        BaseTemplate template1 = Mockito.mock(BaseTemplate.class);
        BaseTemplate template2 = Mockito.mock(BaseTemplate.class);

        when(mockedApplicationSettings.getMessageTemplateFile())
                .thenReturn("template.file");
        when(mockedApplicationSettings.getTemplateConfigFile())
                .thenReturn("template.config");
        when(mockedTemplateFileReader.messageFile("template.file"))
                .thenReturn(new String[]{"<template1>", "<template1>|<template2>"});
        when(mockedTemplateFileReader.configurationFile("template.config"))
                .thenReturn(Arrays.asList(template1, template2));
        when(template1.getName())
                .thenReturn("template1");
        when(template2.getName())
                .thenReturn("template2");
        when(template1.generate())
                .thenReturn("value00")
                .thenReturn("value01")
                .thenReturn("value02")
                .thenReturn("value03");
        when(template2.generate())
                .thenReturn("value10")
                .thenReturn("value11");

        MessageGenerator messageGenerator = new MessageGenerator();
        messageGenerator.initialize(mockedApplicationSettings, mockedTemplateFileReader);

        assertThat(messageGenerator.nextKeyAndMessage())
                .containsExactly("0", "value00");
        assertThat(messageGenerator.nextKeyAndMessage())
                .containsExactly("1", "value01|value10");
        assertThat(messageGenerator.nextKeyAndMessage())
                .containsExactly("2", "value02");
        assertThat(messageGenerator.nextKeyAndMessage())
                .containsExactly("3", "value03|value11");
    }

    @Test
    public void shouldThrowExceptionIfOccurred() throws IOException {
        ApplicationSettings mockedApplicationSettings = Mockito.mock(ApplicationSettings.class);
        TemplateFileReader mockedTemplateFileReader = Mockito.mock(TemplateFileReader.class);

        when(mockedApplicationSettings.getMessageTemplateFile())
                .thenReturn("template.file");
        when(mockedTemplateFileReader.messageFile("template.file"))
                .thenThrow(new RuntimeException("test"));

        MessageGenerator messageGenerator = new MessageGenerator();
        assertThrows(RuntimeException.class, () -> messageGenerator.initialize(mockedApplicationSettings, mockedTemplateFileReader));
    }
}