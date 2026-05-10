// java
package com.mchiir.starterkit.exception;

import lombok.Builder;
import lombok.Value;
import lombok.NonNull;

import java.time.LocalDateTime;

@Value
@Builder
public class ErrorResponse {
    @Builder.Default
    LocalDateTime timestamp = LocalDateTime.now();
    int status;
    @NonNull
    String code;
    @NonNull
    String message;
    String path;
    Object details;
}