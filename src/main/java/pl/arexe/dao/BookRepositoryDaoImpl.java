package pl.arexe.dao;

import pl.arexe.entity.Book;
import pl.arexe.repository.BookRepository;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
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
         if (getBookByISBN(book.getISBN()) != null){
             throw new IllegalArgumentException("Book with that ISBN already exists");
         }
        BookRepository.getBookList().add(book);
    }

    @Override
    public void updateBook(Book book) {
        Book bookByISBN = getBookByISBN(book.getISBN());
        if (bookByISBN != null) {
            int index = BookRepository.getBookList().indexOf(bookByISBN);
            book.setId(bookByISBN.getId());
            BookRepository.getBookList().set(index, book);
        }
    }

    @Override
    public void deleteBook(Long ISBN) {
        Book bookByISBN = getBookByISBN(ISBN);
        BookRepository.getBookList().remove(bookByISBN);
    }
}
