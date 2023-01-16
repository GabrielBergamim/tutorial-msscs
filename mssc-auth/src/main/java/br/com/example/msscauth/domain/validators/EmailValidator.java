package br.com.example.msscauth.domain.validators;

import java.util.Optional;
import java.util.regex.Pattern;

public class EmailValidator {

    private static final String emailRegex = "^(?=.{1,64}@)[A-Za-z\\d_-]+(\\.[A-Za-z\\d_-]+)*@"
            + "[^-][A-Za-z\\d-]+(\\.[A-Za-z\\d-]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isValid(String email) {
        return Optional.ofNullable(email)
                .map(mail -> Pattern.compile(emailRegex)
                        .matcher(mail)
                        .matches())
                .orElse(false);
    }
}
