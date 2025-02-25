package com.example.library.service;

import com.example.library.dto.UserDto;
import com.example.library.model.User;


public interface UserService {
    
    void save(UserDto userdto);
    User findUser(String username);
    
}
