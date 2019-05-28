package pl.arexe.dao;

import pl.arexe.entity.Book;

import javax.ejb.Local;
import java.util.List;

@Local
public interface BookRepositoryDao {

    List<Book> getBookList();
    Book getBookByISBN(Long ISBN);
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(Long ISBN);
}
