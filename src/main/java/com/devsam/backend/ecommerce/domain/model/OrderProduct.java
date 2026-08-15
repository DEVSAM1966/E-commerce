package com.devsam.backend.ecommerce.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderProduct {
    private Integer id;
    private BigDecimal quantity;
    private BigDecimal price;
    private Integer productId;

    // Función para calcular price * quantity
    public BigDecimal getTotalItem() {
        return this.price.multiply(quantity);
    }
}
