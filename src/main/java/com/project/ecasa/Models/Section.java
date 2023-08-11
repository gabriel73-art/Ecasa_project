package com.project.ecasa.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ecasa.Enums.ProductType;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="sections")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    @Column(name="sizes_sections")
    private Double sizeSection;
    @Enumerated(EnumType.STRING)
    @Column(name="type_accepted")
    private ProductType productType;
    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<SectionProduct> sectionProducts;

    public Section() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSizeSection() {
        return sizeSection;
    }

    public void setSizeSection(Double sizeSection) {
        this.sizeSection = sizeSection;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<SectionProduct> getSectionProducts() {
        return sectionProducts;
    }

}
