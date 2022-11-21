package br.com.example.msscauth.domain.usecases.createuser;

import br.com.example.msscauth.domain.dto.UserInputDto;
import br.com.example.msscauth.domain.dto.UserOutputDto;

public interface CreateUserUseCase {

    UserOutputDto execute(UserInputDto user);
}
