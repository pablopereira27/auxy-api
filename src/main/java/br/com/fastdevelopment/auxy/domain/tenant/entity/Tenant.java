package br.com.fastdevelopment.auxy.domain.tenant.entity;

import br.com.fastdevelopment.auxy.domain.customer.entity.Customer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
    name = "tenants",
    uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_tenant_slug",
                columnNames = "slug"
        )
    }
)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String slug;

    public Tenant(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    @OneToMany(mappedBy = "tenant")
    private List<Customer> customers = new ArrayList<>();
}
