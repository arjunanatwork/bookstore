package com.arjunan.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arjunan.bookstore.dto.ClassificationDTO;
import com.arjunan.bookstore.service.ClassificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Classification", description = "The Classification API")
public class ClassificationController {

	@Autowired
	ClassificationService classificationService;

	@PostMapping("/classification")
    @Operation(summary = "Save an Classification", description = "Save an Classification", tags = { "classification" })
	public ClassificationDTO addClassification(@RequestBody ClassificationDTO classification) {
		return classificationService.saveClassification(classification);
	}

	@GetMapping("/classifications")
    @Operation(summary = "Get all Classification", description = "Get all Classification", tags = { "classification" })
	public List<ClassificationDTO> findAllClassifications() {
		return classificationService.getClassifications();
	}

	@GetMapping("/classification/{id}")
    @Operation(summary = "Find a Classification by Id", description = "Find a Classification by Id", tags = { "classification" })
	public ClassificationDTO findClassificationById(@PathVariable int id) {
		return classificationService.getClassificationById(id);
	}

	@PutMapping("/classification/update")
    @Operation(summary = "Update a Classification", description = "Update a Classification", tags = { "classification" })
	public ClassificationDTO updateClassification(@RequestBody ClassificationDTO classification) {
		return classificationService.updateClassification(classification);
	}

	@DeleteMapping("/classification/delete/{id}")
    @Operation(summary = "Delete a Classification", description = "Delete a Classification", tags = { "classification" })
	public String deleteClassification(@PathVariable int id) {
		return classificationService.deleteClassification(id);
	}
}
