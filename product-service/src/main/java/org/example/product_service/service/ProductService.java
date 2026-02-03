package org.example.product_service.service;

import org.example.product_service.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {

    Product createProduct(Product product);

    Product updateProduct(UUID id, Product product);

    Product getProduct(UUID id);

    Product getProductBySku(String sku);

    Page<Product> listProducts(Pageable page);

    Page<Product> listActiveProducts(Pageable page);

    void deactivateProduct(UUID id);

    Product sa();
}
