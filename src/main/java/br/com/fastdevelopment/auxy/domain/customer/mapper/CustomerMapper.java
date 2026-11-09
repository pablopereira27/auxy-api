package br.com.fastdevelopment.auxy.domain.customer.mapper;

import br.com.fastdevelopment.auxy.domain.customer.dto.CustomerCreateRequest;
import br.com.fastdevelopment.auxy.domain.customer.dto.CustomerResponse;
import br.com.fastdevelopment.auxy.domain.customer.dto.CustomerUpdateRequest;
import br.com.fastdevelopment.auxy.domain.customer.entity.Customer;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy =
                NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerMapper {
    @Mapping(target = "tenant", ignore = true)
    Customer toEntity(CustomerCreateRequest request);

    CustomerResponse toResponse(Customer customer);

    @Mapping(target = "tenant", ignore = true)
    void updateEntity(
            CustomerUpdateRequest request,
            @MappingTarget Customer customer
    );
}
