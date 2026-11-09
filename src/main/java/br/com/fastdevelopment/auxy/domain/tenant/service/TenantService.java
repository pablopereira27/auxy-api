package br.com.fastdevelopment.auxy.domain.tenant.service;

import br.com.fastdevelopment.auxy.domain.tenant.dto.TenantCreateRequest;
import br.com.fastdevelopment.auxy.domain.tenant.dto.TenantResponse;
import br.com.fastdevelopment.auxy.domain.tenant.dto.TenantUpdateRequest;
import br.com.fastdevelopment.auxy.domain.tenant.entity.Tenant;
import br.com.fastdevelopment.auxy.domain.tenant.mapper.TenantMapper;
import br.com.fastdevelopment.auxy.domain.tenant.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;

    public TenantService(
            TenantRepository tenantRepository,
            TenantMapper tenantMapper
    ) {
        this.tenantRepository = tenantRepository;
        this.tenantMapper = tenantMapper;
    }

    public List<TenantResponse> list() {
        return tenantRepository.findAll()
                .stream()
                .map(tenantMapper::toResponse)
                .toList();
    }

    public Optional<TenantResponse> getById(Long id) {
        return tenantRepository.findById(id)
                .map(tenantMapper::toResponse);
    }

    public TenantResponse create(TenantCreateRequest request) {
        String normalizedSlug = tenantMapper.normalizeSlug(request.slug());

        if (tenantRepository.existsBySlug(normalizedSlug)) {
            throw new RuntimeException("Slug already exists");
        }

        Tenant tenant = tenantMapper.toEntity(request);
        Tenant savedTenant = tenantRepository.save(tenant);

        return tenantMapper.toResponse(savedTenant);
    }

    public Optional<TenantResponse> update(Long id, TenantUpdateRequest request) {
        Optional<Tenant> tenantOptional = tenantRepository.findById(id);

        if (tenantOptional.isEmpty()) {
            return Optional.empty();
        }

        Tenant tenant = tenantOptional.get();

        String slug = request.slug() == null
                || request.slug().isBlank()
                ? tenant.getSlug()
                : tenantMapper.normalizeSlug(request.slug());

        if (tenantRepository.existsBySlugAndIdNot(slug, id)) {
            throw new RuntimeException("Slug already exists");
        }

        tenantMapper.updateEntity(request, tenant);

        Tenant updatedTenant = tenantRepository.save(tenant);

        return Optional.of(tenantMapper.toResponse(updatedTenant));
    }

    public boolean delete(Long id) {
        if (!tenantRepository.existsById(id)) {
            return false;
        }

        tenantRepository.deleteById(id);
        return true;
    }
}
