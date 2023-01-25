package ru.Mikhail.Busygin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Mikhail.Busygin.dao.BookDAO;
import ru.Mikhail.Busygin.models.Book;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping("/showBooks")
    public String showBooks(Model model) {
        model.addAttribute("books", bookDAO.showBooks());
        return "books/showBooks";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.showBook(id));
        return "books/showBook";
    }

    @GetMapping("/newBook")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/newBook";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") Book book) {
        bookDAO.createBook(book);
        return "redirect:/books/showBooks";
    }

    @GetMapping("/{id}/editBook")
    public String editBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.showBook(id));
        return "books/editBook";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookDAO.updateBook(id, book);
        return "redirect:/books/showBooks";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books/showBooks";
    }
}
