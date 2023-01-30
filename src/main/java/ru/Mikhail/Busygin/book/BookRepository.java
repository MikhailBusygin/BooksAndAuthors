package ru.Mikhail.Busygin.book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    @Query(value = "SELECT b FROM Book b WHERE b.name LIKE '%' || :keyword || '%'")
    public List<Book> search(@Param("keyword") String keyword);
}
