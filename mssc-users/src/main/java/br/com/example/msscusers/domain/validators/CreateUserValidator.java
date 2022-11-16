package br.com.example.msscusers.domain.validators;

import br.com.example.msscusers.domain.dto.UserInputDto;
import br.com.example.msscusers.domain.exceptions.UserValidationException;

public class CreateUserValidator {

    private CreateUserValidator() {

    }

    public static void validate(UserInputDto userDto) {
        if (userDto == null) {
            throw new UserValidationException("User should not be null");
        }
    }
}
