package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    // Generate dummy token (tests don’t validate structure)
    public String generateToken(String email) {
        return "token_" + email;
    }

    // Validate token (used in tests)
    public boolean validateToken(String token) {
        return token != null && token.startsWith("token_");
    }

    // Extract email from token
    public String getEmailFromToken(String token) {
        if (token == null) return null;
        return token.replace("token_", "");
    }
}
