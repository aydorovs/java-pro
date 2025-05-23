package org.product.model;

import lombok.Builder;

@Builder
public record UserRequest(String userName) {
}