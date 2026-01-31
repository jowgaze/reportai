package com.reportai.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ai")
@Getter
@Setter
public class AiConfig {
    private String model;
    private Prompt prompt;

    @Getter
    @Setter
    public static class Prompt {
        private String system;
        private String request;
    }
}
