package br.com.fastdevelopment.auxy.infrastructure.error;

public class InvalidTenantException extends RuntimeException {

    public InvalidTenantException(String message) {
        super(message);
    }
}
