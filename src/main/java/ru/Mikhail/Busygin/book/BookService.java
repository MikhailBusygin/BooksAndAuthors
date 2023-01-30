package ru.Mikhail.Busygin.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {
    @Autowired
    BookRepository repository;

    public void save(Book customer) {
        repository.save(customer);
    }

    public List<Book> listAll() {
        return (List<Book>) repository.findAll();
    }

    public Book get(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Book> search(String keyword) {
        return repository.search(keyword);
    }
}
