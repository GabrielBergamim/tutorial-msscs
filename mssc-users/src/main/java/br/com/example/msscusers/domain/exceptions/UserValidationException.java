package br.com.example.msscusers.domain.exceptions;

public class UserValidationException extends RuntimeException {

    private final String msg;

    public UserValidationException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
