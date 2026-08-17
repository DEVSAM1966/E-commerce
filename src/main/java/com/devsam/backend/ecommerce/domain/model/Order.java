package com.devsam.backend.ecommerce.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private Integer id;
    private LocalDateTime dateCreated;
    private List<OrderProduct> orderProducts;
    private OrderState orderState;
    private Integer userId;

    // Constructor de ordenProducts sin parámetros para crear la lista
    public Order() {
        orderProducts = new ArrayList<>();
    }

    // Función para calcular el valor de cada ordenProducts que tenga
    // la cabecera Order
    public BigDecimal getTotalOrderPrice() {
        return this.orderProducts.stream().map(
                ordenProduct -> ordenProduct.getTotalItem() )
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }
}
