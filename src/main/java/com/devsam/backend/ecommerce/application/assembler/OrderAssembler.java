package com.devsam.backend.ecommerce.application.assembler;

import com.devsam.backend.ecommerce.domain.model.Order;
import com.devsam.backend.ecommerce.domain.model.OrderProduct;
import com.devsam.backend.ecommerce.domain.model.OrderState;
import com.devsam.backend.ecommerce.infrastructure.rest.request.OrderProductRequest;
import com.devsam.backend.ecommerce.infrastructure.rest.request.OrderRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderAssembler {

    public Order toDomain(OrderRequest request) {
        Order order = new Order();
        order.setUserId(request.userId());
        order.setOrderState(OrderState.valueOf(request.orderState()));

        List<OrderProduct> products = request.orderProducts().stream()
                .map(this::toDomain)
                .toList();

        order.setOrderProducts(products);

        return order;
    }

    public OrderProduct toDomain(OrderProductRequest request) {
        OrderProduct op = new OrderProduct();
        op.setProductId(request.productId());
        op.setQuantity(new BigDecimal(request.quantity()));
        op.setPrice(new BigDecimal(request.price()));
        return op;
    }
}

