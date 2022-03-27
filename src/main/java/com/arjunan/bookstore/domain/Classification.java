package com.arjunan.bookstore.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CLASSIFICATION")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Classification extends BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classification_generator")
	@SequenceGenerator(name="classification_generator", sequenceName = "classification_seq", allocationSize=1)
	private int id;
	
	@OneToMany(mappedBy = "classification", cascade = CascadeType.ALL)
	private List<Book> books = new ArrayList<>();

}
