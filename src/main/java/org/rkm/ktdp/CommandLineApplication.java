package org.rkm.ktdp;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.springframework.boot.CommandLineRunner;

public class CommandLineApplication implements CommandLineRunner {
    private final CommandLineParams commandLineParams;

    public CommandLineApplication(CommandLineParams commandLineParams) {
        this.commandLineParams = commandLineParams;
    }

    @Override
    public void run(String... args) {
        CommandLine commandLine = commandLineParams.purse(args);
        if (commandLine.hasOption("help")) {
            commandLineParams.printHelp(new HelpFormatter());
        }
    }
}

