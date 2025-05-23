package org.payment.exception;

public class ProductServiceInternalErrorException extends RuntimeException {
    public ProductServiceInternalErrorException(String message) {
        super(message);
    }
}