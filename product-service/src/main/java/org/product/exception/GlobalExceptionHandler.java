package org.product.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.product.model.ErrorItem;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorItem handleException(HttpServletResponse response, EntityNotFoundException e) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return new ErrorItem(
                404,
                OffsetDateTime.now(),
                e.getMessage(),
                "Entity not found."
        );
    }

    @ExceptionHandler(Exception.class)
    public ErrorItem handleException(Exception e) {
        return new ErrorItem(
                500,
                OffsetDateTime.now(),
                e.getMessage(),
                "product-service error."
        );
    }
}
