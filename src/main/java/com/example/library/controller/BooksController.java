package com.example.library.controller;


import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import org.springframework.security.core.userdetails.User;
import com.example.library.service.BookService;
import com.example.library.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BooksController {

    private final BookService bookService;
    private final UserService userService;

    public BooksController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }
    
    @GetMapping("/addBook")
    String addBook(@AuthenticationPrincipal User user, Model model) {
        if (user != null){
            model.addAttribute("user", userService.findUser(user.getUsername()));
        }
        else {
            model.addAttribute("user", null);
        }
        model.addAttribute("books", bookService.findAllBooks());
        model.addAttribute("book", new BookDto());
        return "addBook";
    }
    
    @PostMapping("/saveBook")
    String saveBook(@ModelAttribute("book") BookDto bookDto, @AuthenticationPrincipal User user, BindingResult bindingResult) {
        
        bookDto.setUser(userService.findUser(user.getUsername()));
        
        if (bindingResult.hasErrors()) {
            return "addBook";
        }
        
        bookService.saveBook(bookDto);
        
        return "redirect:/profile";
    }
    
    @GetMapping("/booksList")
    String booksList(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userService.findUser(user.getUsername()));
        model.addAttribute("books", bookService.findAllBooks());
        return "booksList";
    }

    @PostMapping("deleteBook")
    public String deleteBook(@ModelAttribute("book") Book book){
        this.bookService.deleteBook(book.getId());
        return "redirect:/booksList";
    }

}
