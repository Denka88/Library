package com.example.library.controller;


import com.example.library.config.SecurityConfig;
import com.example.library.model.User;
import com.example.library.service.BookService;
import com.example.library.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsersController.class)
@Import(SecurityConfig.class)
class UsersControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockitoBean
    private UserService userService;
    
    @MockitoBean
    private BookService bookService;
    
    @Test
    void registration() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    void save() throws Exception {
        User user = new User();
        user.setId(1L);
        
        when(userService.findById(1L)).thenReturn(user);
        
        mvc.perform(MockMvcRequestBuilders.post("/save").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void index() throws Exception{
        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        
        when(userService.findById(1L)).thenReturn(user);
        when(userService.findUser("testuser")).thenReturn(user);
        
        mvc.perform(MockMvcRequestBuilders.get("/").with(user(testUser)))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
                
    }   

    @Test
    void profile() throws Exception {
        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        when(userService.findById(1L)).thenReturn(user);
        when(userService.findUser("testuser")).thenReturn(user);
        
        mvc.perform(MockMvcRequestBuilders.get("/profile").with(user(testUser)))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"));
    }

    @Test
    void list() throws Exception {
        UserDetails testUser = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("password")
                .roles("ADMIN")
                .build();

        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        when(userService.findById(1L)).thenReturn(user);
        when(userService.findUser("testuser")).thenReturn(user);
        
        mvc.perform(MockMvcRequestBuilders.get("/usersList").with(user(testUser)))
                .andExpect(status().isOk())
                .andExpect(view().name("usersList"));
    }
}