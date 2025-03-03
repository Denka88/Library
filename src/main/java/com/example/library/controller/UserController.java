package com.example.library.controller;

import com.example.library.dto.UserDto;
import com.example.library.impl.UserServiceImpl;
import com.example.library.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final UserServiceImpl userServiceImpl;


    public UserController(UserService userService, UserServiceImpl userServiceImpl) {
        this.userService = userService;
        this.userServiceImpl = userServiceImpl;
    }

    @ModelAttribute("user")
    public UserDto user() {
        return new UserDto();
    }

    @GetMapping("/registration")
    String registration(@ModelAttribute("userDto") UserDto userDto) {
        return "registration";
    }

    @PostMapping("/save")
    String save(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (userServiceImpl.isUsernameAvailable(userDto.getUsername())) {
            System.out.println("Пользователь есть!");
            bindingResult.rejectValue("username","error.username", "Имя пользователя уже занято!");
            return "registration";
        }

        userServiceImpl.save(userDto);
        return "redirect:/login";
    }
    
    @GetMapping("/")
    String index(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("user", userService.findUser(user.getUsername()));
        } else {
            model.addAttribute("user", null);
        }
        return "index";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    String profile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userService.findUser(user.getUsername()));
        model.addAttribute("title", "Электронная библиотека - профиль пользователя");
        return "profile";
    }
}
