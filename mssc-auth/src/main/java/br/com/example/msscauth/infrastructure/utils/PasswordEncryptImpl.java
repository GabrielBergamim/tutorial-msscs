package br.com.example.msscauth.infrastructure.utils;

import br.com.example.msscauth.domain.utils.PasswordEncrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncryptImpl implements PasswordEncrypt {

    private final PasswordEncoder encoder;

    @Override
    public boolean compare(String password, String hashPassword) {
        return encoder.matches(password, hashPassword);
    }

    @Override
    public String encrypt(String password) {
        return encoder.encode(password);
    }
}
