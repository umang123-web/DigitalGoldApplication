package com.gold.goldapp.DigitalGoldApplication.configuration;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("${openai.api.key:}") // default empty string if not set
    private String apiKey;

    @Bean
    public OpenAiService openAiService() {
        if (apiKey == null || apiKey.isBlank()) {
            throw new RuntimeException("OpenAI token required. Set openai.api.key in application.properties");
        }
        return new OpenAiService(apiKey);
    }
    }
