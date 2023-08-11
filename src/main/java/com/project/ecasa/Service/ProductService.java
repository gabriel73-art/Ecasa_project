package com.project.ecasa.Service;

import com.project.ecasa.Enums.ContainerType;
import com.project.ecasa.Exceptions.NotMatchException;
import com.project.ecasa.Models.Product;
import com.project.ecasa.Repositories.ProductRepository;
import com.project.ecasa.Specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


    public List<Product> getProductsBySpecifications(Long sectionId, String lot, Boolean fragile,
                                                     String color, Double minPrice, Double maxPrice,
                                                     ContainerType containerType)throws NotMatchException {
        Specification<Product> spec = Specification.where(null);

        if (sectionId != null) {
            spec = spec.and(ProductSpecifications.hasSection(sectionId));
        }

        if (lot != null) {
            spec = spec.and(ProductSpecifications.hasLot(lot));
        }

        if (fragile != null) {
            spec = spec.and(ProductSpecifications.isFragile(fragile));
        }

        if (color != null) {
            spec = spec.and(ProductSpecifications.hasColor(color));
        }

        if (minPrice != null && maxPrice != null) {
            spec = spec.and(ProductSpecifications.hasPriceInRange(minPrice, maxPrice));
        } else if (minPrice != null) {
            spec = spec.and(ProductSpecifications.hasMinPrice(minPrice));
        } else if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.hasMaxPrice(maxPrice));
        }

        if (containerType != null) {
            spec = spec.and(ProductSpecifications.hasContainerType(containerType));
        }
        if(productRepository.findAll(spec).isEmpty())
            throw new NotMatchException("There is no products for that specifications");

        return productRepository.findAll(spec);
    }
}

