package org.payment.exception;

public class PaymentServiceInsufficientFundsException extends PaymentProcessingException {
    public PaymentServiceInsufficientFundsException(String message) {
        super(message);
    }
}