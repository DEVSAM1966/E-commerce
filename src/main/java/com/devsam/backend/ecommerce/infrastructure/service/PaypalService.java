package com.devsam.backend.ecommerce.infrastructure.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PaypalService {
    private final APIContext apiContext;

    // Constructor.
    public PaypalService(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    // Metodo para crear el pago con los parametros que solicita Paypal
    public Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl
    ) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(currency);

        // Damos formato de dos decimales
        amount.setTotal(String.format(Locale.forLanguageTag(currency), "%.2f", total));

        // Generamos la transacción de Paypal con el objeto Transaction
        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        // Generamos una lista de Transaction (es lo que recomienda Paypal)
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        // Generamos un objeto de tipo Payer
        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        // Generamos un objeto de tipo Payment para usar nuestra declaracion de payer
        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        // Generamos el objeto RedirectUrls
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setReturnUrl(successUrl);  // Si se aprueba el pago por Paypal
        redirectUrls.setCancelUrl(cancelUrl);   // Si no se aprueba el pago por Paypal

        return payment.create(apiContext);
    }

    // Método executePayment() que es invocado una vez aprobado el pago por Paypal
    // Recibe como parámetro el ID del pago y el ID de la cuenta de quien realiaza el pago.
    public  Payment executePayment(
            String paymentId,
            String payerId
    ) throws PayPalRESTException {

        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        return  payment.execute(apiContext,paymentExecution);
    }

}
