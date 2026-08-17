package com.devsam.backend.ecommerce.infrastructure.adapter;

import com.devsam.backend.ecommerce.domain.model.OrderState;
import com.devsam.backend.ecommerce.infrastructure.entity.OrderEntity;
import com.devsam.backend.ecommerce.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface IOrderCrudRepository extends CrudRepository<OrderEntity, Integer> {

    // Método para cambiar el estado de una orden obtenida por su id
    // Usamos JPSQL para implementar el método
    @Transactional
    @Modifying
    @Query("UPDATE OrderEntity o SET o.orderState = :state WHERE o.id = :id")
    void updateStateById(Integer id, OrderState state);

    // Método para obtener todas las ordenes de un usuario.
    Iterable<OrderEntity> findByUserEntity(UserEntity userEntity);

}
