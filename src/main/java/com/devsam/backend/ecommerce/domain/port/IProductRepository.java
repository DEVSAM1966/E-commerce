package com.devsam.backend.ecommerce.domain.port;

import com.devsam.backend.ecommerce.domain.model.Product;

public interface IProductRepository {
    Product save(Product product);
    Iterable<Product> findAll();
    Product findById(Integer id);
    void deleteById(Integer id);
}
