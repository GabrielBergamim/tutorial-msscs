package br.com.example.msscauth.application.adapters;

public interface PasswordEncrypt {

    boolean compare(String password, String hashPassword);

    String encrypt(String password);
}
