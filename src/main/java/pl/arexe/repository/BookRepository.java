package pl.arexe.repository;

import pl.arexe.entity.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BookRepository {
    private static List<Book> bookRepository = new ArrayList<>();

    public static List<Book> getBookList() {
        if (bookRepository.size() == 0) {
            defaultRepository();
        }
        return bookRepository;
    }

    private static void defaultRepository(){

        Book book1 = Book.builder()
                .ISBN(978_2_2554_2140_5L)
                .title("Budynki jedorodzinne")
                .authors(Arrays.asList("Michalak Hanna", "Pyrak Stefan"))
                .releaseDate(2013)
                .pages(320)
                .build();

        Book book2 = Book.builder()
                .ISBN(978_2_1234_5680_3L)
                .title("Joyland")
                .authors(Collections.singletonList("Stephen King"))
                .releaseDate(2013)
                .pages(336)
                .build();

        Book book3 = Book.builder()
                .ISBN(978_8_5541_9124_1L)
                .title("Gra o tron. Pieśń Lodu i Ognia. Tom 1")
                .authors(Collections.singletonList("Martin George R. R."))
                .releaseDate(2011)
                .pages(844)
                .build();

        bookRepository.addAll(Arrays.asList(book1, book2, book3));
    }

}
