package com.vishra.job.config;

import com.google.genai.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestClientConfig {

    @Bean
    public Client genClient(GeminiProperties  properties){
        return Client.builder()
                .apiKey(properties.getKey())
                .build();
    }
}
