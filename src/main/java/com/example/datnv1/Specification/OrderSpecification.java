package com.example.datnv1.Specification;

import com.example.datnv1.Entity.Orders.Orders;
import com.example.datnv1.Entity.Product.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class OrderSpecification {

    public static Specification<Orders> hasStatus(String status) {
        return (Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (status != null && !status.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status));
            }
            return predicate;
        };
    }

    public static Specification<Orders> hasSkus(List<String> skus) {
        return (Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return root.get("sku").in(skus);
        };
    }
}
