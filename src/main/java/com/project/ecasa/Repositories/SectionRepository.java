package com.project.ecasa.Repositories;

import com.project.ecasa.Enums.ProductType;
import com.project.ecasa.Models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByProductType(ProductType productType);
}
