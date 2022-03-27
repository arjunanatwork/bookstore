package com.arjunan.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arjunan.bookstore.dto.AuthorDTO;
import com.arjunan.bookstore.service.AuthorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Author", description = "The Author API")
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@PostMapping("/author")
    @Operation(summary = "Save an Author", description = "Save an Author", tags = { "author" })
	public AuthorDTO addAuthor(@RequestBody AuthorDTO author) {
		return authorService.saveAuthor(author);
	}

	@GetMapping("/authors")
    @Operation(summary = "Get all Author", description = "Get all Author", tags = { "author" })
	public List<AuthorDTO> findAllAuthors() {
		return authorService.getAuthors();
	}

	@GetMapping("/author/{id}")
    @Operation(summary = "Get an Author By ID", description = "Get an Author By ID", tags = { "author" })
	public AuthorDTO findAuthorById(@PathVariable int id) {
		return authorService.getAuthorById(id);
	}

	@PutMapping("/author/update")
    @Operation(summary = "Update an Author", description = "Update an Author", tags = { "author" })
	public AuthorDTO updateAuthor(@RequestBody AuthorDTO author) {
		return authorService.updateAuthor(author);
	}

	@DeleteMapping("/author/delete/{id}")
    @Operation(summary = "Delete an Author", description = "Delete an Author", tags = { "author" })
	public String deleteAuthor(@PathVariable int id) {
		return authorService.deleteAuthor(id);
	}

}
