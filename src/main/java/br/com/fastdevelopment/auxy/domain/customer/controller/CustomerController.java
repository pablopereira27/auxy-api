package br.com.fastdevelopment.auxy.domain.customer.controller;

import br.com.fastdevelopment.auxy.domain.customer.dto.CustomerCreateRequest;
import br.com.fastdevelopment.auxy.domain.customer.dto.CustomerResponse;
import br.com.fastdevelopment.auxy.domain.customer.service.CustomerService;
import br.com.fastdevelopment.auxy.domain.customer.dto.CustomerUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerResponse> list() {
        return this.customerService.list();
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerResponse> getById(
            @PathVariable Long id
    ) {
        return this.customerService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CustomerResponse create(
            @Valid @RequestBody CustomerCreateRequest request
    ) {
        return this.customerService.create(request);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerUpdateRequest request
    ) {
        return this.customerService.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        boolean deleted = customerService.delete(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
