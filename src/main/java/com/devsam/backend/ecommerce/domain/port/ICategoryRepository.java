package com.devsam.backend.ecommerce.domain.port;

import com.devsam.backend.ecommerce.domain.model.Category;

public interface ICategoryRepository {
    Category save(Category category);
    Iterable<Category> findAll();
    Category findById(Integer id);
    void deleteById(Integer id);
}
