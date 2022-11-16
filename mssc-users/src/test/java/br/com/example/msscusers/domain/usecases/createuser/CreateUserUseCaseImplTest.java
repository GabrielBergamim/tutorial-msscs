package br.com.example.msscusers.domain.usecases.createuser;

import br.com.example.msscusers.domain.dto.UserInputDto;
import br.com.example.msscusers.domain.exceptions.UserValidationException;
import br.com.example.msscusers.domain.models.User;
import br.com.example.msscusers.domain.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseImplTest {

    @Mock
    private UserRepository repository;

    private CreateUserUseCaseImpl sut;

    private UserInputDto user;

    @BeforeEach
    void setUp() {
        sut = new CreateUserUseCaseImpl(repository, new ModelMapper());

        user = UserInputDto.builder()
                .email("test@email.com")
                .password("123456789")
                .passowrdConfirmation("123456789")
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
        user.setPassowrdConfirmation("");
        Assertions.assertThrows(UserValidationException.class, () -> sut.execute(user));
    }

    @Test
    void shouldReturnUserValidationException_whenUserPwdAndPwdConfirmationInputIsDifferent() {
        user.setPassowrdConfirmation("1234");
        Assertions.assertThrows(UserValidationException.class, () -> sut.execute(user));
    }

    @Test
    void shouldReturnCreatedUserWithId_whenParameterUserIsInformed() {
        Mockito.when(repository.save(any())).thenReturn(User.builder()
                .id(1)
                .email(user.getEmail())
                .build());

        var userCreated = sut.execute(user);

        Assertions.assertTrue(userCreated.getId() > 0);
        Assertions.assertEquals(user.getEmail(), userCreated.getEmail());
    }
}
