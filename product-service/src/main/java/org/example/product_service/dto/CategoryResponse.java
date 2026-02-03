package org.example.product_service.dto;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name,
        UUID paentId
) {
}
