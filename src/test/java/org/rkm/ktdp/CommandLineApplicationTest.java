package org.rkm.ktdp;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommandLineApplicationTest {
    private CommandLineParams mockCommandLineParams = Mockito.mock(CommandLineParams.class);

    private CommandLineApplication commandLineApplication = new CommandLineApplication(mockCommandLineParams);

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
}