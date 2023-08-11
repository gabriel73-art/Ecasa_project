package com.project.ecasa.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ecasa.Enums.ContainerType;
import com.project.ecasa.Enums.ProductType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name="types_products")
    private ProductType productType;
    @Column(name="prices")
    private Double price;
    @Column(name="sizes")
    private Double size;
    @Column(name = "colors")
    private String color;
    @Column(name = "lots")
    private String lot;
    @Column(name="fragility")
    private Boolean fragile;
    @Enumerated(EnumType.STRING)
    @Column(name="types_containers")
    private ContainerType containerType;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<SectionProduct> sectionProducts;



    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public Boolean getFragile() {
        return fragile;
    }

    public void setFragile(Boolean fragile) {
        this.fragile = fragile;
    }

    public ContainerType getContainerType() {
        return containerType;
    }

    public void setContainerType(ContainerType containerType) {
        this.containerType = containerType;
    }

    public List<SectionProduct> getSectionProducts() {
        return sectionProducts;
    }

    public void setSectionProducts(List<SectionProduct> sectionProducts) {
        this.sectionProducts = sectionProducts;
    }
}
