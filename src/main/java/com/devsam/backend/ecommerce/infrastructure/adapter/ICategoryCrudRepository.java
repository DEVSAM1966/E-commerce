package com.devsam.backend.ecommerce.infrastructure.adapter;

import com.devsam.backend.ecommerce.infrastructure.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryCrudRepository extends CrudRepository<CategoryEntity, Integer> {
}
