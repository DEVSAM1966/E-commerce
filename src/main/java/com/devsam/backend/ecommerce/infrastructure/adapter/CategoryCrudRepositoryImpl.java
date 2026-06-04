package com.devsam.backend.ecommerce.infrastructure.adapter;

import com.devsam.backend.ecommerce.domain.model.Category;
import com.devsam.backend.ecommerce.domain.port.ICategoryRepository;
import com.devsam.backend.ecommerce.infrastructure.mapper.CategoryMapper;
import org.springframework.stereotype.Repository;

// Esta clase conecta infrastructure/adapter con domain/port (para Category)
@Repository
public class CategoryCrudRepositoryImpl implements ICategoryRepository {

    // Variable que usa la interfaz ICategoryCrudRepository que extiende CrudRepository
    private final ICategoryCrudRepository iCategoryCrudRepository;

    // Variable para usar CategoryMapper
    private final CategoryMapper categoryMapper;

    // Constructor
    public CategoryCrudRepositoryImpl(ICategoryCrudRepository iCategoryCrudRepository, CategoryMapper categoryMapper) {
        this.iCategoryCrudRepository = iCategoryCrudRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category save(Category category) {

        return categoryMapper.toCategory(iCategoryCrudRepository.save(categoryMapper.toCategoryEntity(category)));
    }

    @Override
    public Iterable<Category> findAll() {

        return categoryMapper.toCategoryList(iCategoryCrudRepository.findAll());
    }

    @Override
    public Category findById(Integer id) {

        return categoryMapper.toCategory(iCategoryCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Categoría con ID: " + id + " no existe")
        ));
    }

    @Override
    public void deleteById(Integer id) {
        iCategoryCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Categoría con ID: " + id + " no existe")
        );
        iCategoryCrudRepository.deleteById(id);
    }
}
