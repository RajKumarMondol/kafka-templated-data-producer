package org.rkm.ktdp;

import lombok.SneakyThrows;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rkm.ktdp.configs.ApplicationSettings;
import org.rkm.ktdp.io.KafkaRecordProducer;
import org.rkm.ktdp.io.TemplateFileReader;
import org.slf4j.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommandLineApplicationTest {
    private Logger mockLogger = Mockito.mock(Logger.class);
    private ApplicationSettings mockApplicationSettings = Mockito.mock(ApplicationSettings.class);
    private CommandLineParams mockCommandLineParams = Mockito.mock(CommandLineParams.class);
    private KafkaRecordProducer mockedKafkaRecordProducer = Mockito.mock(KafkaRecordProducer.class);
    private MessageGenerator mockedMessageGenerator = Mockito.mock(MessageGenerator.class);
    private TemplateFileReader mockedTemplateFileReader = Mockito.mock(TemplateFileReader.class);

    private CommandLineApplication commandLineApplication = new CommandLineApplication(
            mockLogger,
            mockApplicationSettings,
            mockCommandLineParams,
            mockedKafkaRecordProducer,
            mockedMessageGenerator,
            mockedTemplateFileReader);

    @Test
    public void shouldDisplayHelpForCommandlineApplication() {
        String[] args = new String[]{"--help"};
        CommandLine mockedCommandLine = Mockito.mock(CommandLine.class);

        when(mockCommandLineParams.purse(args))
                .thenReturn(mockedCommandLine);
        when(mockedCommandLine.hasOption("help"))
                .thenReturn(true);

        commandLineApplication.run(args);

        verify(mockCommandLineParams)
                .printHelp(any(HelpFormatter.class));
    }

    @Test
    @SneakyThrows
    public void shouldProduceTestMessageToKafkaFromCommandlineApplication() {
        String[] args = new String[]{"--message-count", "10"};
        CommandLine mockedCommandLine = Mockito.mock(CommandLine.class);

        when(mockCommandLineParams.purse(args))
                .thenReturn(mockedCommandLine);
        when(mockApplicationSettings.getTopicName())
                .thenReturn("output");
        when(mockApplicationSettings.toString())
                .thenReturn("mockSettings.toString()");
        when(mockApplicationSettings.getMessageCount())
                .thenReturn(1);
        when(mockedMessageGenerator.nextKeyAndMessage())
                .thenReturn(new String[]{"testKey", "testMessage"});

        commandLineApplication.run(args);

        verify(mockApplicationSettings)
                .updateFrom(mockedCommandLine);
        verify(mockedMessageGenerator)
                .initialize(mockApplicationSettings, mockedTemplateFileReader);
        verify(mockLogger)
                .info("mockSettings.toString()");
        verify(mockedKafkaRecordProducer)
                .produceSingleRecord("output", "testKey", "testMessage");
    }
}