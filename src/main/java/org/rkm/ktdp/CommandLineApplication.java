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
    private final KafkaRecordProducer kafkaRecordProducer;

    public CommandLineApplication(ApplicationSettings applicationSettings,
                                  Logger logger,
                                  CommandLineParams commandLineParams,
                                  KafkaRecordProducer kafkaRecordProducer) {
        this.applicationSettings = applicationSettings;
        this.logger = logger;
        this.commandLineParams = commandLineParams;
        this.kafkaRecordProducer = kafkaRecordProducer;
    }

    @Override
    public void run(String... args) {
        CommandLine commandLine = commandLineParams.purse(args);
        if (commandLine.hasOption("help")) {
            commandLineParams.printHelp(new HelpFormatter());
        } else {
            applicationSettings.updateFrom(commandLine);
            logger.info(applicationSettings.toString());

            kafkaRecordProducer
                    .produceSingleRecord(applicationSettings.getTopicName(), "testKey", "testMessage");
        }
    }
}

