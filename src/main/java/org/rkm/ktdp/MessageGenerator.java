package org.rkm.ktdp;

import lombok.SneakyThrows;
import org.rkm.ktdp.configs.ApplicationSettings;
import org.rkm.ktdp.io.FileReader;
import org.rkm.ktdp.templates.specification.BaseTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MessageGenerator {
    private final String[][] templateMessageTemplateUsage;
    private final String[] templateMessages;
    private final Map<String, BaseTemplate> configuredTemplates;
    private Integer messageIndex = 0;

    @SneakyThrows
    public MessageGenerator(ApplicationSettings settings, FileReader fileReader) {
        this.configuredTemplates = fileReader.templateConfigurations(settings.getTemplateConfigFile())
                .stream()
                .collect(Collectors.toMap(t -> "<" + t.getName() + ">", t -> t));
        this.templateMessages = fileReader.messageTemplateFile(settings.getMessageTemplateFile());
        this.templateMessageTemplateUsage = Arrays.asList(templateMessages)
                .stream()
                .map(msg -> configuredTemplates.keySet()
                        .stream()
                        .filter(template -> msg.contains(template))
                        .toArray(String[]::new))
                .toArray(String[][]::new);
    }

    public String[] nextKeyAndMessage() {
        try {
            int currentTemplateIndex = messageIndex % templateMessages.length;
            String messageTemplate = templateMessages[currentTemplateIndex];
            String[] usedTemplates = templateMessageTemplateUsage[currentTemplateIndex];
            for (String usedTemplate : usedTemplates) {
                messageTemplate = messageTemplate
                        .replace(usedTemplate, configuredTemplates.get(usedTemplate).generate());
            }
            return new String[]{messageIndex.toString(), messageTemplate};
        } finally {
            messageIndex++;
        }
    }
}
