package org.rkm.ktdp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.rkm.ktdp.templates.specification.BaseTemplate;
import org.slf4j.Logger;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.List;

public class FileReader {
    private final Logger logger;

    public FileReader(Logger logger) {
        this.logger = logger;
    }

    public List<BaseTemplate> templateConfigurations(String templateConfigurationFile) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            return objectMapper.readValue(ResourceUtils.getFile(templateConfigurationFile),
                    new TypeReference<List<BaseTemplate>>() {
                    });
        } catch (IOException e) {
            logger.error("Error while reading template configuration.", e);
            throw e;
        }
    }
}
