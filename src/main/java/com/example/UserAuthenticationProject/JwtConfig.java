package com.example.UserAuthenticationProject;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {

    // Use a secure key with a size >= 256 bits. generate a stronger secret key for
    // JWT HMAC-SHA algorithms with a size greater than or equal to 256 bits. This key is now secure enough
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Define token expiration time (e.g., 1 hour)
    private static final long EXPIRATION_TIME = 3600_000; // 1 hour in milliseconds 1 hour = 3600000

    @Bean
    public SecretKey secretKey() {
        return SECRET_KEY;
    }

    public long getExpirationTime() {
        return EXPIRATION_TIME;
    }
}