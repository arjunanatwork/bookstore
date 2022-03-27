package com.arjunan.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjunan.bookstore.domain.Classification;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification,Integer> {

}
