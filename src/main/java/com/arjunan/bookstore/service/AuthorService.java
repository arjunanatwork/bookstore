package com.arjunan.bookstore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arjunan.bookstore.domain.Author;
import com.arjunan.bookstore.dto.AuthorDTO;
import com.arjunan.bookstore.repo.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	AuthorRepository authorRepo;

	@Autowired
	private ModelMapper modelMapper;

	public AuthorDTO saveAuthor(AuthorDTO author) {
		Author authorInstance = convertToEntity(author);
		authorInstance.setCreatedBy("SYSTEM");
		return convertToDTO(authorRepo.save(authorInstance));
	}

	public List<AuthorDTO> getAuthors() {
		List<Author> authorInstanceList = authorRepo.findAll();
		List<AuthorDTO> authorDTOList = authorInstanceList.stream().map(this::convertToDTO)
				.collect(Collectors.toList());
		return authorDTOList;
	}

	public AuthorDTO getAuthorById(int id) {
		Author authorInstance = authorRepo.findById(id).orElse(null);
		return convertToDTO(authorInstance);
	}

	public String deleteAuthor(int id) {
		authorRepo.deleteById(id);
		return "Author removed !! " + id;
	}

	public AuthorDTO updateAuthor(AuthorDTO author) {

		Author existingAuthor = authorRepo.findById(author.getId()).orElse(null);
		existingAuthor.setName(author.getName());
		return convertToDTO(authorRepo.save(existingAuthor));

	}

	private Author convertToEntity(AuthorDTO authorDTO) {
		Author author = modelMapper.map(authorDTO, Author.class);
		return author;
	}

	private AuthorDTO convertToDTO(Author author) {
		AuthorDTO authorDTO = modelMapper.map(author, AuthorDTO.class);
		return authorDTO;
	}
}
