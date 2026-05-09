package com.mchiir.starterkit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Handle validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                buildError(
                        HttpStatus.BAD_REQUEST,
                        "Validation failed",
                        request.getRequestURI(),
                        errors
                )
        );
    }

    // 2. Handle runtime exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(
            RuntimeException ex,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                buildError(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getMessage(),
                        request.getRequestURI(),
                        null
                )
        );
    }

    // 3. Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(
            Exception ex,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                buildError(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Unexpected error occurred",
                        request.getRequestURI(),
                        null
                )
        );
    }

    // Shared error response builder
    private Map<String, Object> buildError(
            HttpStatus status,
            String message,
            String path,
            Object errors
    ) {
        Map<String, Object> error = new HashMap<>();

        error.put("timestamp", LocalDateTime.now());
        error.put("status", status.value());
        error.put("error", status.getReasonPhrase());
        error.put("message", message);
        error.put("path", path);

        if (errors != null) {
            error.put("details", errors);
        }

        return error;
    }
}
