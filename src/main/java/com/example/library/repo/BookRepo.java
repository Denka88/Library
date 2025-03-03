package com.example.library.repo;

import com.example.library.model.Book;
import com.example.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
    
    Book findByUser(User user);
    
    Book findBookById(Long id);
    
}
