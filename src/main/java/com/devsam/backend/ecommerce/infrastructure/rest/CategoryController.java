package com.devsam.backend.ecommerce.infrastructure.rest;

import com.devsam.backend.ecommerce.application.CategoryService;
import com.devsam.backend.ecommerce.domain.model.Category;
import com.devsam.backend.ecommerce.domain.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/admin/categories")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    // Variable para usar el service
    private final CategoryService categoryService;

    // Constructor
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Implementar el método save()
    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category)  throws IOException {

        if (category.getId() == null || category.getId() == 0) {
            category.setId(null); // CREATE
        }

        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    // Implementar el método findAll()
    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    // Implementar el método findById()
    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id) {

        return ResponseEntity.ok(categoryService.findById(id));
    }

    // Implementar el método deleteById()
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id) {

        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
