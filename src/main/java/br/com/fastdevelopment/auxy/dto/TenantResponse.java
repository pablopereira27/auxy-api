package br.com.fastdevelopment.auxy.dto;

public record TenantResponse(
        Long id,
        String name,
        String slug
) {
}