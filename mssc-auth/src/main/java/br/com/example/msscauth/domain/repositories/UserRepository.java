package br.com.example.msscauth.domain.repositories;

import br.com.example.msscauth.domain.models.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByEmail(String email);
}
