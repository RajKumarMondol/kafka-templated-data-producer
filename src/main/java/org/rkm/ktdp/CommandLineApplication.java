package org.rkm.ktdp;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineApplication implements CommandLineRunner {
    private final ApplicationSettings applicationSettings;
    private final Logger logger;
    private final CommandLineParams commandLineParams;

    public CommandLineApplication(ApplicationSettings applicationSettings,
                                  Logger logger,
                                  CommandLineParams commandLineParams) {
        this.applicationSettings = applicationSettings;
        this.logger = logger;
        this.commandLineParams = commandLineParams;
    }

    @Override
    public void run(String... args) {
        CommandLine commandLine = commandLineParams.purse(args);
        if (commandLine.hasOption("help")) {
            commandLineParams.printHelp(new HelpFormatter());
        } else {
            applicationSettings.updateFrom(commandLine);
            logger.info(applicationSettings.toString());
        }
    }
}

