package com.example.library.service;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import com.example.library.model.User;

import java.util.List;
import java.util.Optional;

public interface BookService {
    
    List<Book> findAllBooks();
    
    List<Book> findByUser(User user);
    
    void saveBook(BookDto bookDto);
    
    Book findBookById(Long id);
    
}
