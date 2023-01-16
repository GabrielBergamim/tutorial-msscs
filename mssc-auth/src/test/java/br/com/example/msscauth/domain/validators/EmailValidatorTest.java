package br.com.example.msscauth.domain.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailValidatorTest {

    @Test
    void shouldReturnFalse_whenEmailIsNull() {
        Assertions.assertFalse(EmailValidator.isValid(null));
    }

    @Test
    void shouldReturnFalse_whenEmailIsEmpty() {
        Assertions.assertFalse(EmailValidator.isValid(""));
    }

    @Test
    void shouldReturnFalse_whenEmailIsInvalid() {
        String invalidEmail = "teste2email.com";
        Assertions.assertFalse(EmailValidator.isValid(invalidEmail));
    }

    @Test
    void shouldReturnTrue_whenEmailIsValid() {
        String validEmail = "teste@email.com";
        Assertions.assertTrue(EmailValidator.isValid(validEmail));
    }
}
