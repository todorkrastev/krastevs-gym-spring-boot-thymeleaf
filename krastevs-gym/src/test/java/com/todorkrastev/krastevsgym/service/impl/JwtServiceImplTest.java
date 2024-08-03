package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.config.JwtConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Key;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtServiceImplTest {

    private JwtServiceImpl jwtService;

    @Mock
    private JwtConfig mockJwtConfig;

    @BeforeEach
    void setUp() {
        jwtService = new JwtServiceImpl(mockJwtConfig);
    }

    @Test
    void generateToken_ReturnsToken() {
        String userId = "user123";
        Map<String, Object> claims = Map.of("role", "USER");
        String secret = "mysecretkeymysecretkeymysecretkeymysecretkey";
        long expiration = 3600000L; // 1 hour

        when(mockJwtConfig.getSecret()).thenReturn(secret);
        when(mockJwtConfig.getExpiration()).thenReturn(expiration);

        String token = jwtService.generateToken(userId, claims);

        assertNotNull(token);
    }

    @Test
    void getSingingKey_ReturnsKey() {
        String secret = "mysecretkeymysecretkeymysecretkeymysecretkey";

        when(mockJwtConfig.getSecret()).thenReturn(secret);

        Key key = jwtService.getSingingKey();

        assertNotNull(key);
    }
}