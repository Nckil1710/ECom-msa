package org.example.product_service.mapper;

import org.example.product_service.domain.Product;
import org.example.product_service.dto.ProductResponse;

public class ProductMapper {

    public static ProductResponse toResponse(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getSku(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getBrand(),
                p.getCategory().getId(),
                p.getCategory().getName(),
                p.getStatus().name()
        );
    }
}
