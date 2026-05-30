package com.devsam.backend.ecommerce.infrastructure.adapter;

import com.devsam.backend.ecommerce.domain.model.Category;
import com.devsam.backend.ecommerce.domain.port.ICategoryRepository;
import org.springframework.stereotype.Repository;

// Esta clase conecta infrastructure/adapter con domain/port (para Category)
@Repository
public class CategoryCrudRepositoryImpl implements ICategoryRepository {

    // Variable que usa la interfaz ICategoryCrudRepository que extiende CrudRepository
    private final ICategoryCrudRepository iCategoryCrudRepository;

    // Constructor
    public CategoryCrudRepositoryImpl(ICategoryCrudRepository iCategoryCrudRepository) {
        this.iCategoryCrudRepository = iCategoryCrudRepository;
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public Iterable<Category> findAll() {
        return null;
    }

    @Override
    public Category findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
