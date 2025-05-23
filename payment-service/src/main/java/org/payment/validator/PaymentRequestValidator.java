package org.payment.validator;

import org.payment.exception.PaymentValidationException;
import org.payment.model.PaymentRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentRequestValidator {

    public boolean validate(PaymentRequest paymentRequest) {
        if (paymentRequest == null) {
            throw new PaymentValidationException("missing paymentRequest");
        } else if (paymentRequest.productId() == null) {
            throw new PaymentValidationException("missing paymentRequest.productId");
        } else if (paymentRequest.amount() == null) {
            throw new PaymentValidationException("missing paymentRequest.amount");
        } else if (paymentRequest.amount().compareTo(BigDecimal.ZERO) < 0
                || paymentRequest.amount().compareTo(BigDecimal.ZERO) == 0) {
            throw new PaymentValidationException("paymentRequest.amount needs to be greater than zero");
        }

        return true;
    }
}