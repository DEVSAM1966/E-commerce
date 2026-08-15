package com.devsam.backend.ecommerce.application;

import com.devsam.backend.ecommerce.domain.model.Order;
import com.devsam.backend.ecommerce.domain.port.IOrderRepository;

public class OrderService {
    // Definimos el uso de una variable de tipo IOrderRepository
    private final IOrderRepository iOrderRepository;

    // Constructor OrderService
    public OrderService(IOrderRepository iOrderRepository) {
        this.iOrderRepository = iOrderRepository;
    }

    // Generamos los métodos que tiene la interface IOrderRepository
    public Order save(Order order) {
        return this.iOrderRepository.save(order);
    }

    public Iterable<Order> findAll() {
        return this.iOrderRepository.findAll();
    }

    public Iterable<Order> findByUserId(Integer userId) {
        return this.iOrderRepository.findByUserId(userId);
    }

    public void updateStateById(Integer id, String state) {
        this.iOrderRepository.updateStateById(id, state);
    }
}
