package br.com.fastdevelopment.auxy.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
