package org.idea.hackaton1.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String id) {
        super(String.format("%s no encontrado con id: %s", resourceName, id));
    }
}
