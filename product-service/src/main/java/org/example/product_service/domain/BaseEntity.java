package org.example.product_service.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    @Version
    private long version;

    // Automatically set the creation timestamp before saving a new entity
    @PrePersist
    public void prePersist() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;  // Set updatedAt during creation as well
    }

    // Automatically update the update timestamp before updating an existing entity
    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }
}
