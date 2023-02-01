package br.com.example.msscauth.infrastructure.config.beans;

import br.com.example.msscauth.domain.repositories.UserRepository;
import br.com.example.msscauth.domain.usecases.auth.AuthUseCase;
import br.com.example.msscauth.domain.usecases.auth.AuthUseCaseImpl;
import br.com.example.msscauth.domain.utils.JWTUtils;
import br.com.example.msscauth.domain.utils.PasswordEncrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginBeanConfig {

    @Bean
    public AuthUseCase authUseCase(UserRepository userRepository,
                                   PasswordEncrypt passwordEncrypt,
                                   JWTUtils jwtUtils) {
        return new AuthUseCaseImpl(userRepository, passwordEncrypt, jwtUtils);
    }

}
