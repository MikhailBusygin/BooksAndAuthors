package ru.Mikhail.Busygin.dao;

import org.springframework.stereotype.Component;
import ru.Mikhail.Busygin.models.Book;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {
    private static int BOOK_COUNT;
    private List<Book> books;

    {
        books = new ArrayList<>();

        books.add(new Book(++BOOK_COUNT, "silver fang"));
        books.add(new Book(++BOOK_COUNT, "atomic samurai"));
        books.add(new Book(++BOOK_COUNT, "king"));
    }

    public List<Book> showBooks() {
        return books;
    }

    public Book showBook(int id) {
        return books.stream().filter(book -> book.getId() == id).findAny().orElse(null);
    }

    public void createBook(Book book) {
        book.setId(++BOOK_COUNT);
        books.add(book);
    }

    public void updateBook(int id, Book updatedBook) {
        Book bookToBeUpdated = showBook(id);
        bookToBeUpdated.setName(updatedBook.getName());
    }

    public void deleteBook(int id) {
        books.removeIf(book -> book.getId() == id);
    }
}
