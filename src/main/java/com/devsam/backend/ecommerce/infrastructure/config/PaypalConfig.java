package com.devsam.backend.ecommerce.infrastructure.config;

import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaypalConfig {
    // Obtenemos los valores de application-dev.properties para usar la API de Paypal
    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    // Creamos un contexto para que estas variables queden disponible para la aplicación.
    @Bean
    public APIContext apiContext() {
        // Usamos el constructor APIContext de la libreria de Paypal
        return new APIContext(clientId, clientSecret, mode);
    }
}
