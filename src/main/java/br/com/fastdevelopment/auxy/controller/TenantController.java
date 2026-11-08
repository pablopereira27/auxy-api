package br.com.fastdevelopment.auxy.controller;

import br.com.fastdevelopment.auxy.dto.TenantRequest;
import br.com.fastdevelopment.auxy.dto.TenantResponse;
import br.com.fastdevelopment.auxy.service.TenantService;
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
        @RequestBody TenantRequest request
    ) {
        return this.tenantService.create(request);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TenantResponse> update(
        @PathVariable Long id,
        @RequestBody TenantRequest request
    ) {
        return this.tenantService.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(
        @PathVariable Long id
    ) {
        this.tenantService.delete(id);
    }
}
