package br.com.fastdevelopment.auxy.domain.tenant.dto;

public record TenantResponse(
        Long id,
        String name,
        String slug
) {
}