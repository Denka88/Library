package com.example.library.repo;

import com.example.library.model.Book;
import com.example.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {
    
    List<Book> findByUser(User user);

    List<Book> findByAuthor(String author);

    void deleteById(Long id);
    
}
