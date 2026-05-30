package com.devsam.backend.ecommerce.infrastructure.rest;

import com.devsam.backend.ecommerce.application.UserService;
import com.devsam.backend.ecommerce.domain.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    // Variable de tipo UserService
    private final UserService userService;

    // Constructor de UserController
    public UserController(UserService userService) {

        this.userService = userService;
    }

    // Implementación del método save()
    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    // Implementación del método findById()
    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        return userService.findById(id);
    }
}
