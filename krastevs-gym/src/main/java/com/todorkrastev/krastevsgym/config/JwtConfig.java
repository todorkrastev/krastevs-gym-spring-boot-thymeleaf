package com.todorkrastev.krastevsgym.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private String secret;
    private long expiration;

    public JwtConfig() {
    }

    public String getSecret() {
        return secret;
    }

    public JwtConfig setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public long getExpiration() {
        return expiration;
    }

    public JwtConfig setExpiration(long expiration) {
        this.expiration = expiration;
        return this;
    }
}