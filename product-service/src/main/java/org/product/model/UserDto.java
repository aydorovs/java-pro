package org.product.model;

import lombok.Builder;

@Builder
public record UserDto(Long id, String userName) {
}