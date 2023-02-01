package br.com.example.msscauth.domain.utils;

import java.util.Map;

public interface JWTUtils {

    String generateToken(Map<String, Object> extraClaims, String email);

    String generateToken(String email);

    boolean isTokenValid(String token, String email);

    public String extractUsername(String token);
}
