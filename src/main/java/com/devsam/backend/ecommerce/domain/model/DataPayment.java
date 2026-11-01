package com.devsam.backend.ecommerce.domain.model;
import lombok.Data;

// Clase que contiene información sobre el pago en Paypal
@Data
public class DataPayment {
    private String method;
    private String amount;
    private String currency;
    private String description;
}
