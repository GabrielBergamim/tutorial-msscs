package br.com.example.msscusers.domain.usecases.createuser;

import br.com.example.msscusers.domain.dto.UserInputDto;
import br.com.example.msscusers.domain.dto.UserOutputDto;
import br.com.example.msscusers.domain.models.User;
import br.com.example.msscusers.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    @Override
    public UserOutputDto execute(UserInputDto userDto) {
        User user = mapper.map(userDto, User.class);

        user = userRepository.save(user);

        return mapper.map(user, UserOutputDto.class);
    }

}
