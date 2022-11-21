package br.com.example.msscauth.infrastructure.config.beans;

import br.com.example.msscauth.domain.repositories.UserRepository;
import br.com.example.msscauth.domain.usecases.createuser.CreateUserUseCase;
import br.com.example.msscauth.domain.usecases.createuser.CreateUserUseCaseImpl;
import br.com.example.msscauth.domain.usecases.usernotification.UserNotification;
import br.com.example.msscauth.infrastructure.repositories.UserRepositoryImpl;
import br.com.example.msscauth.infrastructure.repositories.UserJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeanConfiguration {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository,
                                               ModelMapper mapper,
                                               UserNotification userNotification) {
        return new CreateUserUseCaseImpl(userRepository, mapper, userNotification);
    }

    @Bean
    public UserRepository userRepository(UserJpaRepository userJPARepository, ModelMapper mapper) {
        return new UserRepositoryImpl(userJPARepository, mapper);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
