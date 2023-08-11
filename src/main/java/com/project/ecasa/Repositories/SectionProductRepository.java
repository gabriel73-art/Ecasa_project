package com.project.ecasa.Repositories;

import com.project.ecasa.Models.SectionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SectionProductRepository extends JpaRepository<SectionProduct, Long> {

}

