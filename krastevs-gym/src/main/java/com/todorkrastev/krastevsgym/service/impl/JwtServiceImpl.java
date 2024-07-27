package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.config.JwtConfig;
import com.todorkrastev.krastevsgym.service.JwtService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {
    private final JwtConfig jwtConfig;

    public JwtServiceImpl(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public String generateToken(String userId, Map<String, Object> claims) {
        var now = new Date();

        JwtBuilder jwtBuilder = Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(new Date(now.getTime() + jwtConfig.getExpiration()))
                .signWith(getSingingKey(), SignatureAlgorithm.HS256);

        return jwtBuilder.compact();
    }

    private Key getSingingKey() {
        byte[] keyBytes = jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
