package org.rkm.ktdp;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.rkm.ktdp.configs.ApplicationSettings;
import org.rkm.ktdp.io.KafkaRecordProducer;
import org.rkm.ktdp.io.TemplateFileReader;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineApplication implements CommandLineRunner {
    private final Logger logger;
    private final ApplicationSettings applicationSettings;
    private final CommandLineParams commandLineParams;
    private final KafkaRecordProducer kafkaRecordProducer;
    private final MessageGenerator messageGenerator;
    private final TemplateFileReader templateFileReader;

    public CommandLineApplication(Logger logger,
                                  ApplicationSettings applicationSettings,
                                  CommandLineParams commandLineParams,
                                  KafkaRecordProducer kafkaRecordProducer,
                                  MessageGenerator messageGenerator,
                                  TemplateFileReader templateFileReader) {
        this.logger = logger;
        this.applicationSettings = applicationSettings;
        this.commandLineParams = commandLineParams;
        this.kafkaRecordProducer = kafkaRecordProducer;
        this.messageGenerator = messageGenerator;
        this.templateFileReader = templateFileReader;
    }

    @Override
    public void run(String... args) {
        try {
            CommandLine commandLine = commandLineParams.purse(args);
            if (commandLine.hasOption("help")) {
                commandLineParams.printHelp(new HelpFormatter());
                return;
            } else {
                applicationSettings.updateFrom(commandLine);
                logger.info(applicationSettings.toString());
            }
        } catch (Exception exception) {
            logger.error("Unable to parse command line params :" + exception.getMessage(), exception);
            return;
        }
        try {
            messageGenerator.initialize(applicationSettings, templateFileReader);
        } catch (IOException exception) {
            return;
        } catch (Exception exception) {
            logger.error("Unable to configure message template :" + exception.getMessage(), exception);
            return;
        }
        try {
            for (int index = 0; index < applicationSettings.getMessageCount(); index++) {
                String[] result = messageGenerator.nextKeyAndMessage();
//                kafkaRecordProducer.produceSingleRecord(applicationSettings.getTopicName(), result[0], result[1]);
                System.out.println(result[0]);
                System.out.println(result[1]);
            }
        } catch (Exception exception) {
            logger.error("Error occurred while generating message :" + exception.getMessage(), exception);
            return;
        }
    }
}

