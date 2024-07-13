package com.todorkrastev.krastevsgym.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "exercise.api")
public class ExerciseApiConfig {
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public ExerciseApiConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}
