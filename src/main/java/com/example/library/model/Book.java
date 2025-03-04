package com.example.library.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Locale;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String author;
    private LocalDateTime addedAt;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    

    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy", new Locale("ru"));
        return addedAt.format(formatter);
    }

    public Book(Long id, String title, String author, LocalDateTime addedAt, User user) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.addedAt = addedAt;
        this.user = user;
    }

    public Book() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime year) {
        this.addedAt = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
