package com.mchiir.starterkit.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest req
    ) {

        Map<String, String> fieldErrors = new HashMap<>();

        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fe.getField(), fe.getDefaultMessage());
        }

        log.warn(
                "Validation failed for request [{}]: {}",
                req.getRequestURI(),
                fieldErrors
        );

        ErrorResponse body = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code("VALIDATION_ERROR")
                .message("Validation failed")
                .path(req.getRequestURI())
                .details(fieldErrors)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMalformedJson(
            HttpMessageNotReadableException ex,
            HttpServletRequest req
    ) {

        log.warn(
                "Malformed JSON request at [{}]: {}",
                req.getRequestURI(),
                ex.getMostSpecificCause().getMessage()
        );

        ErrorResponse body = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code("MALFORMED_JSON")
                .message("Invalid JSON request body")
                .path(req.getRequestURI())
                .details(null)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest req
    ) {

        log.warn(
                "Resource not found at [{}]: {}",
                req.getRequestURI(),
                ex.getMessage()
        );

        ErrorResponse body = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .code("NOT_FOUND")
                .message(ex.getMessage())
                .path(req.getRequestURI())
                .details(null)
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(body);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(
            BusinessException ex,
            HttpServletRequest req
    ) {

        log.warn(
                "Business exception at [{}]: {}",
                req.getRequestURI(),
                ex.getMessage()
        );

        ErrorResponse body = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code("BUSINESS_ERROR")
                .message(ex.getMessage())
                .path(req.getRequestURI())
                .details(null)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthentication(
            AuthenticationException ex,
            HttpServletRequest req
    ) {

        log.warn(
                "Authentication failed at [{}]: {}",
                req.getRequestURI(),
                ex.getMessage()
        );

        ErrorResponse body = ErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .code("UNAUTHORIZED")
                .message(ex.getMessage())
                .path(req.getRequestURI())
                .details(null)
                .build();

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(body);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(
            AccessDeniedException ex,
            HttpServletRequest req
    ) {

        log.warn(
                "Access denied at [{}]: {}",
                req.getRequestURI(),
                ex.getMessage()
        );

        ErrorResponse body = ErrorResponse.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .code("ACCESS_DENIED")
                .message("Access is denied")
                .path(req.getRequestURI())
                .details(null)
                .build();

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception ex,
            HttpServletRequest req
    ) {

        log.error(
                "Unexpected server error at [{}]",
                req.getRequestURI(),
                ex
        );

        ErrorResponse body = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code("SERVER_ERROR")
                .message("An unexpected error occurred")
                .path(req.getRequestURI())
                .details(null)
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(body);
    }
}