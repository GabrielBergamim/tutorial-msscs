package br.com.example.msscauth.domain.usecases.createuser;

import br.com.example.msscauth.application.adapters.repositories.UserRepository;
import br.com.example.msscauth.domain.dto.user.UserInputDto;
import br.com.example.msscauth.domain.dto.user.UserOutputDto;
import br.com.example.msscauth.domain.exceptions.UserValidationException;
import br.com.example.msscauth.domain.models.Notification;
import br.com.example.msscauth.domain.models.User;
import br.com.example.msscauth.domain.usecases.usernotification.UserNotification;
import br.com.example.msscauth.application.adapters.PasswordEncrypt;
import br.com.example.msscauth.domain.validators.CreateUserValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    private final UserNotification userNotification;

    private final PasswordEncrypt passwordEncrypt;

    @Override
    public UserOutputDto execute(UserInputDto userDto) {
        CreateUserValidator.validate(userDto);

        validateIfEmailAlreadyExists(userDto.getEmail());

        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncrypt.encrypt(user.getPassword()));

        user = userRepository.save(user);

        sendNotification(user);

        return mapper.map(user, UserOutputDto.class);
    }

    private void sendNotification(User user) {
        userNotification.execute(Notification.builder()
                .to(user.getEmail())
                .subject("User created")
                .message("Your user has been successfully created!")
                .build());
    }

    private void validateIfEmailAlreadyExists(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new UserValidationException("Email already exists");
        });
    }

}
