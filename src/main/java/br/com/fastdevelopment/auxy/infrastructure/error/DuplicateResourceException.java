package br.com.fastdevelopment.auxy.infrastructure.error;

public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}
