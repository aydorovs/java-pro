package org.payment.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.payment.model.ErrorItem;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PaymentServiceInsufficientFundsException.class)
    public ErrorItem handleException(HttpServletResponse response, PaymentServiceInsufficientFundsException e) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ErrorItem(
                400,
                OffsetDateTime.now(),
                e.getMessage(),
                "Insufficient funds."
        );
    }

    @ExceptionHandler(PaymentServiceProductNotFoundException.class)
    public ErrorItem handleException(HttpServletResponse response, PaymentServiceProductNotFoundException e) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return new ErrorItem(
                404,
                OffsetDateTime.now(),
                e.getMessage(),
                "Product not found."
        );
    }

    @ExceptionHandler(PaymentServiceUserProductsNotFoundException.class)
    public ErrorItem handleException(HttpServletResponse response, PaymentServiceUserProductsNotFoundException e) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return new ErrorItem(
                404,
                OffsetDateTime.now(),
                e.getMessage(),
                "Product not found."
        );
    }

    @ExceptionHandler(PaymentValidationException.class)
    public ErrorItem handleException(HttpServletResponse response, PaymentValidationException e) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ErrorItem(
                400,
                OffsetDateTime.now(),
                e.getMessage(),
                "Payment request failed."
        );
    }

    @ExceptionHandler(PaymentProcessingException.class)
    public ErrorItem handleException(HttpServletResponse response, PaymentProcessingException e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ErrorItem(
                500,
                OffsetDateTime.now(),
                e.getMessage(),
                "Payment service error."
        );
    }

    @ExceptionHandler(ProductServiceInternalErrorException.class)
    public ErrorItem handleException(HttpServletResponse response, ProductServiceInternalErrorException e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ErrorItem(
                500,
                OffsetDateTime.now(),
                e.getMessage(),
                "Product service error."
        );
    }

    @ExceptionHandler(Exception.class)
    public ErrorItem handleException(Exception e) {
        return new ErrorItem(
                500,
                OffsetDateTime.now(),
                e.getMessage(),
                "payment-service error."
        );
    }
}