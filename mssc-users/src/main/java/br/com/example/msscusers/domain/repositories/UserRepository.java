package br.com.example.msscusers.domain.repositories;

import br.com.example.msscusers.domain.models.User;

public interface UserRepository {

    User save(User user);
}
