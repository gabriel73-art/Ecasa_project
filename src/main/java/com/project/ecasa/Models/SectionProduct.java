package com.project.ecasa.Models;

import javax.persistence.*;

@Entity
@Table(name = "section_product")
public class SectionProduct {

    @EmbeddedId
    private SectionProductId id;

    @ManyToOne
    @MapsId("sectionId")
    @JoinColumn(name = "section_id")
    private Section section;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    public SectionProduct() {
    }

    public SectionProductId getId() {
        return id;
    }

    public void setId(SectionProductId id) {
        this.id = id;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

