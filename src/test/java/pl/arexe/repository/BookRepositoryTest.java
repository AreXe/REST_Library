package pl.arexe.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.arexe.entity.Book;

import java.util.List;

class BookRepositoryTest {

    @Test
    void bookListOnInitializeShouldReturnDefaultSizeOf3Books() {
        //when
        int defaultSize = 3;
        List<Book> bookList = BookRepository.getBookList();
        //then
        Assertions.assertEquals(defaultSize, bookList.size());
    }
}