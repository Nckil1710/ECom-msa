package org.example.product_service.repository;

import org.example.product_service.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findByName(String name);

    boolean existsByNameAndParentId(String name, UUID parentId);

    List<Category> findByParentId(UUID parentId);

//    @Query("SELECT c FROM Category c WHERE c.parent IS NULL")
    @Query(name = "Category.findRootCategories")
    List<Category> findAllRootCategories();
}
