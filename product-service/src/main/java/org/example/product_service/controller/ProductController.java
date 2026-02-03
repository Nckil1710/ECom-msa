package org.example.product_service.controller;

import lombok.RequiredArgsConstructor;
import org.example.product_service.domain.Product;
import org.example.product_service.domain.ProductStatus;
import org.example.product_service.dto.ProductRequest;
import org.example.product_service.dto.ProductResponse;
import org.example.product_service.mapper.ProductMapper;
import org.example.product_service.service.CategoryService;
import org.example.product_service.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @PatchMapping("/{id}/deactivate")
    public ProductResponse deactivate(@PathVariable UUID id) {
        productService.deactivateProduct(id);
        return ProductMapper.toResponse(
                productService.getProduct(id)
        );
    }

    @GetMapping
    public Page<ProductResponse> getAsList(Pageable page) {
        return productService.listProducts(page)
                .map(ProductMapper::toResponse);
    }

    @GetMapping("/sku/{sku}")
    public ProductResponse getBySku(@PathVariable String sku) {
        return ProductMapper.toResponse(
                productService.getProductBySku(sku)
        );
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable UUID id) {
        return ProductMapper.toResponse(
                productService.getProduct(id)
        );
    }

    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductRequest request) {
        var category = categoryService.getCategory(request.categoryId());
        var product = new Product(
                request.sku(),
                request.name(),
                request.price(),
                ProductStatus.ACTIVE,
                category
        );
        product.setBrand(request.brand());
        product.setDescription(request.description());
        var response = ProductMapper.toResponse(productService.createProduct(product));
        return response;
    }
}
