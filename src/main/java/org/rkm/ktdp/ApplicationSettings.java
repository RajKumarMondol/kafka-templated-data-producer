package org.rkm.ktdp;

import lombok.Getter;
import org.apache.commons.cli.CommandLine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Getter
public class ApplicationSettings {
    private String topicName;
    private Integer messageCount;
    private Integer messageInterval;
    private Instant startTime;
    private Boolean isRealtime = false;
    private String messageTemplateFile;
    private String templateConfigFile;

    public ApplicationSettings(@Value("${settings.topic-name}") String topicName,
                               @Value("${settings.message-count}") int messageCount,
                               @Value("${settings.message-interval}") int messageInterval,
                               @Value("${settings.start-time}") Instant startTime,
                               @Value("${settings.message-template-file}") String messageTemplateFile,
                               @Value("${settings.template-config-file}") String templateConfigFile) {
        this.topicName = topicName;
        this.messageCount = messageCount;
        this.messageInterval = messageInterval;
        this.startTime = startTime;
        this.messageTemplateFile = messageTemplateFile;
        this.templateConfigFile = templateConfigFile;
    }

    public void updateFrom(CommandLine commandLine) {
        if (commandLine.hasOption("topic-name")) {
            topicName = commandLine.getOptionValue("topic-name");
        }
        if (commandLine.hasOption("message-count")) {
            messageCount = Integer.parseUnsignedInt(commandLine.getOptionValue("message-count"));
        }
        if (commandLine.hasOption("message-interval")) {
            messageInterval = Integer.parseUnsignedInt(commandLine.getOptionValue("message-interval"));
        }
        if (commandLine.hasOption("message-template-file")) {
            messageTemplateFile = commandLine.getOptionValue("message-template-file");
        }
        if (commandLine.hasOption("template-config-file")) {
            templateConfigFile = commandLine.getOptionValue("template-config-file");
        }
        if (commandLine.hasOption("start-time")) {
            startTime = Instant.parse(commandLine.getOptionValue("start-time"));
        }
        isRealtime = commandLine.hasOption("realtime");
    }

    public String toString() {
        return "Kafka Templated Data Producer Settings :\n" +
                "\ttopicName = " + this.topicName + ",\n" +
                "\tmessageCount = " + this.messageCount + ",\n" +
                "\tmessageInterval = " + this.messageInterval + ",\n" +
                "\tstartTime = " + this.startTime + ",\n" +
                "\tisRealtime = " + this.isRealtime + ",\n" +
                "\tmessageTemplateFile = " + this.messageTemplateFile + ",\n" +
                "\ttemplateConfigFile = " + this.templateConfigFile
                ;
    }
}
