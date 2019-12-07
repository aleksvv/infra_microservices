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

    private int authorId;

    private int hitCount;

    public BookDTO(Book book, int hitCount) {
        this.hitCount = hitCount;
        id = book.getId();
        name = book.getName();
        year = book.getYear();
        pages = book.getPages();
        price = book.getPrice();
        authorId = ofNullable(book.getAuthor()).map(Person::getId).orElse(0);
    }
}
