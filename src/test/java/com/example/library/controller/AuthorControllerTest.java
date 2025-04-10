package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.model.User;
import com.example.library.service.AuthorService;
import com.example.library.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

    private MockMvc mvc;
    
    @Mock
    private AuthorService authorService;
    @Mock
    private UserService userService;

    private AuthorController authorController;

    @BeforeEach
    void setUp() {
        authorController = new AuthorController(authorService, userService);
        mvc = MockMvcBuilders.standaloneSetup(authorController).build();
    }

    @Test
    void getAuthor() throws Exception {
        Author author = new Author(1L, "Олег", "Лего");

        UserDetails testUser  = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
                .build();

        User user = new User();
        user.setUsername("testuser");

        when(authorService.findAuthorById(1L)).thenReturn(author);
        when(userService.findUser ("testuser")).thenReturn(user);

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