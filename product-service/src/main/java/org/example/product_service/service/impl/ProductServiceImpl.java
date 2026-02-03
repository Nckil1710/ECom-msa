package org.example.product_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.product_service.domain.Product;
import org.example.product_service.domain.ProductStatus;
import org.example.product_service.repository.ProductRepository;
import org.example.product_service.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    @Transactional
    public Product createProduct(Product product) {
        if (repository.existsBySku(product.getSku())) {
            throw new IllegalArgumentException("SKU already exists.");
        }
        if (product.getPrice().signum() < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        product.setStatus(ProductStatus.ACTIVE);
        return repository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(UUID id, Product product) {
        var existing = getProduct(id);
        if (existing.getStatus() == ProductStatus.DISCONTINUED) {
            throw new IllegalArgumentException("Cannot update");
        }
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setBrand(product.getBrand());
        existing.setCategory(product.getCategory());

        return repository.save(existing);
    }

    @Override
    public Product getProduct(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    @Override
    public Product getProductBySku(String sku) {
        return repository.findBySku(sku)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    @Override
    public Page<Product> listProducts(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public Page<Product> listActiveProducts(Pageable page) {
        return repository.findByStatus(ProductStatus.ACTIVE, page);
    }

    @Override
    public void deactivateProduct(UUID id) {
        var p = getProduct(id);
        p.setStatus(ProductStatus.INACTIVE);
        repository.save(p);
    }
}
