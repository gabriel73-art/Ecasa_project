package com.project.ecasa.Models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SectionProductId implements Serializable {

    private Long sectionId;
    private Long productId;

    public SectionProductId() {
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}

