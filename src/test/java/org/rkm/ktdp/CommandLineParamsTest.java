package org.rkm.ktdp;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class CommandLineParamsTest {
    private CommandLineParams commandLineParams = new CommandLineParams();

    @Test
    public void shouldBeAbleToParseTopicNameFromCommandLineParams() {
        CommandLine commandLine = commandLineParams.purse(new String[]{"--topic-name", "topicName"});

        assertThat(commandLine.getOptionValue("topic-name"))
                .isEqualTo("topicName");
    }

    @Test
    public void shouldBeAbleToParseMessageCountFromCommandLineParams() {
        CommandLine commandLine = commandLineParams.purse(new String[]{"--message-count", "10"});

        assertThat(commandLine.getOptionValue("message-count"))
                .isEqualTo("10");
    }

    @Test
    public void shouldBeAbleToParseMessageIntervalFromCommandLineParams() {
        CommandLine commandLine = commandLineParams.purse(new String[]{"--message-interval", "10"});

        assertThat(commandLine.getOptionValue("message-interval"))
                .isEqualTo("10");
    }

    @Test
    public void shouldBeAbleToParseTemplateFileFromCommandLineParams() {
        CommandLine commandLine = commandLineParams
                .purse(new String[]{"--message-template-file", "messageTemplateFile"});

        assertThat(commandLine.getOptionValue("message-template-file"))
                .isEqualTo("messageTemplateFile");
    }

    @Test
    public void shouldBeAbleToParseConfigFileFromCommandLineParams() {
        CommandLine commandLine = commandLineParams.purse(new String[]{"--template-config-file", "templateConfigFile"});

        assertThat(commandLine.getOptionValue("template-config-file"))
                .isEqualTo("templateConfigFile");
    }

    @Test
    public void shouldBeAbleToParseStarTimeFromCommandLineParams() {
        CommandLine commandLine = commandLineParams.purse(new String[]{"--start-time", Instant.MIN.toString()});

        assertThat(commandLine.getOptionValue("start-time"))
                .isEqualTo(Instant.MIN.toString());
    }

    @Test
    public void shouldBeAbleToParseRealtimeFromCommandLineParams() {
        CommandLine commandLine = commandLineParams.purse(new String[]{"--realtime"});

        assertThat(commandLine.hasOption("realtime"))
                .isTrue();
    }

    @Test
    public void shouldBeAbleToParseHelpFromCommandLineParams() {
        CommandLine commandLine = commandLineParams.purse(new String[]{"--help"});

        assertThat(commandLine.hasOption("help"))
                .isTrue();
    }

    @Test
    public void shouldThrowErrorForInvalidCommandLineParams() {
        assertThrows(UnrecognizedOptionException.class, () -> commandLineParams.purse(new String[]{"--xyz"}));
    }

    @Test
    public void shouldPrintHelpForCommandLineParams() {
        HelpFormatter mockedHelpFormatter = Mockito.mock(HelpFormatter.class);
        commandLineParams.printHelp(mockedHelpFormatter);
        verify(mockedHelpFormatter)
                .printHelp(eq("ktdp")
                        , eq("Options, flags and arguments may be in any order")
                        , any(Options.class)
                        , eq("This Kafka Data Producer to produce message to kafka topic based on " +
                                "given template configuration and template file.Brought to you by RKM.")
                        , eq(true));
    }
}