package org.idea.hackaton1.exceptions;

public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }

    public UnauthorizedAccessException() {
        super("No tiene permisos para realizar esta acción");
    }
}
