package com.example.library.impl;

import com.example.library.dto.AuthorDto;
import com.example.library.model.Author;
import com.example.library.repo.AuthorRepo;
import com.example.library.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;

    public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepo.findAll();
    }

    @Override
    public void saveAuthor(AuthorDto authorDto) {
        Author newAuthor = new Author();
        newAuthor.setName(authorDto.getName());
        newAuthor.setSurname(authorDto.getSurname());
        authorRepo.save(newAuthor);
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorRepo.findById(id);
    }
}
