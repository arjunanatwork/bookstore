package com.arjunan.bookstore.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public abstract class BaseDomain extends Auditable {

	@Column(name = "name")
	private String name;

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "last_updated_by")
	private String lastUpdatedBy;

}
