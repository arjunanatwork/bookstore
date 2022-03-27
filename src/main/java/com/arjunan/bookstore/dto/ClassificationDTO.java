package com.arjunan.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassificationDTO {

	private int id;
	private String name;

}
