package com.arjunan.bookstore.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arjunan.bookstore.domain.Author;
import com.arjunan.bookstore.domain.Book;
import com.arjunan.bookstore.domain.Classification;
import com.arjunan.bookstore.domain.Promotion;
import com.arjunan.bookstore.dto.BookRequestDTO;
import com.arjunan.bookstore.dto.BookResponseDTO;
import com.arjunan.bookstore.dto.CheckoutDTO;
import com.arjunan.bookstore.repo.AuthorRepository;
import com.arjunan.bookstore.repo.BookRepository;
import com.arjunan.bookstore.repo.ClassificationRepository;
import com.arjunan.bookstore.repo.PromotionRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepo;

	@Autowired
	AuthorRepository authorRepo;

	@Autowired
	ClassificationRepository classificationRepo;
	
	@Autowired
	PromotionRepository promotionRepo;

	public BookResponseDTO saveBook(BookRequestDTO bookDTO) {
		Optional<Author> authorInstance = authorRepo.findById(bookDTO.getAuthorId());
		Optional<Classification> classificationInstance = classificationRepo.findById(bookDTO.getClassificationId());
		Book bookInstance = convertToEntity(bookDTO);
		bookInstance.setAuthor(authorInstance.get());
		bookInstance.setClassification(classificationInstance.get());
		bookInstance.setCreatedBy("SYSTEM");
		return convertToDTO(bookRepo.save(bookInstance));
	}

	public List<BookResponseDTO> getBooks() {
		List<Book> bookInstanceList = bookRepo.findAll();
		return bookInstanceList.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public BookResponseDTO getBookById(int id) {
		Book bookInstance = bookRepo.findById(id).orElse(null);
		return convertToDTO(bookInstance);
	}

	public String deleteBook(int id) {
		bookRepo.deleteById(id);
		return "Book removed !! " + id;
	}

	public BookResponseDTO updateBook(int id, BookRequestDTO bookDTO) {

		Book existingBook = bookRepo.findById(id).orElse(null);
		Optional<Author> authorInstance = authorRepo.findById(bookDTO.getAuthorId());
		Optional<Classification> classificationInstance = classificationRepo.findById(bookDTO.getClassificationId());
		existingBook.setName(bookDTO.getName());
		existingBook.setDescription(bookDTO.getDescription());
		existingBook.setPrice(bookDTO.getPrice());
		existingBook.setIsbn(bookDTO.getIsbn());
		existingBook.setAuthor(authorInstance.get());
		existingBook.setClassification(classificationInstance.get());
		Book bookInstance = bookRepo.save(existingBook);

		return convertToDTO(bookInstance);

	}

	private Book convertToEntity(BookRequestDTO bookDTO) {
		Book bookInstance = new Book();
		bookInstance.setName(bookDTO.getName());
		bookInstance.setDescription(bookDTO.getDescription());
		bookInstance.setPrice(bookDTO.getPrice());
		bookInstance.setIsbn(bookDTO.getIsbn());
		return bookInstance;
	}

	private BookResponseDTO convertToDTO(Book book) {
		BookResponseDTO bookResponseDTO = new BookResponseDTO();
		bookResponseDTO.setId(book.getId());
		bookResponseDTO.setAuthor(book.getAuthor().getName());
		bookResponseDTO.setClassification(book.getClassification().getName());
		bookResponseDTO.setDescription(book.getDescription());
		bookResponseDTO.setName(book.getName());
		bookResponseDTO.setIsbn(book.getIsbn());
		bookResponseDTO.setPrice(book.getPrice());
		return bookResponseDTO;
	}

	public CheckoutDTO checkout(List<String> bookIds, String promoCode) {
        double totalAmount = 0.00;
        for(String id: bookIds) {
        	Book bookInstance = bookRepo.findById(Integer.parseInt(id)).orElse(null);
            Promotion promotionInstance = promotionRepo.findByPromotionCodeAndClassification(promoCode, bookInstance.getClassification());
            if(promotionInstance != null ) {
            	double discountAmount = (bookInstance.getPrice()) * promotionInstance.getDiscount() / 100 ;
                double discountedAmount =  bookInstance.getPrice() - discountAmount;
                totalAmount= totalAmount + discountedAmount;
            } else {
                totalAmount= totalAmount +  bookInstance.getPrice();
            }
        }
        
        CheckoutDTO checkoutDTO = new CheckoutDTO();
        checkoutDTO.setFinalPrice(totalAmount);
		return checkoutDTO;
	}
}
