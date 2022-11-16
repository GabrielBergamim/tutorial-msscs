package br.com.example.msscusers.domain.repositories;

import br.com.example.msscusers.domain.models.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByEmail(String email);
}
