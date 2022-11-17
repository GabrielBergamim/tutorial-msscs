package br.com.example.msscusers.infrastructure.repositories;

import br.com.example.msscusers.domain.models.User;
import br.com.example.msscusers.domain.repositories.UserRepository;
import br.com.example.msscusers.infrastructure.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    private final ModelMapper mapper;

    @Override
    public User save(User user) {
        UserEntity userEntity = mapper.map(user, UserEntity.class);
        userEntity = jpaRepository.save(userEntity);
        return mapper.map(userEntity, User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(userEntity -> mapper.map(userEntity, User.class));
    }
}
