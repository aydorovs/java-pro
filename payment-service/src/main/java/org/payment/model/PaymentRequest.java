package org.payment.model;

import java.math.BigDecimal;

public record PaymentRequest(
        Long productId,
        BigDecimal amount,
        String description
) {
}