package org.idea.hackaton1.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record ErrorResponse(
        String error,
        String message,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        LocalDateTime timestamp,
        String path
) {
    // Factory method principal
    public static ErrorResponse of(String error, String message, String path) {
        return new ErrorResponse(
                error,
                message,
                LocalDateTime.now(),
                path
        );
    }

    // Factory methods para errores comunes HTTP
    public static ErrorResponse badRequest(String message, String path) {
        return of("BAD_REQUEST", message, path);
    }

    public static ErrorResponse unauthorized(String message, String path) {
        return of("UNAUTHORIZED", message, path);
    }

    public static ErrorResponse forbidden(String message, String path) {
        return of("FORBIDDEN", message, path);
    }

    public static ErrorResponse notFound(String message, String path) {
        return of("NOT_FOUND", message, path);
    }

    public static ErrorResponse conflict(String message, String path) {
        return of("CONFLICT", message, path);
    }

    public static ErrorResponse serviceUnavailable(String message, String path) {
        return of("SERVICE_UNAVAILABLE", message, path);
    }

    public static ErrorResponse internalServerError(String message, String path) {
        return of("INTERNAL_SERVER_ERROR", message, path);
    }
}
