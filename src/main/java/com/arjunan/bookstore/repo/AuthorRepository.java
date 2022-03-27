package com.arjunan.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjunan.bookstore.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

}
