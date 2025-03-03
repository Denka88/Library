package com.example.library.dto;

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
    private LocalDateTime year;

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

    public LocalDateTime getYear() {
        return year;
    }

    public void setYear(LocalDateTime year) {
        this.year = year;
    }
}
