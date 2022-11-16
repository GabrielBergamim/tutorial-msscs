package br.com.example.msscusers.domain.usecases.createuser;

import br.com.example.msscusers.domain.dto.UserInputDto;
import br.com.example.msscusers.domain.dto.UserOutputDto;
import br.com.example.msscusers.domain.exceptions.UserValidationException;
import br.com.example.msscusers.domain.models.User;
import br.com.example.msscusers.domain.repositories.UserRepository;
import br.com.example.msscusers.domain.validators.CreateUserValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    @Override
    public UserOutputDto execute(UserInputDto userDto) {
        CreateUserValidator.validate(userDto);

        validateIfEmailAlreadyExists(userDto.getEmail());

        User user = mapper.map(userDto, User.class);

        user = userRepository.save(user);

        return mapper.map(user, UserOutputDto.class);
    }

    private void validateIfEmailAlreadyExists(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new UserValidationException("Email already exists");
        });
    }

}
