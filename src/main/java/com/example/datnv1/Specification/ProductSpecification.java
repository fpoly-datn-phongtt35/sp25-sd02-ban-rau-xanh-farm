package com.example.datnv1.Specification;

import com.example.datnv1.Entity.Product.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public class ProductSpecification {

    public static Specification<Product> hasProductName(String productName) {
        return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (productName == null || productName.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("name"), productName);
        };
    }

    public static Specification<Product> hasSkus(List<String> skus) {
        return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return root.get("sku").in(skus);
        };
    }

}
