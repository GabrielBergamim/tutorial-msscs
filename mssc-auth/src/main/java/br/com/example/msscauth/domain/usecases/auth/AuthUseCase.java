package br.com.example.msscauth.domain.usecases.auth;

import br.com.example.msscauth.domain.models.AuthorizationToken;

public interface AuthUseCase {

    AuthorizationToken execute(String email, String password);
}
