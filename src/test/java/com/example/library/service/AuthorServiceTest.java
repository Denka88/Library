package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.repo.AuthorRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepo authorRepo;
    
    @InjectMocks
    private AuthorService authorService;
    
    @Test
    void findAllAuthors() {
        Author author = new Author();
        author.setName("Олег");
        author.setSurname("Лего");
        List<Author> authors = List.of(author);
        
        when(authorRepo.findAll()).thenReturn(authors);
        List<Author> result = authorService.findAllAuthors();
        assertThat(result).hasSize(1);
    }

    @Test
    void saveAuthor() {
    }

    @Test
    void findAuthorById() {
    }

    @Test
    void deleteAuthor() {
    }

    @Test
    void editAuthor() {
    }

    @Test
    void hasBooks() {
    }
}