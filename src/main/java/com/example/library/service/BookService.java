package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.User;

import java.util.List;

public interface BookService {
    
    List<Book> findAllBooks();
    
    Book findByUser(User user);
    
    void saveBook(Book book);
    
}
