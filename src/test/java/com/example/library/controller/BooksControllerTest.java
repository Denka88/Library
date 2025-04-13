package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import com.example.library.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BooksController.class)
class BooksControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockitoBean
    private BookService bookService;
    
    @MockitoBean
    private AuthorService authorService;
    
    @MockitoBean
    private UserService userService;
    
    @Test
    void addBook() throws Exception{
        Book book = new Book();
        book.setId(1L);

        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        User user = new User();
        user.setUsername("testuser");
        
        when(bookService.findBookById(1L)).thenReturn(book);
        when(userService.findUser("testuser")).thenReturn(user);
        
        mvc.perform(MockMvcRequestBuilders.get("/addBook").with(user(testUser)))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("addBook"));
    }

    @Test
    void saveBook() throws Exception {
        Book book = new Book();
        book.setId(1L);

        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        User user = new User();
        user.setUsername("testuser");

        when(bookService.findBookById(1L)).thenReturn(book);
        when(userService.findUser("testuser")).thenReturn(user);
        
        mvc.perform(MockMvcRequestBuilders.post("/saveBook").with(user(testUser)).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/profile"));
    }

    @Test
    void booksList() throws Exception {
        Author author = new Author();
        author.setId(1L);
        author.setName("Олег");
        author.setSurname("Лего");
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        Book book = new Book();
        book.setId(1L);
        book.setAuthor(author);
        book.setUser(user);

        Page<Book> mockPage = new PageImpl<>(List.of(book), PageRequest.of(0, 5), 1);

        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();


        when(authorService.findAuthorById(1L)).thenReturn(author);
        when(bookService.findPaginated(any(PageRequest.class))).thenReturn(mockPage);
        when(bookService.findBookById(1L)).thenReturn(book);
        when(userService.findUser("testuser")).thenReturn(user);
        
        mvc.perform(MockMvcRequestBuilders.get("/booksList?page=1&?size=5").with(user(testUser)))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("booksList"));
    }
}