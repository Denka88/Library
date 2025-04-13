package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.repo.BookRepo;
import com.example.library.service.BookService;
import com.example.library.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockitoBean
    private UserService userService;
    
    @MockitoBean
    private BookService bookService;
    
    @MockitoBean
    private BookRepo bookRepo;
    
    @Test
    void getUser() throws Exception{
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();
        
        when(userService.findById(1L)).thenReturn(user);
        when(userService.findUser("testuser")).thenReturn(user);
        
        mvc.perform(MockMvcRequestBuilders.get("/users/user/1").with(user(testUser)))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("/users/user"));
    }

    @Test
    void deleteUser() throws Exception{
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();
        
        when(userService.findById(1L)).thenReturn(user);
        when(userService.findUser("testuser")).thenReturn(user);
        
        mvc.perform(MockMvcRequestBuilders.post("/users/user/1/deleteUser").with(user(testUser)).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/usersList"));
    }

    @Test
    void editUser() throws Exception{
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        when(userService.findById(1L)).thenReturn(user);
        when(userService.findUser("testuser")).thenReturn(user);

        mvc.perform(MockMvcRequestBuilders.post("/users/user/1/editUser").with(user(testUser)).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/usersList"));
    }

    @Test
    void getEditUser() throws Exception{
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        when(userService.findById(1L)).thenReturn(user);
        when(userService.findUser("testuser")).thenReturn(user);
        
        mvc.perform(MockMvcRequestBuilders.get("/users/user/1/editUser").with(user(testUser)))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("/editUser"));
    }
}