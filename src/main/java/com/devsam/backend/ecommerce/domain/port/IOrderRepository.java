package com.devsam.backend.ecommerce.domain.port;

import com.devsam.backend.ecommerce.domain.model.Order;

public interface IOrderRepository {

    // Gravar una orden
    Order save(Order order);

    // Buscar una order por su id
    Order findById(Integer id);

    // Mostrar todas las ordenes
    Iterable<Order> findAll();

    // Mostrar todas las ordenes de un usuario, se buscar por el id de usuario
    Iterable<Order> findByUserId(Integer userId);

    // Actualizar el estado de una orden
    void updateStateById(Integer id, String state);
}
