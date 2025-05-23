package org.payment.model;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String accountNumber,
        BigDecimal balance,
        String productType,
        Long userId
) {
}