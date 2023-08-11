package com.project.ecasa.Service;

import com.project.ecasa.Enums.ProductType;
import com.project.ecasa.Exceptions.NotFoundException;
import com.project.ecasa.Exceptions.NotMatchException;
import com.project.ecasa.Exceptions.SectionNotEmptyException;
import com.project.ecasa.Models.Product;
import com.project.ecasa.Models.Section;
import com.project.ecasa.Models.SectionProduct;
import com.project.ecasa.Models.SectionProductId;
import com.project.ecasa.Repositories.ProductRepository;
import com.project.ecasa.Repositories.SectionProductRepository;
import com.project.ecasa.Repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SectionProductRepository sectionProductRepository;


    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }


    public Section createSection(Section section){
        return sectionRepository.save(section);

    }

    public Section updateSection(Long sectionId, Section section)throws NotFoundException {
        Section existingSection = sectionRepository.findById(sectionId).get();
        if(existingSection==null)
            throw new NotFoundException();

        existingSection.setSizeSection(section.getSizeSection());
        existingSection.setProductType(section.getProductType());

        return sectionRepository.save(existingSection);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public void deleteSection(Long sectionId) throws SectionNotEmptyException {
        Section section = sectionRepository.findById(sectionId).orElseThrow(NotFoundException::new);

        if (!section.getSectionProducts().isEmpty()) {
            for (SectionProduct sectionProduct : section.getSectionProducts()) {
                if (sectionProduct.getQuantity() > 0) {
                    throw new SectionNotEmptyException("Cannot delete this section because contains products");
                }
            }
        }

        sectionRepository.deleteById(sectionId);
    }


    public SectionProduct addProductToSection(Long sectionId, Long productId, int quantity) throws NotMatchException {
        Product product = productRepository.findById(productId).orElseThrow(NotFoundException::new);
        Section section = sectionRepository.findById(sectionId).orElseThrow(NotFoundException::new);
        if (product.getProductType() != section.getProductType()) {
            throw new NotMatchException("This type of product can't be into this section");
        }

        SectionProduct sectionProduct = new SectionProduct();
        SectionProductId id = new SectionProductId();

        id.setSectionId(sectionId);
        id.setProductId(productId);
        sectionProduct.setId(id);
        sectionProduct.setProduct(product);
        sectionProduct.setSection(section);


        sectionProduct.setQuantity(quantity);
        return sectionProductRepository.save(sectionProduct);
    }
}
