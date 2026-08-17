package com.devsam.backend.ecommerce.infrastructure.rest.request;

import java.util.List;

public record OrderRequest(
        Integer userId,
        String orderState,
        List<OrderProductRequest> orderProducts
) {}
