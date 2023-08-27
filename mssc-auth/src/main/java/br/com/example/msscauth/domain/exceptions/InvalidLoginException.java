package br.com.example.msscauth.domain.exceptions;

public class InvalidLoginException extends RuntimeException {

    public InvalidLoginException() {
        super("Invalid login credentials");
    }
}
