package br.com.example.msscusers.domain.usecases.createuser;

import br.com.example.msscusers.domain.dto.UserInputDto;
import br.com.example.msscusers.domain.dto.UserOutputDto;

public interface CreateUserUseCase {

    UserOutputDto execute(UserInputDto user);
}
