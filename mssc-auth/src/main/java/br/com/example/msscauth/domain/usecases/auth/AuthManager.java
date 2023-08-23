package br.com.example.msscauth.domain.usecases.auth;

import br.com.example.msscauth.domain.models.User;

public interface AuthManager {

    void authenticate(User user);
}
