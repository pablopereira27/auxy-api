package br.com.fastdevelopment.auxy.domain.tenant.controller;

import br.com.fastdevelopment.auxy.domain.tenant.dto.TenantCreateRequest;
import br.com.fastdevelopment.auxy.domain.tenant.dto.TenantResponse;
import br.com.fastdevelopment.auxy.domain.tenant.dto.TenantUpdateRequest;
import br.com.fastdevelopment.auxy.domain.tenant.service.TenantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenants")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public List<TenantResponse> list() {
        return this.tenantService.list();
    }

    @GetMapping("{id}")
    public ResponseEntity<TenantResponse> getById(
        @PathVariable Long id
    ) {
        return this.tenantService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TenantResponse create(
        @Valid @RequestBody TenantCreateRequest request
    ) {
        return this.tenantService.create(request);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TenantResponse> update(
        @PathVariable Long id,
        @Valid @RequestBody TenantUpdateRequest request
    ) {
        return this.tenantService.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable Long id
    ) {
        boolean deleted = tenantService.delete(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
