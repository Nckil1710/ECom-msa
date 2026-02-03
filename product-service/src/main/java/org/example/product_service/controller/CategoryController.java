package org.example.product_service.controller;

import lombok.RequiredArgsConstructor;
import org.example.product_service.domain.Category;
import org.example.product_service.dto.CategoryRequest;
import org.example.product_service.dto.CategoryResponse;
import org.example.product_service.service.CategoryService;
import org.example.product_service.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest request) {
        Category parent = null;
        if (request.parentId() != null) {
            parent = categoryService.getCategory(request.parentId());
        }
        Category category = new Category(request.name(), parent);
        category = categoryService.createCategory(category);
        return new CategoryResponse(
                category.getId(), category.getName(),
                parent != null ? parent.getId() : null
        );
    }

}
