package com.arjunan.bookstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PROMOTION")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Promotion extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotion_generator")
	@SequenceGenerator(name="promotion_generator", sequenceName = "promotion_seq", allocationSize=1)
	private int id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
	private Classification classification;
	
	@Column(name = "promotion_code")
	private String promotionCode;
	
	@Column(name = "discount")
	private int discount;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "last_updated_by")
	private String lastUpdatedBy;
}
