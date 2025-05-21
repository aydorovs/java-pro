package org.lessons.lesson4.model;

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