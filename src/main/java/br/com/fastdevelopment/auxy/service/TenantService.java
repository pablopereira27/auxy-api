package br.com.fastdevelopment.auxy.service;

import br.com.fastdevelopment.auxy.dto.TenantRequest;
import br.com.fastdevelopment.auxy.dto.TenantResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TenantService {

    private final List<TenantResponse> tenants = new ArrayList<>();

    TenantService() {
        tenants.add(new TenantResponse(1L,"Fast Development","fast-development"));
        tenants.add(new TenantResponse(2L,"Empresa 1","empresa-1"));
    }

    private void validateSlugUniqueness(
            Long tenantId,
            String slug
    ) {
        boolean exists = tenants.stream()
                .anyMatch(t -> t.slug().equals(slug)
                    && !t.id().equals(tenantId)
                );

        if (exists) {
            throw new RuntimeException("Slug already exists");
        }
    }

    private Optional<Integer> findTenantIndexById(
            Long id
    ) {
        for (int i = 0; i < tenants.size(); i++) {

            if (tenants.get(i).id().equals(id)) {
                return Optional.of(i);
            }

        }

        return Optional.empty();
    }

    public List<TenantResponse> list() {
        return tenants;
    }

    public Optional<TenantResponse> getById(Long id) {
        return tenants.stream()
                .filter(t -> t.id().equals(id))
                .findFirst();
    }

    public TenantResponse create(TenantRequest tenantData) {
        validateSlugUniqueness(null, tenantData.slug());

        Long nextId = tenants.stream()
                .map(TenantResponse::id)
                .max(Long::compareTo)
                .orElse(0L) + 1;

        TenantResponse tenant = new TenantResponse(
            nextId,
            tenantData.name(),
            tenantData.slug()
        );

        tenants.add(tenant);

        return tenant;
    }

    public Optional<TenantResponse> update(Long id, TenantRequest tenantData) {
        if(tenantData.slug() != null && !tenantData.slug().isBlank()) {
            validateSlugUniqueness(id, tenantData.slug());
        }

        Optional<Integer> tenantIndex = findTenantIndexById(id);

        if (tenantIndex.isEmpty()) {
            return Optional.empty();
        }

        TenantResponse current = tenants.get(tenantIndex.get());

        String name = tenantData.name() == null || tenantData.name().isBlank()
                ? current.name()
                : tenantData.name();

        String slug = tenantData.slug() == null || tenantData.slug().isBlank()
                ? current.slug()
                : tenantData.slug();

        TenantResponse updated = new TenantResponse(
                current.id(),
                name,
                slug
        );

        tenants.set(tenantIndex.get(), updated);

        return Optional.of(updated);
    }

    public boolean delete(Long id) {
        Optional<Integer> tenantIndex = findTenantIndexById(id);

        if (tenantIndex.isEmpty()) {
            return false;
        }

        tenants.remove(tenantIndex.get().intValue());
        return true;
    }
}
