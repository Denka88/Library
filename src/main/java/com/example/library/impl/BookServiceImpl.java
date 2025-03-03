package com.example.library.impl;

import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.repo.BookRepo;
import com.example.library.service.BookService;
import org.springframework.stereotype.Service;

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
    public Book findByUser(User user) {
        return bookRepo.findByUser(user);
    }

    @Override
    public void saveBook(Book book) {
        Book newBook = new Book();
        newBook.setTitle(book.getTitle());
        newBook.setAuthor(book.getAuthor());
        newBook.setYear(book.getYear());
        newBook.setUser(book.getUser());
        bookRepo.save(newBook);
    }
}
