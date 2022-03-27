package com.arjunan.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjunan.bookstore.domain.Classification;
import com.arjunan.bookstore.domain.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion,Integer>  {

	Promotion findByPromotionCodeAndClassification(String promoCode, Classification classification);
}
