package br.com.example.msscauth.domain.exceptions;

public class MissingParameterException extends RuntimeException {

    private final String field;

    public MissingParameterException(String field) {
        super("Missing parameter: " + field);
        this.field = field;
    }
}
