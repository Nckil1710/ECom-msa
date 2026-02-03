package org.example.product_service.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findRootCategories", query = "SELECT c FROM Category c WHERE c.parent IS NULL")
public class Category extends BaseEntity {

    @Column(nullable = true, length = 32)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id",nullable = true)
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> children;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products;


    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }
}
