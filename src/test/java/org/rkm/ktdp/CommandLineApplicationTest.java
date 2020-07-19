package org.rkm.ktdp;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
public class CommandLineApplicationTest {
    private ApplicationSettings mockedApplicationSettings = Mockito.mock(ApplicationSettings.class);
    private Logger mockedLogger = Mockito.mock(Logger.class);
    private MessageGenerator mockedMessageGenerator = Mockito.mock(MessageGenerator.class);
    private CommandLineParams mockedCommandLineParams = Mockito.mock(CommandLineParams.class);
    private KafkaRecordProducer mockedKafkaRecordProducer = Mockito.mock(KafkaRecordProducer.class);

    private CommandLineApplication commandLineApplication = new CommandLineApplication(
            mockedApplicationSettings,
            mockedLogger,
            mockedCommandLineParams,
            mockedKafkaRecordProducer,
            mockedMessageGenerator);

    @Test
    public void shouldDisplayHelpForCommandlineApplication() {
        String[] args = new String[]{"--help"};
        CommandLine mockedCommandLine = Mockito.mock(CommandLine.class);

        when(mockedCommandLineParams.purse(args))
                .thenReturn(mockedCommandLine);
        when(mockedCommandLine.hasOption("help"))
                .thenReturn(true);

        commandLineApplication.run(args);

        verify(mockedCommandLineParams)
                .printHelp(any(HelpFormatter.class));
    }

    @Test
    public void shouldProduceTestMessageToKafkaFromCommandlineApplication() {
        String[] args = new String[]{"--message-count", "10"};
        CommandLine mockedCommandLine = Mockito.mock(CommandLine.class);

        when(mockedCommandLineParams.purse(args))
                .thenReturn(mockedCommandLine);
        when(mockedApplicationSettings.getTopicName())
                .thenReturn("output");
        when(mockedApplicationSettings.getMessageTemplateFile())
                .thenReturn("template.file");
        when(mockedApplicationSettings.getMessageCount())
                .thenReturn(5);
        when(mockedApplicationSettings.toString())
                .thenReturn("mockSettings.toString()");
        when(mockedMessageGenerator.nextKeyAndMessage())
                .thenReturn(new String[]{"testKey0", "Message0"})
                .thenReturn(new String[]{"testKey1", "Message1"})
                .thenReturn(new String[]{"testKey0", "Message0"})
                .thenReturn(new String[]{"testKey1", "Message1"})
                .thenReturn(new String[]{"testKey0", "Message0"});

        commandLineApplication.run(args);

        verify(mockedApplicationSettings)
                .updateFrom(mockedCommandLine);
        verify(mockedLogger)
                .info("mockSettings.toString()");

        verify(mockedKafkaRecordProducer, times(3))
                .produceSingleRecord("output", "testKey0", "Message0");
        verify(mockedKafkaRecordProducer, times(2))
                .produceSingleRecord("output", "testKey1", "Message1");
    }

    @Test
    public void shouldLogErrorIfExceptionOccursWhileProcessing() {
        String[] args = new String[]{};

        when(mockedCommandLineParams.purse(args))
                .thenThrow(new RuntimeException("test"));

        commandLineApplication.run(args);

        verify(mockedLogger)
                .error(eq("Application has Exited due to unexpected exception."), any(RuntimeException.class));
    }
}