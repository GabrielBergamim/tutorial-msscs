package br.com.example.msscauth.domain.usecases.auth;

import br.com.example.msscauth.domain.exceptions.InvalidLoginException;
import br.com.example.msscauth.domain.exceptions.MissingParameterException;
import br.com.example.msscauth.domain.models.User;
import br.com.example.msscauth.domain.repositories.UserRepository;
import br.com.example.msscauth.domain.utils.PasswordEncrypt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AuthUseCaseImplTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncrypt passwordEncrypt;

    private AuthUseCaseImpl sut;

    @BeforeEach
    void setUp() {
        Mockito.lenient().when(repository.findByEmail(any())).thenReturn(Optional.of(User.builder()
                .email("valid@email.com")
                .password("valid_password")
                .build()));

        Mockito.lenient().when(passwordEncrypt.compare(any(), any())).thenReturn(true);

        sut = new AuthUseCaseImpl(repository, passwordEncrypt);
    }

    @Test
    void shouldReturnMissingParameterException_whenEmailIsNull() {
        Assertions.assertThrows(MissingParameterException.class, () -> sut.execute(null, null));
    }

    @Test
    void shouldReturnMissingParameterException_whenEmailIsEmpty() {
        Assertions.assertThrows(MissingParameterException.class, () -> sut.execute("", null));
    }

    @Test
    void shouldReturnMissingParameterException_whenPasswordIsNull() {
        Assertions.assertThrows(MissingParameterException.class, () ->
                sut.execute("valid@email.com", null));
    }

    @Test
    void shouldReturnMissingParameterException_whenPasswordIsEmpty() {
        Assertions.assertThrows(MissingParameterException.class, () ->
                sut.execute("valid@email.com", ""));
    }

    @Test
    void shouldReturnException_whenUserNotFound() {
        Mockito.when(repository.findByEmail(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidLoginException.class, () ->
                sut.execute("invalid@email.com", "any_password"));
    }

    @Test
    void shouldReturnException_whenPasswordIsInvalid() {
        Mockito.when(passwordEncrypt.compare(any(), any())).thenReturn(false);
        Assertions.assertThrows(InvalidLoginException.class, () ->
                sut.execute("valid@email.com", "invalid_password"));
    }
}
