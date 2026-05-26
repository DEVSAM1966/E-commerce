package com.devsam.backend.ecommerce.application;

import com.devsam.backend.ecommerce.domain.model.User;
import com.devsam.backend.ecommerce.domain.port.IUserRepository;

// CRUD de User
public class UserService {
    private final IUserRepository iUserRepository;

    // Constructor de UserService
    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    // Implementación de save()
    public User save(User user) {

        return this.iUserRepository.save(user);
    }

    // Implementación de findById()
    public User findById(Integer id) {

        return this.iUserRepository.findById(id);
    }
}
