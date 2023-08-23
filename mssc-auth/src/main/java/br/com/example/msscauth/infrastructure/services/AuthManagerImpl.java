package br.com.example.msscauth.infrastructure.services;

import br.com.example.msscauth.domain.models.User;
import br.com.example.msscauth.domain.usecases.auth.AuthManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RequiredArgsConstructor
public class AuthManagerImpl implements AuthManager {

    private final AuthenticationManager authenticationManager;

    @Override
    public void authenticate(User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),
                user.getPassword()));
    }
}
