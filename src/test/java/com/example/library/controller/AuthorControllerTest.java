package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.model.User;
import com.example.library.service.AuthorService;
import com.example.library.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private AuthorService authorService;
    @MockitoBean
    private UserService userService;

    @Test
    void getAuthor() throws Exception {
        Author author = new Author();
        author.setId(1L);

        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        User user = new User();
        user.setUsername("testuser");

        when(authorService.findAuthorById(1L)).thenReturn(author);
        when(userService.findUser("testuser")).thenReturn(user);

        mvc.perform(MockMvcRequestBuilders.get("/authors/author/1").with(user(testUser)))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("author"))
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("/authors/author"));
    }

    @Test
    void deleteAuthor() throws Exception {
        Author author = new Author();
        author.setId(1L);

        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        User user = new User();
        user.setUsername("testuser");

        when(userService.findUser("testuser")).thenReturn(user);
        when(authorService.findAuthorById(1L)).thenReturn(author);

        mvc.perform(MockMvcRequestBuilders.post("/authors/author/1/deleteAuthor").with(user(testUser)).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/authorsList"));
    }

    @Test
    void editBook() throws Exception {
        Author author = new Author();
        author.setId(1L);

        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        User user = new User();
        user.setUsername("testuser");

        when(userService.findUser("testuser")).thenReturn(user);
        when(authorService.findAuthorById(1L)).thenReturn(author);

        mvc.perform(MockMvcRequestBuilders.post("/authors/author/1/editAuthor").with(user(testUser)).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/authors/author/1"));
    }

    @Test
    void getEditAuthor() throws Exception {
        Author author = new Author();
        author.setId(1L);

        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        User user = new User();
        user.setUsername("testuser");

        when(userService.findUser("testuser")).thenReturn(user);
        when(authorService.findAuthorById(1L)).thenReturn(author);

        mvc.perform(MockMvcRequestBuilders.get("/authors/author/1/editAuthor").with(user(testUser)))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("author"))
                .andExpect(view().name("/editAuthor"));
    }
}