package br.com.example.msscauth.infrastructure.config.beans;

import br.com.example.msscauth.domain.repositories.UserRepository;
import br.com.example.msscauth.domain.usecases.auth.AuthUseCase;
import br.com.example.msscauth.domain.usecases.auth.AuthUseCaseImpl;
import br.com.example.msscauth.domain.utils.JWTUtils;
import br.com.example.msscauth.domain.utils.PasswordEncrypt;
import br.com.example.msscauth.infrastructure.utils.JWTUtilsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginBeanConfiguration {

    @Bean
    public AuthUseCase authUseCase(UserRepository userRepository,
                                   PasswordEncrypt passwordEncrypt,
                                   JWTUtils jwtUtils) {
        return new AuthUseCaseImpl(userRepository, passwordEncrypt, jwtUtils);
    }

    @Bean
    public JWTUtils jwtUtils(@Value("${jwt.secret}") String secret,
                             @Value("${jwt.expiration}") Long expiration) {
        return new JWTUtilsImpl(secret, expiration);
    }
}
