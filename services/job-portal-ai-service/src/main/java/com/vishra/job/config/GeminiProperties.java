package com.vishra.job.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@ConfigurationProperties(prefix = "gemini.api")
public class GeminiProperties {
    private String key;
    private String model;
    private int maxOutputTokens = 2048;
    private double temperature  = 0.7;
}
