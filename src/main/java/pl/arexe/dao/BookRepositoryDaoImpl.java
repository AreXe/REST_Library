package pl.arexe.dao;

import pl.arexe.entity.Book;
import pl.arexe.repository.BookRepository;

import java.util.List;

public class BookRepositoryDaoImpl implements BookRepositoryDao {

    @Override
    public List<Book> getBookList() {
        return BookRepository.getBookList();
    }

    @Override
    public Book getBookByISBN(Long ISBN) {
        return BookRepository.getBookList().stream()
                .filter(book -> ISBN.equals(book.getISBN()))
                .findAny()
                .orElse(null);
    }

    @Override
    public void addBook(Book book) {
        BookRepository.getBookList().add(book);
    }

    @Override
    public void updateBook(Book book) {
        Book bookByISBN = getBookByISBN(book.getISBN());
        if (bookByISBN != null) {
            int index = BookRepository.getBookList().indexOf(bookByISBN);
            BookRepository.getBookList().set(index, book);
        }
    }

    @Override
    public void deleteBook(Long ISBN) {
        Book bookByISBN = getBookByISBN(ISBN);
        BookRepository.getBookList().remove(bookByISBN);
    }
}
