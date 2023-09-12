package br.com.example.msscauth.domain.usecases.auth;

import br.com.example.msscauth.application.adapters.AuthManager;
import br.com.example.msscauth.domain.exceptions.InvalidLoginException;
import br.com.example.msscauth.domain.exceptions.MissingParameterException;
import br.com.example.msscauth.domain.models.User;
import br.com.example.msscauth.application.adapters.repositories.UserRepository;
import br.com.example.msscauth.application.adapters.utils.JWTUtils;
import br.com.example.msscauth.application.adapters.PasswordEncrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthUseCaseImplTest {

    @InjectMocks
    private AuthUseCaseImpl sut;

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncrypt passwordEncrypt;

    @Mock
    private JWTUtils jwtUtils;

    @Mock
    private AuthManager manager;

    @BeforeEach
    void setUp() {
        lenient().when(repository.findByEmail(any())).thenReturn(of(User.builder()
                .email("valid@email.com")
                .password("valid_password")
                .build()));

        lenient().when(passwordEncrypt.compare(any(), any())).thenReturn(true);

        lenient().when(jwtUtils.generateToken(any())).thenReturn("valid_token");
    }

    @Test
    void shouldReturnMissingParameterException_whenEmailIsNull() {
        assertThrows(MissingParameterException.class, () -> sut.execute(null, null));
    }

    @Test
    void shouldReturnMissingParameterException_whenEmailIsEmpty() {
        assertThrows(MissingParameterException.class, () -> sut.execute("", null));
    }

    @Test
    void shouldReturnMissingParameterException_whenPasswordIsNull() {
        assertThrows(MissingParameterException.class, () ->
                sut.execute("valid@email.com", null));
    }

    @Test
    void shouldReturnMissingParameterException_whenPasswordIsEmpty() {
        assertThrows(MissingParameterException.class, () ->
                sut.execute("valid@email.com", ""));
    }

    @Test
    void shouldReturnException_whenUserNotFound() {
        when(repository.findByEmail(any())).thenReturn(Optional.empty());

        assertThrows(InvalidLoginException.class, () ->
                sut.execute("invalid@email.com", "any_password"));
    }

    @Test
    void shouldReturnException_whenPasswordIsInvalid() {
        when(passwordEncrypt.compare(any(), any())).thenReturn(false);
        assertThrows(InvalidLoginException.class, () ->
                sut.execute("valid@email.com", "invalid_password"));
    }

    @Test
    void shouldReturnAuthToken_whenLoginIsSuccess() {
        var authToken = sut.execute("valid@email.com", "valid_password");

        assertNotNull(authToken);
        assertNotNull(authToken.getAccessToken());
        assertEquals("valid_token", authToken.getAccessToken());
        verify(manager).authenticate(any());
    }
}
