package br.com.example.msscauth.domain.utils;

public interface JWTUtils {

    String generateToken(String email);

    boolean isValid(String token);
}
