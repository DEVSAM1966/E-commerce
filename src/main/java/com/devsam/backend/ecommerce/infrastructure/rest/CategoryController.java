package com.devsam.backend.ecommerce.infrastructure.rest;

import com.devsam.backend.ecommerce.application.CategoryService;
import com.devsam.backend.ecommerce.domain.model.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/categories")
@Slf4j
public class CategoryController {

    // Variable para usar el service
    private final CategoryService categoryService;

    // Constructor
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Implementar el método save()
    @PostMapping
    public Category save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    // Implementar el método findAll()
    @GetMapping
    public Iterable<Category> findAll() {
        return categoryService.findAll();
    }

    // Implementar el método findById()
    @GetMapping("/{id}")
    public Category findById(@PathVariable Integer id) {
        return categoryService.findById(id);
    }

    // Implementar el método deleteById()
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        categoryService.deleteById(id);
    }

}
