package org.rkm.ktdp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private static Logger applicationLogger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
        } catch (Exception exception) {
            applicationLogger.error("Unknown exception occurred", exception);
        }
    }

    @Bean
    public static Logger applicationLogger() {
        return applicationLogger;
    }
}
