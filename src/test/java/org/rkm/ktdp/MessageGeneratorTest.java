package org.rkm.ktdp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rkm.ktdp.configs.ApplicationSettings;
import org.rkm.ktdp.io.FileReader;
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
        FileReader mockedFileReader = Mockito.mock(FileReader.class);
        BaseTemplate template1 = Mockito.mock(BaseTemplate.class);
        BaseTemplate template2 = Mockito.mock(BaseTemplate.class);

        when(mockedApplicationSettings.getMessageTemplateFile())
                .thenReturn("template.file");
        when(mockedApplicationSettings.getTemplateConfigFile())
                .thenReturn("template.config");
        when(mockedFileReader.messageTemplateFile("template.file"))
                .thenReturn(new String[]{"<template1>", "<template1>|<template2>"});
        when(mockedFileReader.templateConfigurations("template.config"))
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

        MessageGenerator messageGenerator = new MessageGenerator(mockedApplicationSettings, mockedFileReader);

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
        FileReader mockedFileReader = Mockito.mock(FileReader.class);

        when(mockedApplicationSettings.getMessageTemplateFile())
                .thenReturn("template.file");
        when(mockedFileReader.messageTemplateFile("template.file"))
                .thenThrow(new RuntimeException("test"));

        assertThrows(RuntimeException.class, () -> new MessageGenerator(mockedApplicationSettings, mockedFileReader));
    }
}