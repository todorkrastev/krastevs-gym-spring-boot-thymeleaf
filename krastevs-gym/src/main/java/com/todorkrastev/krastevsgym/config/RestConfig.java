package com.todorkrastev.krastevsgym.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Bean("genericRestClient")
    public RestClient genericRestClient() {
        return RestClient.create();
    }

    @Bean("exercisesRestClient")
    public RestClient exercisesRestClient(ExerciseApiConfig exerciseApiConfig) {
        return RestClient
                .builder()
                .baseUrl(exerciseApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
