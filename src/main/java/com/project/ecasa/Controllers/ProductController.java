package com.project.ecasa.Controllers;

import com.project.ecasa.Enums.ContainerType;
import com.project.ecasa.Exceptions.NotMatchException;
import com.project.ecasa.Models.Product;
import com.project.ecasa.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping("/search")
    public List<Product> filterProducts(@RequestParam(required = false)Long sectionId,
                                        @RequestParam(required = false)String lot,
                                        @RequestParam(required = false)Boolean fragile,
                                        @RequestParam(required = false)String color,
                                        @RequestParam(required = false)Double minPrice,
                                        @RequestParam(required = false)Double maxPrice,
                                        @RequestParam(required = false)ContainerType containerType) {
       try {
           return productService.getProductsBySpecifications(sectionId, lot, fragile,
                   color, minPrice, maxPrice,
                   containerType);
       }catch (NotMatchException ex){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"", ex);
       }
    }

    @ExceptionHandler(NotMatchException.class)
    public ResponseEntity<String> handleNotMatchException(NotMatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

