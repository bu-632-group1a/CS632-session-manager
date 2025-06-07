package com.example.sessionservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;

@Configuration
public class JwtConfig {
    @Bean
    public JwtDecoder jwtDecoder() {
        String secret = System.getenv("JWT_SECRET");
        System.out.println("JWT Secret: " + secret);
        return NimbusJwtDecoder.withSecretKey(
            new SecretKeySpec(secret.getBytes(), "HmacSHA256")
        ).build();
    }
}