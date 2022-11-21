package br.com.example.msscauth.domain.usecases.createuser;

import br.com.example.msscauth.domain.dto.user.UserInputDto;
import br.com.example.msscauth.domain.dto.user.UserOutputDto;

public interface CreateUserUseCase {

    UserOutputDto execute(UserInputDto user);
}
