package com.arjunan.bookstore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arjunan.bookstore.domain.Classification;
import com.arjunan.bookstore.dto.ClassificationDTO;
import com.arjunan.bookstore.repo.ClassificationRepository;

@Service
public class ClassificationService {

	@Autowired
	ClassificationRepository classificationRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	public ClassificationDTO saveClassification(ClassificationDTO classification) {
		Classification classificationInstance = convertToEntity(classification);
		classificationInstance.setCreatedBy("SYSTEM");
		return convertToDTO(classificationRepo.save(classificationInstance));
	}

	public List<ClassificationDTO> getClassifications() {
		List<Classification> classificationInstanceList = classificationRepo.findAll();
		List<ClassificationDTO> classificationDTOList = classificationInstanceList.stream().map(this::convertToDTO)
				.collect(Collectors.toList());
		return classificationDTOList;
	}

	public ClassificationDTO getClassificationById(int id) {
		Classification classificationInstance = classificationRepo.findById(id).orElse(null);
		return convertToDTO(classificationInstance);
	}

	public String deleteClassification(int id) {
		classificationRepo.deleteById(id);
		return "Classification removed !! " + id;
	}

	public ClassificationDTO updateClassification(ClassificationDTO classification) {

		Classification existingAuthor = classificationRepo.findById(classification.getId()).orElse(null);
		existingAuthor.setName(classification.getName());
		return convertToDTO(classificationRepo.save(existingAuthor));

	}
	
	private Classification convertToEntity(ClassificationDTO classificationDTO) {
		Classification classification = modelMapper.map(classificationDTO, Classification.class);
		return classification;
	}

	private ClassificationDTO convertToDTO(Classification classification) {
		ClassificationDTO classificationDTO = modelMapper.map(classification, ClassificationDTO.class);
		return classificationDTO;
	}
}
