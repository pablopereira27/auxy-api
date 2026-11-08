package br.com.fastdevelopment.auxy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TenantCreateRequest(
        @NotBlank()
        @Size(max = 100)
        String name,

        @NotBlank()
        @Size(max = 100)
        String slug
) {
}
