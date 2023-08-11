package com.project.ecasa.Controllers;

import com.project.ecasa.Exceptions.NotFoundException;
import com.project.ecasa.Exceptions.NotMatchException;
import com.project.ecasa.Exceptions.SectionNotEmptyException;
import com.project.ecasa.Models.Section;
import com.project.ecasa.Models.SectionProduct;
import com.project.ecasa.Service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping
    public List<Section> getAllSections() {
        return sectionService.getAllSections();
    }
    @PostMapping("/createSection")
    public ResponseEntity<Section> createSection(@RequestBody Section section) {
        Section createdSection = sectionService.createSection(section);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSection);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Section> updateSection(@PathVariable Long id, @RequestBody Section section) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(sectionService.updateSection(id,section));

        }catch (NotFoundException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No sections found for the specified id to update.", ex);
        }
    }


   @DeleteMapping("/delete/{id}")
   public ResponseEntity<String> deleteSection(@PathVariable Long id) {
       try {
           sectionService.deleteSection(id);
           return ResponseEntity.ok("Section deleted successfully");
       } catch (NotFoundException ex) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "", ex);
       } catch (SectionNotEmptyException ex) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "", ex);
       }
   }

    @PostMapping("/addProductToSection/{ids}/{idp}")
    public ResponseEntity<SectionProduct> addQuantitySection(@PathVariable Long ids, @PathVariable Long idp, @RequestParam Integer quantity) {
        try{
            return ResponseEntity.ok(sectionService.addProductToSection(ids,idp,quantity));

        }catch (NotMatchException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"", ex);
        }
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SectionNotEmptyException.class)
    public ResponseEntity<String> handleSectionNotEmptyException(SectionNotEmptyException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(NotMatchException.class)
    public ResponseEntity<String> handleNotMatchException(NotMatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
