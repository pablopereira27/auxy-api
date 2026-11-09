package br.com.fastdevelopment.auxy.infrastructure.error;

public class MissingTenantException extends RuntimeException {

    public MissingTenantException(String message) {
        super(message);
    }
}
