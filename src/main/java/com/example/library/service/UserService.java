package com.example.library.service;

import com.example.library.dto.UserDto;
import com.example.library.model.User;

import java.util.List;


public interface UserService {
    
    void save(UserDto userdto);
    User findUser(String username);
    
    
    List<User> findAllUsers();

    boolean isUsernameAvailable(String username);
}
