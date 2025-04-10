package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import com.example.library.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    private MockMvc mvc;

    @Mock
    private BookService bookService;

    @Mock
    private UserService userService;

    @Mock
    private AuthorService authorService;

    private BookController bookController;

    @BeforeEach
    void setUp() {
        bookController = new BookController(userService, bookService, authorService);
        mvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void getBook() throws Exception {
        Book book = new Book();
        Author author = new Author();
        author.setId(1L);
        book.setId(1L);
        book.setAuthor(author);

        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        User user = new User();
        user.setUsername("testuser");

        when(bookService.findBookById(1L)).thenReturn(book);
        when(authorService.findAuthorById(1L)).thenReturn(author);
        when(userService.findUser("testuser")).thenReturn(user);

        mvc.perform(MockMvcRequestBuilders.get("/books/book/1").with(user(testUser)))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("/books/book"));
    }

    @Test
    void deleteBook() throws Exception {
        Book book = new Book();
        Author author = new Author();
        author.setId(1L);
        book.setId(1L);
        book.setAuthor(author);

        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        User user = new User();
        user.setUsername("testuser");

        when(bookService.findBookById(1L)).thenReturn(book);
        when(authorService.findAuthorById(1L)).thenReturn(author);
        when(userService.findUser("testuser")).thenReturn(user);

        mvc.perform(MockMvcRequestBuilders.post("/books/book/1/deleteBook").with(user(testUser)).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile"));
    }

    @Test
    void editBook() throws Exception {
        Book book = new Book();
        Author author = new Author();
        author.setId(1L);
        book.setId(1L);
        book.setAuthor(author);

        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        User user = new User();
        user.setUsername("testuser");

        when(bookService.findBookById(1L)).thenReturn(book);
        when(authorService.findAuthorById(1L)).thenReturn(author);
        when(userService.findUser("testuser")).thenReturn(user);

        mvc.perform(MockMvcRequestBuilders.post("/books/book/1/editBook").with(user(testUser)).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books/book/1"));
    }

    @Test
    void getEditBook() throws Exception {
        Book book = new Book();
        Author author = new Author();
        author.setId(1L);
        book.setId(1L);
        book.setAuthor(author);

        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        User user = new User();
        user.setUsername("testuser");

        when(bookService.findBookById(1L)).thenReturn(book);
        when(authorService.findAuthorById(1L)).thenReturn(author);
        when(userService.findUser("testuser")).thenReturn(user);

        mvc.perform(MockMvcRequestBuilders.get("/books/book/1/editBook").with(user(testUser)))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(view().name("/editBook"));
    }
}