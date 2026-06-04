package com.devsam.backend.ecommerce.infrastructure.adapter;

import com.devsam.backend.ecommerce.infrastructure.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface IProductCrudRepository extends CrudRepository<ProductEntity, Integer> {

}
