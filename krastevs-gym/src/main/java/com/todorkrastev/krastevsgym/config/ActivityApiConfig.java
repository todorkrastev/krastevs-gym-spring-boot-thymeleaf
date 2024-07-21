package com.todorkrastev.krastevsgym.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "activity.api")
public class ActivityApiConfig {
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public ActivityApiConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}
