package com.arjunan.bookstore.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arjunan.bookstore.dto.BookRequestDTO;
import com.arjunan.bookstore.dto.BookResponseDTO;
import com.arjunan.bookstore.dto.CheckoutDTO;
import com.arjunan.bookstore.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Book", description = "The Book API")
public class BookController {

	@Autowired
	BookService bookService;

	@PostMapping("/book")
    @Operation(summary = "Save a Book", description = "Save a Book", tags = { "book" })
	@ResponseStatus(code = HttpStatus.CREATED)
	public BookResponseDTO addBook(@RequestBody BookRequestDTO book) {
		return bookService.saveBook(book);
	}

	@GetMapping("/books")
    @Operation(summary = "Get all Books", description = "Get all Books", tags = { "book" })
	@ResponseStatus(code = HttpStatus.OK)
	public List<BookResponseDTO> findAllBooks() {
		return bookService.getBooks();
	}

	@GetMapping("/book/{id}")
    @Operation(summary = "Find a Book by Id", description = "Find a Book by Id", tags = { "book" })
	@ResponseStatus(code = HttpStatus.OK)
	public BookResponseDTO findBookById(@PathVariable int id) {
		return bookService.getBookById(id);
	}

	@PutMapping("/book/update/{id}")
    @Operation(summary = "Update a Book", description = "Update a Book", tags = { "book" })
	@ResponseStatus(code = HttpStatus.OK)
	public BookResponseDTO updateBook(@PathVariable int id, @RequestBody BookRequestDTO book) {
		return bookService.updateBook(id, book);
	}

	@DeleteMapping("/book/delete/{id}")
    @Operation(summary = "Delete a Book", description = "Delete a Book", tags = { "book" })
	@ResponseStatus(code = HttpStatus.OK)
	public String deleteBook(@PathVariable int id) {
		return bookService.deleteBook(id);
	}

	@PostMapping("/book/checkout")
    @Operation(summary = "Checkout Books", description = "Delete a Book", tags = { "book" })
	public CheckoutDTO checkout(@RequestParam String books, @RequestParam(required = false) String promoCode) {
		List<String> bookIds = Arrays.asList(books.split(","));

		return bookService.checkout(bookIds, promoCode);
	}
}
