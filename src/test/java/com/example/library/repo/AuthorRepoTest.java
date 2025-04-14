package com.example.library.repo;

import com.example.library.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AuthorRepoTest {

    @Autowired
    private AuthorRepo authorRepo;
    
    @Test
    void deleteById() {
        Author author = new Author();
        author.setName("Олег");
        author.setSurname("Лего");
        
        Author savedAuthor = authorRepo.save(author);
        
        authorRepo.deleteById(savedAuthor.getId());
        
        Optional<Author> found = authorRepo.findById(savedAuthor.getId());
        assertThat(found).isEmpty();
    }
}