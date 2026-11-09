package br.com.fastdevelopment.auxy.domain.customer.service;

import br.com.fastdevelopment.auxy.domain.customer.dto.CustomerCreateRequest;
import br.com.fastdevelopment.auxy.domain.customer.dto.CustomerResponse;
import br.com.fastdevelopment.auxy.domain.customer.dto.CustomerUpdateRequest;
import br.com.fastdevelopment.auxy.domain.customer.entity.Customer;
import br.com.fastdevelopment.auxy.domain.customer.mapper.CustomerMapper;
import br.com.fastdevelopment.auxy.domain.customer.repository.CustomerRepository;
import br.com.fastdevelopment.auxy.domain.tenant.entity.Tenant;
import br.com.fastdevelopment.auxy.domain.tenant.repository.TenantRepository;
import br.com.fastdevelopment.auxy.infrastructure.error.DuplicateResourceException;
import br.com.fastdevelopment.auxy.infrastructure.error.ResourceNotFoundException;
import br.com.fastdevelopment.auxy.infrastructure.tenant.TenantContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final TenantRepository tenantRepository;

    public CustomerService(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper,
            TenantRepository tenantRepository
    ) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.tenantRepository = tenantRepository;
    }

    public List<CustomerResponse> list() {
        Long tenantId = TenantContext.requireTenantId();

        return customerRepository.findAllByTenantId(tenantId)
                .stream()
                .map(customerMapper::toResponse)
                .toList();
    }

    public Optional<CustomerResponse> getById(Long id) {
        Long tenantId = TenantContext.requireTenantId();

        return customerRepository.findByIdAndTenantId(id, tenantId)
                .map(customerMapper::toResponse);
    }

    public CustomerResponse create(
            CustomerCreateRequest request
    ) {
        Long tenantId = TenantContext.requireTenantId();

        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant with id " + tenantId + " was not found"));

        if (customerRepository.existsByEmailAndTenantId(
                request.email(),
                tenantId
        )) {
            throw new DuplicateResourceException("E-mail already exists");
        }

        Customer customer = customerMapper.toEntity(request);
        customer.setTenant(tenant);

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toResponse(savedCustomer);
    }

    public Optional<CustomerResponse> update(
            Long id,
            CustomerUpdateRequest request
    ) {
        Long tenantId = TenantContext.requireTenantId();

        tenantRepository.findById(tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant with id " + tenantId + " was not found"));

        Optional<Customer> customerOptional = customerRepository.findByIdAndTenantId(id, tenantId);

        if (customerOptional.isEmpty()) {
            return Optional.empty();
        }

        Customer customer = customerOptional.get();

        String email = request.email() == null
                || request.email().isBlank()
                ? customer.getEmail()
                : request.email();

        if (customerRepository.existsByEmailAndTenantIdAndIdNot(email, tenantId, id)) {
            throw new DuplicateResourceException("E-mail already exists");
        }

        customerMapper.updateEntity(request, customer);

        Customer updatedCustomer = customerRepository.save(customer);

        return Optional.of(customerMapper.toResponse(updatedCustomer));
    }

    public boolean delete(Long id) {
        Long tenantId = TenantContext.requireTenantId();

        if (customerRepository.findByIdAndTenantId(id, tenantId).isEmpty()) {
            return false;
        }

        customerRepository.deleteById(id);
        return true;
    }
}
