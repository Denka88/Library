package com.example.library.service;

import com.example.library.dto.AuthorDto;
import com.example.library.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAllAuthors();

    void saveAuthor(AuthorDto authorDto);

    Author findAuthorById(Long id);

    void deleteAuthor(Long id);

    void editAuthor(Author author);

    boolean hasBooks(Long id);
}
