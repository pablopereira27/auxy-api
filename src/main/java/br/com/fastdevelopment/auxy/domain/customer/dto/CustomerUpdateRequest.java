package br.com.fastdevelopment.auxy.domain.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record CustomerUpdateRequest(
        @Size(max = 100)
        String name,

        @Size(max = 100)
        @Email()
        String email
) {
}
