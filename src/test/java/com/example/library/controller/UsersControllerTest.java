package com.example.library.controller;

import com.example.library.service.BookService;
import com.example.library.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {

    private MockMvc mvc;

    @Mock
    private UserService userService;

    @Mock
    private BookService bookService;

    private UsersController usersController;

    @BeforeEach
    void setUp() {
        usersController = new UsersController(userService, bookService);
        mvc = MockMvcBuilders.standaloneSetup(usersController).build();
    }

    @Test
    void registration() {
    }

    @Test
    void save() {
    }

    @Test
    void index() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void profile() {
    }

    @Test
    void list() {
    }
}