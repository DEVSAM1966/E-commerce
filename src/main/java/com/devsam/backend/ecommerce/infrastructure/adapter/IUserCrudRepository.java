package com.devsam.backend.ecommerce.infrastructure.adapter;

import com.devsam.backend.ecommerce.infrastructure.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserCrudRepository extends CrudRepository<UserEntity, Integer> {
}
