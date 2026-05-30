package com.devsam.backend.ecommerce.infrastructure.adapter;

import com.devsam.backend.ecommerce.infrastructure.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserCrudRepository extends CrudRepository<UserEntity, Integer> {
}
