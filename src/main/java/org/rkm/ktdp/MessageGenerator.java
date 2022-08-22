package org.rkm.ktdp;

import org.rkm.ktdp.configs.ApplicationSettings;
import org.rkm.ktdp.io.TemplateFileReader;
import org.rkm.ktdp.templates.specification.BaseTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MessageGenerator {
    private MessageTemplate[] messageTemplates;
    private Map<String, BaseTemplate> configuredTemplates;
    private Integer messageIndex = 0;

    public String[] nextKeyAndMessage() {
        try {
            int messageTemplateIndex = this.messageIndex % this.messageTemplates.length;
            MessageTemplate messageTemplate = this.messageTemplates[messageTemplateIndex];
            return new String[]{messageIndex.toString(), messageTemplate.generate()};
        } finally {
            messageIndex++;
        }
    }

    public void initialize(ApplicationSettings settings, TemplateFileReader templateFileReader) throws IOException {
        this.configuredTemplates = templateFileReader.configurationFile(settings.getTemplateConfigFile())
                .stream()
                .collect(Collectors.toMap(t -> "<" + t.getName() + ">", t -> t));
        this.messageTemplates = Arrays.asList(templateFileReader.messageFile(settings.getMessageTemplateFile()))
                .stream()
                .filter(line -> !StringUtils.isEmpty(line))
                .map(message -> new MessageTemplate(message, configuredTemplates.entrySet()
                        .stream()
                        .filter(template -> message.contains(template.getKey()))
                        .collect(Collectors.toList())))
                .toArray(MessageTemplate[]::new);
    }
}
