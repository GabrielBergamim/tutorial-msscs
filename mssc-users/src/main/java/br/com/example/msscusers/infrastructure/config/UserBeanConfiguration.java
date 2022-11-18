package br.com.example.msscusers.infrastructure.config;

import br.com.example.msscusers.domain.repositories.UserRepository;
import br.com.example.msscusers.domain.usecases.createuser.CreateUserUseCase;
import br.com.example.msscusers.domain.usecases.createuser.CreateUserUseCaseImpl;
import br.com.example.msscusers.infrastructure.repositories.UserJpaRepository;
import br.com.example.msscusers.infrastructure.repositories.UserRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeanConfiguration {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository, ModelMapper mapper) {
        return new CreateUserUseCaseImpl(userRepository, mapper);
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
