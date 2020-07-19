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
    private final MessageGenerator messageGenerator;

    public CommandLineApplication(ApplicationSettings applicationSettings,
                                  Logger logger,
                                  CommandLineParams commandLineParams,
                                  KafkaRecordProducer kafkaRecordProducer,
                                  MessageGenerator messageGenerator) {
        this.applicationSettings = applicationSettings;
        this.logger = logger;
        this.commandLineParams = commandLineParams;
        this.kafkaRecordProducer = kafkaRecordProducer;
        this.messageGenerator = messageGenerator;
    }

    @Override
    public void run(String... args) {
        try {
            CommandLine commandLine = commandLineParams.purse(args);
            if (commandLine.hasOption("help")) {
                commandLineParams.printHelp(new HelpFormatter());
            } else {
                applicationSettings.updateFrom(commandLine);
                logger.info(applicationSettings.toString());

                for (int index = 0; index < applicationSettings.getMessageCount(); index++) {
                    String[] result = messageGenerator.nextKeyAndMessage();
                    kafkaRecordProducer.produceSingleRecord(applicationSettings.getTopicName(), result[0], result[1]);
                }
            }
        } catch (Exception exception) {
            logger.error("Application has Exited due to unexpected exception.", exception);
        }
    }
}

