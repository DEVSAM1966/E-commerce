package com.devsam.backend.ecommerce.infrastructure.adapter;

import com.devsam.backend.ecommerce.domain.model.Product;
import com.devsam.backend.ecommerce.domain.port.IProductRepository;

import com.devsam.backend.ecommerce.infrastructure.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// Esta clase conecta infrastructure/adapter con domain/port (para Product)
@Repository
public class ProductCrudRepositoryImpl implements IProductRepository {

    // Variable que usa la interfaz IProductCrudRepository que extiende CrudRepository
    private final IProductCrudRepository iProductCrudRepository;

    // Variable que usa ProductMapper
    private final ProductMapper productMapper;

    // Constructor
    public ProductCrudRepositoryImpl(IProductCrudRepository iProductCrudRepository, ProductMapper productMapper) {
        this.iProductCrudRepository = iProductCrudRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product save(Product product) {

        return productMapper.toProduct(iProductCrudRepository.save(productMapper.toProductEntity(product)));
    }

    public Iterable<Product> findAll() {

        return productMapper.toProductList(iProductCrudRepository.findAll());
    }

    public Product findById(Integer id) {
        return productMapper.toProduct(iProductCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Producto con id: " + id + " no se encuentra")
        ));
    }

    public void deleteById(Integer id) {
        iProductCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Producto con id: " + id + " no se encuentra")
        );
        iProductCrudRepository.deleteById(id);
    }

}
