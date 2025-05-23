package org.product.model;

import lombok.Builder;

@Builder
public record ProductDto(
        Long id,
        String accountNumber,
        Double balance,
        String productType,
        Long userId
) {
}