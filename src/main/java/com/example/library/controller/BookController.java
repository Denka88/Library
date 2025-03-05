package com.example.library.controller;


import com.example.library.model.Book;
import com.example.library.service.UserService;
import org.springframework.security.core.userdetails.User;
import com.example.library.service.BookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("books/book/{bookId:\\d+}")
public class BookController {
    
    private final UserService userService;
    private final BookService bookService;

    public BookController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }
    
    @ModelAttribute("book")
    private Book book(@PathVariable long bookId) {
        return this.bookService.findBookById(bookId);
    }
    
    @GetMapping
    public String getBook(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", userService.findUser(user.getUsername()));
        return "/books/book";
    }
    
    @PostMapping("/deleteBook")
    public String deleteBook(@ModelAttribute("book") Book book){
        this.bookService.deleteBook(book.getId());
        return "redirect:/booksList";
    }
    
    
}
