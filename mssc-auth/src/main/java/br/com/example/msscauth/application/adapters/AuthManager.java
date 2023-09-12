package br.com.example.msscauth.application.adapters;

import br.com.example.msscauth.domain.models.User;

public interface AuthManager {

    void authenticate(User user);
}
