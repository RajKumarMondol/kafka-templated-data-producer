package org.rkm.ktdp;

import lombok.SneakyThrows;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.springframework.stereotype.Component;

@Component
public class CommandLineParams {
    private final Options options;

    public CommandLineParams() {
        options = new Options();

        options.addOption(Option.builder()
                .longOpt("topic-name")
                .hasArg()
                .desc("Kafka topic name to produce message")
                .build());
        options.addOption(Option.builder()
                .longOpt("message-count")
                .hasArg()
                .desc("Number of messages to publish")
                .build());
        options.addOption(Option.builder()
                .longOpt("message-interval")
                .hasArg()
                .desc("Time difference between messages in milli seconds")
                .build());
        options.addOption(Option.builder()
                .longOpt("message-template-file")
                .hasArg()
                .desc("The message template file with messages in each line")
                .build());
        options.addOption(Option.builder()
                .longOpt("template-config-file")
                .hasArg()
                .desc("The file containing template fields configuration")
                .build());
        options.addOption(Option.builder()
                .longOpt("start-time")
                .hasArg()
                .desc("Given time stamp as start timestamp for messages. Uses current time stamp if not provided.")
                .build());
        options.addOption(Option.builder()
                .longOpt("realtime")
                .desc("Use <interval> ms delay between message production")
                .build());
        options.addOption(Option.builder()
                .longOpt("help")
                .desc("Display help for command line usage.")
                .build());
    }

    @SneakyThrows
    public CommandLine purse(String[] args) {
        return new DefaultParser().parse(options, args);
    }

    public void printHelp(HelpFormatter helpFormatter) {
        helpFormatter.printHelp("ktdp",
                "Options, flags and arguments may be in any order",
                options,
                "This Kafka Data Producer to produce message to kafka topic based on " +
                        "given template configuration and template file.Brought to you by RKM.",
                true);
    }
}
