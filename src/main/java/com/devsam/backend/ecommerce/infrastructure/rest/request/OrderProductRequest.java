package com.devsam.backend.ecommerce.infrastructure.rest.request;

public record OrderProductRequest(
        Integer productId,
        String quantity,
        String price
) {}
