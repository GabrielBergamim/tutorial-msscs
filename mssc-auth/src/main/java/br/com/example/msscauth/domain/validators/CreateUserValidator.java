package br.com.example.msscauth.domain.validators;

import br.com.example.msscauth.domain.dto.user.UserInputDto;
import br.com.example.msscauth.domain.exceptions.UserValidationException;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class CreateUserValidator {

    private CreateUserValidator() { }

    public static void validate(UserInputDto userDto) {
        if (userDto == null) {
            throw new UserValidationException("User should not be null");
        }

        if(isBlank(userDto.getEmail())) {
            throw new UserValidationException("User email should not be empty");
        }

        if(!EmailValidator.isValid(userDto.getEmail())) {
            throw new UserValidationException("User email is not valid");
        }

        if(isBlank(userDto.getPassword())) {
            throw new UserValidationException("User password should not be empty");
        }

        if(isBlank(userDto.getPasswordConfirmation())) {
            throw new UserValidationException("User password confirmation should not be empty");
        }

        if(!userDto.getPassword().equals(userDto.getPasswordConfirmation())) {
            throw new UserValidationException("User password and password confirmation must be equals");
        }
    }
}
