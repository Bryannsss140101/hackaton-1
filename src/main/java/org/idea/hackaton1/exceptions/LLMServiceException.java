package org.idea.hackaton1.exceptions;

public class LLMServiceException extends RuntimeException {
    public LLMServiceException(String message) {
        super(message);
    }

    public LLMServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public LLMServiceException() {
        super("El servicio de GitHub Models no está disponible en este momento");
    }
}
