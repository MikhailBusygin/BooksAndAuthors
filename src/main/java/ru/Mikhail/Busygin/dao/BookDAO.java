package ru.Mikhail.Busygin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.Mikhail.Busygin.models.Book;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showBooks() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book showBook(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void createBook(Book book) {
        jdbcTemplate.update("INSERT INTO book(name) VALUES(?)", book.getName());
    }

    public void updateBook(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET name=? WHERE id=?", updatedBook.getName(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }
}
