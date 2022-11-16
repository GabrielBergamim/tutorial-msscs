package br.com.example.msscusers.domain.validators;

import br.com.example.msscusers.domain.dto.UserInputDto;
import br.com.example.msscusers.domain.exceptions.UserValidationException;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class CreateUserValidator {

    private CreateUserValidator() {

    }

    public static void validate(UserInputDto userDto) {
        if (userDto == null) {
            throw new UserValidationException("User should not be null");
        }

        if(isBlank(userDto.getEmail())) {
            throw new UserValidationException("User email should not be null");
        }
    }
}
