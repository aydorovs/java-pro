package org.lessons.lesson4.model;

import lombok.Builder;

@Builder
public record UserRequest(String userName) {
}