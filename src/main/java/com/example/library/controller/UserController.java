package com.example.library.controller;

import com.example.library.dto.UserDto;

import com.example.library.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserDto user() {
        return new UserDto();
    }

    @GetMapping("/registration")
    String registration() {
        return "registration";
    }

    @PostMapping("/save")
    String save(@ModelAttribute("user") UserDto userDto) {
        userService.save(userDto);
        return "redirect:/login";
    }

    @GetMapping("/")
    String index(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("user", userService.findUser(user.getUsername()));
        return "index";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    String profile(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("user", userService.findUser(user.getUsername()));
        model.addAttribute("title", "Электронная библиотека - профиль пользователя");
        return "profile";
    }
}
