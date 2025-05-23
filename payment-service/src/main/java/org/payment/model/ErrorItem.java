package org.payment.model;

import java.time.OffsetDateTime;

public record ErrorItem(
        int httpCode,
        OffsetDateTime offsetDateTime,
        String errorDetails,
        String message
) {
}