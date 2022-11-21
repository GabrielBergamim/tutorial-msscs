package br.com.example.msscauth.domain.utils;

public interface PasswordEncrypt {

    boolean compare(String password, String hashPassword);

    String encrypt(String password);
}
