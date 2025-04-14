package com.example.library.repo;

import com.example.library.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepoTest {

    @Autowired
    private UserRepo userRepo;
    
    @Test
    void findByUsername() {
        User user = new User();
        user.setUsername("Влад");
        
        User saved = userRepo.save(user);
        
        User found = userRepo.findByUsername(saved.getUsername());
        assertThat(found).isEqualTo(saved);
    }

    @Test
    void existsByUsername() {
        User user = new User();
        user.setUsername("Влад");

        User saved = userRepo.save(user);
        
        Boolean isExists = userRepo.existsByUsername(saved.getUsername());
        assertThat(isExists).isTrue();
    }
}