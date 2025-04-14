package com.example.library.repo;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepoTest {

    @Autowired
    private BookRepo bookRepo;
    
    @Autowired
    private AuthorRepo authorRepo;
    
    @Autowired
    private UserRepo userRepo;
    
    @Test
    void findByUser() {
        Author author = new Author();
        author.setName("Олег");
        author.setSurname("Лего");
        authorRepo.save(author);
        
        User user = new User();
        user.setUsername("Влад");
        userRepo.save(user);
        
        Book book = new Book();
        book.setAuthor(author);
        book.setUser(user);
        
        Book saved = bookRepo.save(book);
        List<Book> found = bookRepo.findByUser(saved.getUser());
        assertThat(found).hasSize(1);
    }

    @Test
    void findByAuthor() {
        Author author = new Author();
        author.setName("Олег");
        author.setSurname("Лего");
        authorRepo.save(author);
        
        User user = new User();
        user.setUsername("Влад");
        userRepo.save(user);
        
        Book book = new Book();
        book.setAuthor(author);
        book.setUser(user);

        Book saved = bookRepo.save(book);
        List<Book> found = bookRepo.findByAuthor(saved.getAuthor());
        assertThat(found).hasSize(1);
    }

    @Test
    void deleteById() {
        Author author = new Author();
        author.setName("Олег");
        author.setSurname("Лего");
        authorRepo.save(author);

        User user = new User();
        user.setUsername("Влад");
        userRepo.save(user);

        Book book = new Book();
        book.setAuthor(author);
        book.setUser(user);

        Book saved = bookRepo.save(book);
        
        bookRepo.deleteById(saved.getId());
        
        Optional<Book> found = bookRepo.findById(saved.getId());
        assertThat(found).isEmpty();
    }

    @Test
    void existsByAuthorId() {
        Author author = new Author();
        author.setName("Олег");
        author.setSurname("Лего");
        authorRepo.save(author);

        User user = new User();
        user.setUsername("Влад");
        userRepo.save(user);

        Book book = new Book();
        book.setAuthor(author);
        book.setUser(user);

        Book saved = bookRepo.save(book);
        
        Boolean isExists = bookRepo.existsByAuthorId(saved.getId());
        
        assertThat(isExists).isTrue();
    }
}