package org.example.product_service.dto;

import java.util.UUID;

public record CategoryRequest(
        String name,
        UUID parentId
) {
}
