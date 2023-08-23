package br.com.example.msscauth.infrastructure.services;

import br.com.example.msscauth.domain.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthManagerImplTest {

    @InjectMocks
    private AuthManagerImpl sut;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    void shouldAuthenticateCallingAuthenticationManager() {
        sut.authenticate(User.builder().build());

        verify(authenticationManager).authenticate(any());
    }
}