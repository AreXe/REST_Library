package pl.arexe.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Builder
public class Book implements Serializable {

    private int id;
    private Long ISBN;
    private String title;
    private List<String> authors;
    private Integer releaseDate;
    private Integer pages;

    private static final AtomicInteger count = new AtomicInteger(0);

    public Book() {
    }

    public Book(int id, Long ISBN, String title, List<String> authors, Integer releaseDate, Integer pages) {
        this.id = count.incrementAndGet();
        this.ISBN = ISBN;
        this.title = title;
        this.authors = authors;
        this.releaseDate = releaseDate;
        this.pages = pages;
    }
}
