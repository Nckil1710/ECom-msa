package org.example.product_service.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(
        String sku,
        String name,
        String description,
        BigDecimal price,
        String brand,
        UUID categoryId
) {
}
