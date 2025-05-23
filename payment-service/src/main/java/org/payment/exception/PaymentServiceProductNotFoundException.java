package org.payment.exception;

public class PaymentServiceProductNotFoundException extends PaymentProcessingException {
    public PaymentServiceProductNotFoundException(String message) {
        super(message);
    }
}