package br.com.example.msscauth.domain.exceptions;

public class UserValidationException extends RuntimeException {

    private final String msg;

    public UserValidationException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
