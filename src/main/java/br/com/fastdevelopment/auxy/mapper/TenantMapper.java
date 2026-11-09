package br.com.fastdevelopment.auxy.mapper;

import br.com.fastdevelopment.auxy.dto.TenantCreateRequest;
import br.com.fastdevelopment.auxy.dto.TenantResponse;
import br.com.fastdevelopment.auxy.dto.TenantUpdateRequest;
import br.com.fastdevelopment.auxy.entity.Tenant;
import org.mapstruct.*;

import java.util.Locale;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE
)
public interface TenantMapper {
    @Mapping(
            target = "slug",
            qualifiedByName = "normalizeSlug"
    )
    Tenant toEntity(TenantCreateRequest request);

    TenantResponse toResponse(Tenant tenant);

    @Mapping(
            target = "slug",
            qualifiedByName = "normalizeSlug"
    )
    void updateEntity(
            TenantUpdateRequest request,
            @MappingTarget Tenant tenant
    );

    @Named("normalizeSlug")
    default String normalizeSlug(String slug) {
        return slug
                .trim()
                .toLowerCase(Locale.ROOT)
                .replaceAll("\\s+", "-");
    }
}
