package br.com.fastdevelopment.auxy.domain.customer.dto;

public record CustomerResponse(
        Long id,
        String name,
        String email
) {
}
