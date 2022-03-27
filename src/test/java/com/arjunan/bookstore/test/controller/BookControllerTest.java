package com.arjunan.bookstore.test.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.arjunan.bookstore.controller.BookController;
import com.arjunan.bookstore.dto.BookResponseDTO;
import com.arjunan.bookstore.dto.CheckoutDTO;
import com.arjunan.bookstore.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

	@Mock
	private BookService bookService;

	@InjectMocks
	private BookController bookController;

	@Autowired
	private MockMvc mockMvc;

	private BookResponseDTO book;
	
	private CheckoutDTO checkoutDTO;

	private List<BookResponseDTO> bookList;

	@BeforeEach
	public void setup() {
		book = new BookResponseDTO(1, "Book1", "Test Desc", "Arjunan", "COMIC", 10.00, "123ada");
		checkoutDTO = new CheckoutDTO(12.00);
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}

	@AfterEach
	void tearDown() {
		book = null;
		checkoutDTO = null;
	}

	@Test
	public void GetMappingOfAllBook() throws Exception {
		when(bookService.getBooks()).thenReturn(bookList);
		mockMvc.perform(MockMvcRequestBuilders.get("/books").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(book))).andDo(MockMvcResultHandlers.print());
		verify(bookService).getBooks();
		verify(bookService, times(1)).getBooks();
	}

	@Test
	public void GetMappingOfBookShouldReturnRespectiveProduct() throws Exception {
		when(bookService.getBookById(book.getId())).thenReturn(book);
		mockMvc.perform(get("/book/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(book)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void PostMappingOfBook() throws Exception {
		when(bookService.saveBook(any())).thenReturn(book);
		mockMvc.perform(post("/book").contentType(MediaType.APPLICATION_JSON).content(asJsonString(book)))
				.andExpect(status().isCreated());
		verify(bookService, times(1)).saveBook(any());
	}

	@Test
	public void DeleteMappingUrlAndIdThenShouldReturnDeletedProduct() throws Exception {
		when(bookService.deleteBook(book.getId())).thenReturn("Book removed !! " + book.getId());
		mockMvc.perform(
				delete("/book/delete/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(book)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void CheckOutShouldReturnAPrice() throws Exception {
		when(bookService.checkout(anyList(),anyString())).thenReturn(checkoutDTO);
		mockMvc.perform(
				post("/book/checkout").contentType(MediaType.APPLICATION_JSON).queryParam("books", "1","2").queryParam("promoCode", "TEST10"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
