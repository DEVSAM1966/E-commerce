package com.devsam.backend.ecommerce.infrastructure.config;

import com.devsam.backend.ecommerce.application.CategoryService;
import com.devsam.backend.ecommerce.application.UserService;
import com.devsam.backend.ecommerce.domain.port.ICategoryRepository;
import com.devsam.backend.ecommerce.domain.port.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public UserService userService(IUserRepository iUserRepository) {
        return new UserService(iUserRepository);
    }

    @Bean
    public CategoryService categoryService(ICategoryRepository iCategoryRepository) {
        return new CategoryService(iCategoryRepository);
    }
}
