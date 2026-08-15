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
    private List<OrderProduct> ordenProducts;
    private OrderState orderState;
    private Integer userId;

    // Constructor de ordenProducts sin parámetros para crear la lista
    public Order() {
        ordenProducts = new ArrayList<>();
    }

    // Función para calcular el valor de cada ordenProducts que tenga
    // la cabecera Order
    public BigDecimal getTotalOrderPrice() {
        return this.ordenProducts.stream().map(
                ordenProduct -> ordenProduct.getTotalItem() )
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }
}
