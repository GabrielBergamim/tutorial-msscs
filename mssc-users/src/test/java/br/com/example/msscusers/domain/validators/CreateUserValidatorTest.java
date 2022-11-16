package br.com.example.msscusers.domain.validators;

import br.com.example.msscusers.domain.dto.UserInputDto;
import br.com.example.msscusers.domain.exceptions.UserValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateUserValidatorTest {

    private UserInputDto user;

    @BeforeEach
    void setUp() {
        user = UserInputDto.builder()
                .email("test@email.com")
                .password("123456789")
                .passowrdConfirmation("123456789")
                .build();
    }

    @Test
    void shouldReturnUserValidationException_whenUserInputIsNull() {
        Assertions.assertThrows(UserValidationException.class, () -> CreateUserValidator.validate(null));
    }

    @Test
    void shouldReturnUserValidationException_whenUserEmailInputIsEmpty() {
        user.setEmail("");
        Assertions.assertThrows(UserValidationException.class, () -> CreateUserValidator.validate(user));
    }

    @Test
    void shouldReturnUserValidationException_whenUserEmailInputIsInvalid() {
        user.setEmail("teste2email.com");
        Assertions.assertThrows(UserValidationException.class, () -> CreateUserValidator.validate(user));
    }

    @Test
    void shouldReturnUserValidationException_whenUserPasswordInputIsEmpty() {
        user.setPassword("");
        Assertions.assertThrows(UserValidationException.class, () -> CreateUserValidator.validate(user));
    }

    @Test
    void shouldReturnUserValidationException_whenUserPasswordConfirmationInputIsEmpty() {
        user.setPassowrdConfirmation("");
        Assertions.assertThrows(UserValidationException.class, () -> CreateUserValidator.validate(user));
    }

    @Test
    void shouldReturnUserValidationException_whenUserPwdAndPwdConfirmationInputIsDifferent() {
        user.setPassowrdConfirmation("1234");
        Assertions.assertThrows(UserValidationException.class, () -> CreateUserValidator.validate(user));
    }
}
