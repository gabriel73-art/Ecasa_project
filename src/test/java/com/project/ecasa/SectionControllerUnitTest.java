package com.project.ecasa;

import com.project.ecasa.Controllers.SectionController;
import com.project.ecasa.Models.Section;
import com.project.ecasa.Models.SectionProduct;
import com.project.ecasa.Service.SectionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SectionControllerUnitTest {
    @Mock
    private SectionService sectionService;


    @InjectMocks
    private SectionController sectionController;
    private AutoCloseable closeable;

    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }



    @Test
    public void testGetAllSections() {

        Section section1 = new Section();
        Section section2 = new Section();
        List<Section> sections = Arrays.asList(section1, section2);


        when(sectionService.getAllSections()).thenReturn(sections);


        List<Section> result = sectionController.getAllSections();


        assertEquals(sections, result);
        verify(sectionService, times(1)).getAllSections();
    }

    @Test
    public void testCreateSection() {

        Section section = new Section();
        Section createdSection = new Section();


        when(sectionService.createSection(section)).thenReturn(createdSection);


        ResponseEntity<Section> response = sectionController.createSection(section);


        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdSection, response.getBody());
        verify(sectionService, times(1)).createSection(section);
    }

    @Test
    public void testUpdateSection() {

        long sectionId = 1L;
        Section section = new Section();
        Section updatedSection = new Section();


        when(sectionService.updateSection(sectionId, section)).thenReturn(updatedSection);


        ResponseEntity<Section> response = sectionController.updateSection(sectionId, section);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedSection, response.getBody());
        verify(sectionService, times(1)).updateSection(sectionId, section);
    }

    @Test
    public void testDeleteSection() {

        long sectionId = 1L;


        ResponseEntity<String> response = sectionController.deleteSection(sectionId);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Section deleted successfully", response.getBody());
        verify(sectionService, times(1)).deleteSection(sectionId);
    }

    @Test
    public void testAddQuantitySection() {

        long sectionId = 1L;
        long productId = 1L;
        int quantity = 10;
        SectionProduct sectionProduct = new SectionProduct();


        when(sectionService.addProductToSection(sectionId, productId, quantity)).thenReturn(sectionProduct);


        ResponseEntity<SectionProduct> response = sectionController.addQuantitySection(sectionId, productId, quantity);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sectionProduct, response.getBody());
        verify(sectionService, times(1)).addProductToSection(sectionId, productId, quantity);
    }

}

