package br.com.example.msscauth.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizationToken {
    private String accessToken;
    private String refreshToken;
}
