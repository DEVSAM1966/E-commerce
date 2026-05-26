package com.devsam.backend.ecommerce.application;

import com.devsam.backend.ecommerce.domain.model.Category;
import com.devsam.backend.ecommerce.domain.port.ICategoryRepository;

// CRUD de Category
public class CategoryService {
    private final ICategoryRepository iCategoryRepository;

    // Constructor de CategoryService
    public CategoryService(ICategoryRepository iCategoryRepository) {

        this.iCategoryRepository = iCategoryRepository;
    }

    // Implementación de save()
    public Category save(Category category){

        return this.iCategoryRepository.save(category);
    }

    // Implementación de findAll()
    public Iterable<Category> findAll(){

        return this.iCategoryRepository.findAll();
    }

    // Implementación findById()
    public Category findById(Integer id){

        return this.iCategoryRepository.findById(id);
    }

    // Implementación deleteById()
    public void deleteById(Integer id){

        this.iCategoryRepository.deleteById(id);
    }

}
