package org.rkm.ktdp.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.rkm.ktdp.templates.specification.BaseTemplate;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
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
        } catch (IOException exception) {
            logger.error("Error while reading template configuration.", exception);
            throw exception;
        }
    }

    public String[] messageTemplateFile(String inputPath) throws IOException {
        List<String> lines = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(new File(inputPath)))) {
            String line = reader.readLine();
            while (!StringUtils.isEmpty(line)) {
                lines.add(line.trim());
                line = reader.readLine();
            }
        } catch (IOException exception) {
            logger.error("Error while reading message template file.", exception);
            throw exception;
        }
        return lines.stream().toArray(String[]::new);
    }
}
