package org.idea.hackaton1.exceptions;

public class EmailServiceException extends RuntimeException {
    public EmailServiceException(String message) {
        super(message);
    }

    public EmailServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailServiceException() {
        super("El servicio de email no está disponible en este momento");
    }
}
