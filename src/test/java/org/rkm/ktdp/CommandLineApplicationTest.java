package org.rkm.ktdp;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommandLineApplicationTest {
    private ApplicationSettings mockApplicationSettings = Mockito.mock(ApplicationSettings.class);
    private Logger mockLogger = Mockito.mock(Logger.class);
    private CommandLineParams mockCommandLineParams = Mockito.mock(CommandLineParams.class);

    private CommandLineApplication commandLineApplication = new CommandLineApplication(mockApplicationSettings,
            mockLogger,
            mockCommandLineParams);

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
    public void shouldRunCommandlineApplication() {
        String[] args = new String[]{"--message-count", "10"};
        CommandLine mockedCommandLine = Mockito.mock(CommandLine.class);

        when(mockCommandLineParams.purse(args))
                .thenReturn(mockedCommandLine);
        when(mockApplicationSettings.toString())
                .thenReturn("mockSettings.toString()");

        commandLineApplication.run(args);

        verify(mockApplicationSettings)
                .updateFrom(mockedCommandLine);
        verify(mockLogger)
                .info("mockSettings.toString()");
    }
}