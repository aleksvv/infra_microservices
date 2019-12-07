package it.discovery.book.dto;

import it.discovery.book.domain.Book;
import it.discovery.book.domain.Person;
import lombok.Getter;
import lombok.Setter;

import static java.util.Optional.ofNullable;

@Getter
@Setter
public class BookDTO {
    private int id;

    private String name;

    private int year;

    private int pages;

    private double price;

    private Integer authorId;

    private String hitCount;

    public BookDTO(Book book, String hitCount) {
        this.hitCount = hitCount;
        id = book.getId();
        name = book.getName();
        year = book.getYear();
        pages = book.getPages();
        price = book.getPrice();
        authorId = ofNullable(book.getAuthor()).map(Person::getId).orElse(null);
    }
}
