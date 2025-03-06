package com.example.library.repo;

import com.example.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepo extends JpaRepository<Author, Long> {

    Author findById(Long id);

    List<Author> findAll();

    void save(Author newAuthor);
}
