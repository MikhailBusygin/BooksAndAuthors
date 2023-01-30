package ru.Mikhail.Busygin.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RequestMapping("/books")
@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/")
    public ModelAndView home() {
        List<Book> books = bookService.listAll();
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @RequestMapping("/new")
    public String newBookForm(Map<String, Object> model) {
        Book book = new Book();
        model.put("book", book);
        return "new_book";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @RequestMapping("/edit")
    public ModelAndView editBookForm(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView("edit_book");
        Book book = bookService.get(id);
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @RequestMapping("/delete")
    public String deleteBookForm(@RequestParam long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Book> result = bookService.search(keyword);
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("result", result);
        return modelAndView;
    }
}
