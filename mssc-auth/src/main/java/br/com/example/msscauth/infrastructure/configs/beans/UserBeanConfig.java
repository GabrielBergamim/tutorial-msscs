package br.com.example.msscauth.infrastructure.configs.beans;

import br.com.example.msscauth.application.adapters.repositories.UserRepository;
import br.com.example.msscauth.domain.usecases.createuser.CreateUserUseCase;
import br.com.example.msscauth.domain.usecases.createuser.CreateUserUseCaseImpl;
import br.com.example.msscauth.domain.usecases.usernotification.UserNotification;
import br.com.example.msscauth.application.adapters.PasswordEncrypt;
import br.com.example.msscauth.infrastructure.repositories.UserJpaRepository;
import br.com.example.msscauth.infrastructure.repositories.UserRepositoryImpl;
import br.com.example.msscauth.infrastructure.utils.PasswordEncryptImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserBeanConfig {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository,
                                               ModelMapper mapper,
                                               UserNotification userNotification,
                                               PasswordEncrypt passwordEncrypt) {
        return new CreateUserUseCaseImpl(userRepository, mapper, userNotification, passwordEncrypt);
    }

    @Bean
    public UserRepository userRepository(UserJpaRepository userJPARepository, ModelMapper mapper) {
        return new UserRepositoryImpl(userJPARepository, mapper);
    }

    @Bean
    public PasswordEncrypt passwordEncrypt(PasswordEncoder encoder) {
        return new PasswordEncryptImpl(encoder);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
