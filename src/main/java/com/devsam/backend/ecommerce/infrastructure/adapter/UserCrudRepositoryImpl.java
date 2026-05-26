package com.devsam.backend.ecommerce.infrastructure.adapter;

import com.devsam.backend.ecommerce.domain.model.User;
import com.devsam.backend.ecommerce.domain.port.IUserRepository;
import com.devsam.backend.ecommerce.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

// Esta clase conecta infrastructure/adapter con domain/port (para User)
@Repository
public class UserCrudRepositoryImpl implements IUserRepository {

    // Variable que usa la interfaz IUserCrudRepository que extiende CrudRepository
    private final IUserCrudRepository iUserCrudRepository;

    // Variable para usar la clase mapper para User
    private final UserMapper userMapper;

    // Constructor
    public UserCrudRepositoryImpl(IUserCrudRepository iUserCrudRepository, UserMapper userMapper) {
        this.iUserCrudRepository = iUserCrudRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        return userMapper.toUser(iUserCrudRepository.save(userMapper.toUserEntity(user)));
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findById(Integer id) {
        return userMapper.toUser(iUserCrudRepository.findById(id).get());
    }
}
