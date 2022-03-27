package com.arjunan.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjunan.bookstore.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

}
