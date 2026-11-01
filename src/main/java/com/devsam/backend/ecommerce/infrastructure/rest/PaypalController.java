package com.devsam.backend.ecommerce.infrastructure.rest;

import com.devsam.backend.ecommerce.domain.model.DataPayment;
import com.devsam.backend.ecommerce.infrastructure.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/payments")
@CrossOrigin(origins = "http://localhost:4200")
public class PaypalController {
    private final PaypalService paypalService;
    private final String SUCCESS_URL = "http:localhost:8085/api/v1/payments/success";
    private final String CANCEL_URL = "http:localhost:8085/api/v1/payments/cancel";

    // Método para verificar que los datos recibidos en Frontend son correctos
    @PostMapping
    public String createPayment(@RequestBody DataPayment dataPayment) {
        // Creamos un objeto Payment invocando el constructor con sus parámetros (atención a esta parte)
        try {
            Payment payment = paypalService.createPayment(
                    Double.valueOf(dataPayment.getAmount()),
                    dataPayment.getCurrency(),
                    dataPayment.getMethod(),
                    "SALE",
                    dataPayment.getDescription(),
                    CANCEL_URL,
                    SUCCESS_URL);
            // Verificamos el pago preguntando a Paypal
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        }

        return "";
    }

    // Método para obtener el OK del pago en Paypal
    @GetMapping("/success")
    public RedirectView paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                // Devolvemos a un componente del frontend para indicar el OK del pago
                return new RedirectView("http:localhost:4200/payment/success");
            }
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Método para recoger la cancelación del cliente en el pago
    @GetMapping("/cancel")
    public RedirectView paymentCancel() {
        // Devolvemos al componente inicial del frontend, sin vaciar el carrito.
        return new RedirectView("http:localhost:4200");
    }
}
