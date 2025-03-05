package com.example.library.impl;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.repo.BookRepo;
import com.example.library.service.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    
    private final BookRepo bookRepo;

    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }


    @Override
    public List<Book> findAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public List<Book> findByUser(User user) {
        return bookRepo.findByUser(user);
    }

    @Override
    public void saveBook(BookDto bookDto) {
        Book newBook = new Book();
        newBook.setTitle(bookDto.getTitle());
        newBook.setAuthor(bookDto.getAuthor());
        newBook.setAddedAt(LocalDateTime.now());
        newBook.setUser(bookDto.getUser());
        bookRepo.save(newBook);
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepo.findById(id).orElse(null);
    }


}
