package br.com.example.msscauth.domain.usecases.auth;

import br.com.example.msscauth.domain.exceptions.InvalidLoginException;
import br.com.example.msscauth.domain.exceptions.MissingParameterException;
import br.com.example.msscauth.domain.models.AuthorizationToken;
import br.com.example.msscauth.domain.models.User;
import br.com.example.msscauth.domain.repositories.UserRepository;
import br.com.example.msscauth.domain.utils.PasswordEncrypt;
import lombok.RequiredArgsConstructor;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RequiredArgsConstructor
public class AuthUseCaseImpl implements AuthUseCase {

    private final UserRepository userRepository;

    private final PasswordEncrypt passwordEncrypt;

    @Override
    public AuthorizationToken execute(String email, String password) {
        if(isBlank(email)) {
            throw new MissingParameterException("email");
        }

        if(isBlank(password)) {
            throw new MissingParameterException("password");
        }

        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            throw new InvalidLoginException();
        });

        if(!passwordEncrypt.compare(password, user.getPassword())) {
            throw new InvalidLoginException();
        }

        return null;
    }
}
