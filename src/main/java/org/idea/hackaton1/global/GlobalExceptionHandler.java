package org.idea.hackaton1.global;

import org.idea.hackaton1.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de errores de validación (400 BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Error de validación");

        ErrorResponse error = ErrorResponse.badRequest(
                message,
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Manejo de IllegalArgumentException (400 BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex,
            WebRequest request) {

        ErrorResponse error = ErrorResponse.badRequest(
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Manejo de credenciales inválidas (401 UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(
            BadCredentialsException ex,
            WebRequest request) {

        ErrorResponse error = ErrorResponse.unauthorized(
                "Credenciales inválidas",
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    // Manejo de acceso denegado por Spring Security (403 FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(
            AccessDeniedException ex,
            WebRequest request) {

        ErrorResponse error = ErrorResponse.forbidden(
                "No tiene permisos para acceder a este recurso",
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    // Manejo de acceso no autorizado personalizado (403 FORBIDDEN)
    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedAccess(
            UnauthorizedAccessException ex,
            WebRequest request) {

        ErrorResponse error = ErrorResponse.forbidden(
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    // Manejo de recurso no encontrado (404 NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException ex,
            WebRequest request) {

        ErrorResponse error = ErrorResponse.notFound(
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Manejo de usuario duplicado (409 CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(
            UserAlreadyExistsException ex,
            WebRequest request) {

        ErrorResponse error = ErrorResponse.conflict(
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // Manejo de servicios no disponibles (503 SERVICE_UNAVAILABLE)
    @ExceptionHandler({LLMServiceException.class, EmailServiceException.class})
    public ResponseEntity<ErrorResponse> handleServiceUnavailable(
            RuntimeException ex,
            WebRequest request) {

        ErrorResponse error = ErrorResponse.serviceUnavailable(
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

    // Manejo de ResponseStatusException (para lanzar errores HTTP directamente)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(
            ResponseStatusException ex,
            WebRequest request) {

        String errorCode = switch (ex.getStatusCode().value()) {
            case 400 -> "BAD_REQUEST";
            case 401 -> "UNAUTHORIZED";
            case 403 -> "FORBIDDEN";
            case 404 -> "NOT_FOUND";
            case 409 -> "CONFLICT";
            case 503 -> "SERVICE_UNAVAILABLE";
            default -> "ERROR";
        };

        ErrorResponse error = ErrorResponse.of(
                errorCode,
                ex.getReason() != null ? ex.getReason() : "Error en la solicitud",
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    // Manejo genérico de cualquier otra excepción (500 INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex,
            WebRequest request) {

        // Log del error para debugging (opcional pero recomendado)
        System.err.println("Error no manejado: " + ex.getMessage());
        ex.printStackTrace();

        ErrorResponse error = ErrorResponse.internalServerError(
                "Ocurrió un error interno en el servidor",
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
