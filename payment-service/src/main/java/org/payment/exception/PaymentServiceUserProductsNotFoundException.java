package org.payment.exception;

public class PaymentServiceUserProductsNotFoundException extends RuntimeException {
    public PaymentServiceUserProductsNotFoundException(String message) {
        super(message);
    }
}