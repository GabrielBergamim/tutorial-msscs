package br.com.example.msscauth.domain.usecases.createuser;

import br.com.example.msscauth.domain.dto.user.UserInputDto;
import br.com.example.msscauth.domain.exceptions.UserValidationException;
import br.com.example.msscauth.domain.models.User;
import br.com.example.msscauth.domain.repositories.UserRepository;
import br.com.example.msscauth.domain.usecases.usernotification.UserNotification;
import br.com.example.msscauth.domain.utils.PasswordEncrypt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseImplTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserNotification userNotification;

    @Mock
    private PasswordEncrypt passwordEncrypt;

    private CreateUserUseCaseImpl sut;

    private UserInputDto user;

    @BeforeEach
    void setUp() {
        sut = new CreateUserUseCaseImpl(repository, new ModelMapper(), userNotification, passwordEncrypt);

        user = UserInputDto.builder()
                .email("test@email.com")
                .password("123456789")
                .passwordConfirmation("123456789")
                .build();
    }

    @Test
    void shouldReturnUserValidationException_whenUserInputIsNull() {
        Assertions.assertThrows(UserValidationException.class, () -> sut.execute(null));
    }

    @Test
    void shouldReturnUserValidationException_whenUserEmailInputIsEmpty() {
        user.setEmail("");
        Assertions.assertThrows(UserValidationException.class, () -> sut.execute(user));
    }

    @Test
    void shouldReturnUserValidationException_whenUserEmailInputIsInvalid() {
        user.setEmail("teste2email.com");
        Assertions.assertThrows(UserValidationException.class, () -> sut.execute(user));
    }

    @Test
    void shouldReturnUserValidationException_whenUserPasswordInputIsEmpty() {
        user.setPassword("");
        Assertions.assertThrows(UserValidationException.class, () -> sut.execute(user));
    }

    @Test
    void shouldReturnUserValidationException_whenUserPasswordConfirmationInputIsEmpty() {
        user.setPasswordConfirmation("");
        Assertions.assertThrows(UserValidationException.class, () -> sut.execute(user));
    }

    @Test
    void shouldReturnUserValidationException_whenUserPwdAndPwdConfirmationInputIsDifferent() {
        user.setPasswordConfirmation("1234");
        Assertions.assertThrows(UserValidationException.class, () -> sut.execute(user));
    }

    @Test
    void shouldReturnUserValidationException_whenUserInputEmailAlreadyExists() {
        Mockito.when(repository.findByEmail(any())).thenReturn(Optional.of(User.builder()
                .id(1)
                .email(user.getEmail())
                .build()));

        Assertions.assertThrows(UserValidationException.class, () -> sut.execute(user));
    }

    @Test
    void shouldReturnCreatedUserWithId_whenParameterUserIsInformed() {
        Mockito.when(repository.save(any())).thenReturn(User.builder()
                .id(1)
                .email(user.getEmail())
                .build());

        Mockito.when(passwordEncrypt.encrypt(any())).thenReturn(user.getPassword());

        var userCreated = sut.execute(user);

        Assertions.assertTrue(userCreated.getId() > 0);
        Assertions.assertEquals(user.getEmail(), userCreated.getEmail());
        verify(repository, times(1)).save(any());
        verify(passwordEncrypt, times(1)).encrypt(any());
        verify(userNotification, times(1)).execute(any());
    }
}
