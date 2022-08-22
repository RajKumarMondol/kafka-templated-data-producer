package org.rkm.ktdp;

import org.rkm.ktdp.templates.specification.BaseTemplate;

import java.util.List;
import java.util.Map;

public class MessageTemplate {
    private final String messageWithTemplates;
    private final List<Map.Entry<String, BaseTemplate>> usedTemplates;

    MessageTemplate(final String messageWithTemplates, final List<Map.Entry<String, BaseTemplate>> usedTemplates) {
        this.messageWithTemplates = messageWithTemplates;
        this.usedTemplates = usedTemplates;
    }

    public String generate() {
        String resultMessage = messageWithTemplates;
        for (Map.Entry<String, BaseTemplate> currentTemplate : usedTemplates) {
            resultMessage = resultMessage.replace(currentTemplate.getKey(), currentTemplate.getValue().generate());
        }
        return resultMessage;
    }
}