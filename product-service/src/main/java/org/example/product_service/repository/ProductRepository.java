package org.example.product_service.repository;

import org.example.product_service.domain.Product;
import org.example.product_service.domain.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findBySku(String sku);

    boolean existsBySku(String sku);

    Page<Product> findByStatus(ProductStatus status, Pageable page);

    Page<Product> findByCategoryId(UUID id, Pageable page);


}
