package com.project.ecasa.Specifications;

import com.project.ecasa.Enums.ContainerType;
import com.project.ecasa.Models.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<Product> hasSection(Long sectionId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.join("sectionProducts").join("section").get("id"), sectionId);
    }

    public static Specification<Product> hasLot(String lot) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("lot"), lot);
    }

    public static Specification<Product> isFragile(Boolean fragile) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("fragile"), fragile);
    }

    public static Specification<Product> hasColor(String color) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("color"), color);
    }

    public static Specification<Product> hasPriceInRange(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }

    public static Specification<Product> hasMinPrice(Double minPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> hasMaxPrice(Double maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> hasContainerType(ContainerType containerType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("containerType"), containerType);
    }
}

