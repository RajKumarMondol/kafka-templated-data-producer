package org.rkm.ktdp.configs;

import org.apache.commons.cli.CommandLine;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ApplicationSettingsTest {

    private CommandLine mockedCommandLine = Mockito.mock(CommandLine.class);

    private ApplicationSettings buildSettings() {
        return new ApplicationSettings("topic-name",
                10,
                1,
                Instant.MIN,
                "message-template-file",
                "template-config-file");
    }

    @Test
    public void shouldBuildSettingsFromGivenParameters() {
        ApplicationSettings applicationSettings = buildSettings();

        assertThat(applicationSettings.getTopicName())
                .isEqualTo("topic-name");
        assertThat(applicationSettings.getMessageCount())
                .isEqualTo(10);
        assertThat(applicationSettings.getMessageInterval())
                .isEqualTo(1);
        assertThat(applicationSettings.getStartTime())
                .isEqualTo(Instant.MIN);
        assertThat(applicationSettings.getIsRealtime())
                .isFalse();
        assertThat(applicationSettings.getMessageTemplateFile())
                .isEqualTo("message-template-file");
        assertThat(applicationSettings.getTemplateConfigFile())
                .isEqualTo("template-config-file");

        assertThat(applicationSettings.toString())
                .isEqualTo("Kafka Templated Data Producer Settings :\n" +
                        "\ttopicName = topic-name,\n" +
                        "\tmessageCount = 10,\n" +
                        "\tmessageInterval = 1,\n" +
                        "\tstartTime = -1000000000-01-01T00:00:00Z,\n" +
                        "\tisRealtime = false,\n" +
                        "\tmessageTemplateFile = message-template-file,\n" +
                        "\ttemplateConfigFile = template-config-file");
    }

    @Test
    public void shouldThrowParsingErrorWhileSettingsFromCommandLine() {
        ApplicationSettings applicationSettings = buildSettings();

        when(mockedCommandLine.hasOption("topic-name"))
                .thenReturn(true);
        when(mockedCommandLine.getOptionValue("topic-name"))
                .thenReturn("topic-name1");

        applicationSettings.updateFrom(mockedCommandLine);
    }

    @Test
    public void shouldUpdateTopicNameInSettingsFromCommandLine() {
        ApplicationSettings applicationSettings = buildSettings();

        when(mockedCommandLine.hasOption("message-count"))
                .thenReturn(true);
        when(mockedCommandLine.getOptionValue("message-count"))
                .thenReturn("abc");

        assertThrows(NumberFormatException.class, () -> applicationSettings.updateFrom(mockedCommandLine));
    }

    @Test
    public void shouldUpdateMessageCountInSettingsFromCommandLine() {
        ApplicationSettings applicationSettings = buildSettings();

        when(mockedCommandLine.hasOption("message-count"))
                .thenReturn(true);
        when(mockedCommandLine.getOptionValue("message-count"))
                .thenReturn("100");

        applicationSettings.updateFrom(mockedCommandLine);

        assertThat(applicationSettings.getTopicName())
                .isEqualTo("topic-name");
        assertThat(applicationSettings.getMessageCount())
                .isEqualTo(100);
        assertThat(applicationSettings.getMessageInterval())
                .isEqualTo(1);
        assertThat(applicationSettings.getStartTime())
                .isEqualTo(Instant.MIN);
        assertThat(applicationSettings.getIsRealtime())
                .isFalse();
        assertThat(applicationSettings.getMessageTemplateFile())
                .isEqualTo("message-template-file");
        assertThat(applicationSettings.getTemplateConfigFile())
                .isEqualTo("template-config-file");
    }

    @Test
    public void shouldUpdateMessageIntervalInSettingsFromCommandLine() {
        ApplicationSettings applicationSettings = buildSettings();

        when(mockedCommandLine.hasOption("message-interval"))
                .thenReturn(true);
        when(mockedCommandLine.getOptionValue("message-interval"))
                .thenReturn("10");

        applicationSettings.updateFrom(mockedCommandLine);

        assertThat(applicationSettings.getTopicName())
                .isEqualTo("topic-name");
        assertThat(applicationSettings.getMessageCount())
                .isEqualTo(10);
        assertThat(applicationSettings.getMessageInterval())
                .isEqualTo(10);
        assertThat(applicationSettings.getStartTime())
                .isEqualTo(Instant.MIN);
        assertThat(applicationSettings.getIsRealtime())
                .isFalse();
        assertThat(applicationSettings.getMessageTemplateFile())
                .isEqualTo("message-template-file");
        assertThat(applicationSettings.getTemplateConfigFile())
                .isEqualTo("template-config-file");
    }

    @Test
    public void shouldUpdateStartTimeInSettingsFromCommandLine() {
        ApplicationSettings applicationSettings = buildSettings();

        when(mockedCommandLine.hasOption("start-time"))
                .thenReturn(true);
        when(mockedCommandLine.getOptionValue("start-time"))
                .thenReturn(Instant.MAX.toString());

        applicationSettings.updateFrom(mockedCommandLine);

        assertThat(applicationSettings.getTopicName())
                .isEqualTo("topic-name");
        assertThat(applicationSettings.getMessageCount())
                .isEqualTo(10);
        assertThat(applicationSettings.getMessageInterval())
                .isEqualTo(1);
        assertThat(applicationSettings.getStartTime())
                .isEqualTo(Instant.MAX);
        assertThat(applicationSettings.getIsRealtime())
                .isFalse();
        assertThat(applicationSettings.getMessageTemplateFile())
                .isEqualTo("message-template-file");
        assertThat(applicationSettings.getTemplateConfigFile())
                .isEqualTo("template-config-file");
    }

    @Test
    public void shouldUpdateIsRealTimeInSettingsFromCommandLine() {
        ApplicationSettings applicationSettings = buildSettings();

        when(mockedCommandLine.hasOption("realtime"))
                .thenReturn(true);

        applicationSettings.updateFrom(mockedCommandLine);

        assertThat(applicationSettings.getTopicName())
                .isEqualTo("topic-name");
        assertThat(applicationSettings.getMessageCount())
                .isEqualTo(10);
        assertThat(applicationSettings.getMessageInterval())
                .isEqualTo(1);
        assertThat(applicationSettings.getStartTime())
                .isEqualTo(Instant.MIN);
        assertThat(applicationSettings.getIsRealtime())
                .isTrue();
        assertThat(applicationSettings.getMessageTemplateFile())
                .isEqualTo("message-template-file");
        assertThat(applicationSettings.getTemplateConfigFile())
                .isEqualTo("template-config-file");
    }

    @Test
    public void shouldUpdateTemplateFileInSettingsFromCommandLine() {
        ApplicationSettings applicationSettings = buildSettings();

        when(mockedCommandLine.hasOption("message-template-file"))
                .thenReturn(true);
        when(mockedCommandLine.getOptionValue("message-template-file"))
                .thenReturn("message-template-file1");

        applicationSettings.updateFrom(mockedCommandLine);

        assertThat(applicationSettings.getTopicName())
                .isEqualTo("topic-name");
        assertThat(applicationSettings.getMessageCount())
                .isEqualTo(10);
        assertThat(applicationSettings.getMessageInterval())
                .isEqualTo(1);
        assertThat(applicationSettings.getStartTime())
                .isEqualTo(Instant.MIN);
        assertThat(applicationSettings.getIsRealtime())
                .isFalse();
        assertThat(applicationSettings.getMessageTemplateFile())
                .isEqualTo("message-template-file1");
        assertThat(applicationSettings.getTemplateConfigFile())
                .isEqualTo("template-config-file");
    }

    @Test
    public void shouldUpdateConfigFileInSettingsFromCommandLine() {
        ApplicationSettings applicationSettings = buildSettings();

        when(mockedCommandLine.hasOption("template-config-file"))
                .thenReturn(true);
        when(mockedCommandLine.getOptionValue("template-config-file"))
                .thenReturn("template-config-file1");

        applicationSettings.updateFrom(mockedCommandLine);

        assertThat(applicationSettings.getTopicName())
                .isEqualTo("topic-name");
        assertThat(applicationSettings.getMessageCount())
                .isEqualTo(10);
        assertThat(applicationSettings.getMessageInterval())
                .isEqualTo(1);
        assertThat(applicationSettings.getStartTime())
                .isEqualTo(Instant.MIN);
        assertThat(applicationSettings.getIsRealtime())
                .isFalse();
        assertThat(applicationSettings.getMessageTemplateFile())
                .isEqualTo("message-template-file");
        assertThat(applicationSettings.getTemplateConfigFile())
                .isEqualTo("template-config-file1");
    }
}