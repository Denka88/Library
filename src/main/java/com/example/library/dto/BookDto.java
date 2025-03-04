package com.example.library.dto;

import com.example.library.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class BookDto {
    
    @NotEmpty
    @Size(max = 30, message = "Не более 30 символов")
    private String title;
    
    @NotEmpty
    private String author;
    
    @NotEmpty
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
