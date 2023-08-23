package br.com.example.msscauth.infrastructure.config.beans;

import br.com.example.msscauth.domain.repositories.UserRepository;
import br.com.example.msscauth.domain.usecases.auth.AuthManager;
import br.com.example.msscauth.domain.usecases.auth.AuthUseCase;
import br.com.example.msscauth.domain.usecases.auth.AuthUseCaseImpl;
import br.com.example.msscauth.domain.utils.JWTUtils;
import br.com.example.msscauth.domain.utils.PasswordEncrypt;
import br.com.example.msscauth.infrastructure.services.AuthManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class LoginBeanConfig {

    @Bean
    public AuthUseCase authUseCase(UserRepository userRepository,
                                   PasswordEncrypt passwordEncrypt,
                                   JWTUtils jwtUtils,
                                   AuthManager manager) {
        return new AuthUseCaseImpl(userRepository, passwordEncrypt, jwtUtils, manager);
    }

    @Bean
    public AuthManager authManager(AuthenticationManager authenticationManager) {
        return new AuthManagerImpl(authenticationManager);
    }
}
