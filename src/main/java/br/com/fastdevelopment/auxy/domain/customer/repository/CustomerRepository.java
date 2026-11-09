package br.com.fastdevelopment.auxy.domain.customer.repository;

import br.com.fastdevelopment.auxy.domain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByTenantId(Long tenantId);

    Optional<Customer> findByIdAndTenantId(
            Long id,
            Long tenantId
    );

    boolean existsByEmailAndTenantId(
            String email,
            Long tenantId
    );

    boolean existsByEmailAndTenantIdAndIdNot(
            String email,
            Long tenantId,
            Long id
    );
}