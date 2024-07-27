package com.todorkrastev.krastevsgym.config;

import com.todorkrastev.krastevsgym.service.JwtService;
import com.todorkrastev.krastevsgym.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Configuration
public class RestConfig {

    @Bean("genericRestClient")
    public RestClient genericRestClient() {
        return RestClient.create();
    }

    @Bean("activityRestClient")
    public RestClient exercisesRestClient(ActivityApiConfig activityApiConfig,
                                          ClientHttpRequestInterceptor requestInterceptor) {
        return RestClient
                .builder()
                .baseUrl(activityApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .requestInterceptor(requestInterceptor)
                .build();
    }

    @Bean
    public ClientHttpRequestInterceptor requestInterceptor(UserService userService, JwtService jwtService) {
        return (request, body, execution) -> {
            userService
                    .getCurrentUser()
                    .ifPresent(userDetails -> {
                        String bearerToken = jwtService.generateToken(
                                userDetails.getUuid().toString(),
                                Map.of(
                                        "roles",
                                        userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
                                )
                        );

                        System.out.println("Bearer token: " + bearerToken);

                        request.getHeaders().setBearerAuth(bearerToken);
                    });
            return execution.execute(request, body);
        };
    }
}