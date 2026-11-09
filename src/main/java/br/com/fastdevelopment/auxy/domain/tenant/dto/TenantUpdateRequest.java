package br.com.fastdevelopment.auxy.domain.tenant.dto;

import jakarta.validation.constraints.Size;

public record TenantUpdateRequest(
        @Size(max = 100)
        String name,

        @Size(max = 100)
        String slug
) {
}
