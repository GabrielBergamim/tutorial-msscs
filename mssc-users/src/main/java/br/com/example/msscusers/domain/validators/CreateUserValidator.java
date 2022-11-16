package br.com.example.msscusers.domain.validators;

import br.com.example.msscusers.domain.dto.UserInputDto;
import br.com.example.msscusers.domain.exceptions.UserValidationException;

import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class CreateUserValidator {

    private static final String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private CreateUserValidator() { }

    public static void validate(UserInputDto userDto) {
        if (userDto == null) {
            throw new UserValidationException("User should not be null");
        }

        if(isBlank(userDto.getEmail())) {
            throw new UserValidationException("User email should not be empty");
        }

        if(!Pattern.compile(emailRegex)
                .matcher(userDto.getEmail())
                .matches()) {
            throw new UserValidationException("User email is not valid");
        }

        if(isBlank(userDto.getPassword())) {
            throw new UserValidationException("User password should not be empty");
        }
    }
}
