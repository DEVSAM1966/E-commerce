package com.devsam.backend.ecommerce.application;

import com.devsam.backend.ecommerce.domain.model.Product;
import com.devsam.backend.ecommerce.domain.port.IProductRepository;

// CRUD de Product
public class ProductService {
    private final IProductRepository iProductRepository;

    // Constructor de ProductService
    public ProductService(IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }

    // Implementación de save()
    public Product save(Product product) {

        return this.iProductRepository.save(product);
    }

    // Implementacón de findAll()
    public Iterable<Product> findAll() {

        return this.iProductRepository.findAll();
    }

    // Implementación de findById()
    public Product findById(Integer id) {

        return this.iProductRepository.findById(id);
    }

    // Implementación de deleteById()
    public void deleteById(Integer id) {

        this.iProductRepository.deleteById(id);
    }

}
