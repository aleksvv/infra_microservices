package it.discovery.book.controller;

import it.discovery.book.dto.BookDTO;
import it.discovery.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    @Value("${library}")
    private String libraryName;

    private final BookRepository bookRepository;

    private final RestTemplate restTemplate;

    @GetMapping("library")
    public String getLibraryName() {
        return libraryName;
    }

    @GetMapping
    public List<BookDTO> findAll() {
        return bookRepository.getBooks()
                .stream()
                .map(book -> new BookDTO(book, getHitCount(book.getId())))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public BookDTO findOne(@PathVariable int id) {
        BookDTO bookDTO = new BookDTO(bookRepository.findBookById(id),
                getHitCount(id));
        saveHit(id);
        return bookDTO;
    }

    private int getHitCount(int id) {
        return restTemplate.getForObject("http://statistics/hit/" + id, Integer.class);
    }

    private void saveHit(int bookId) {
        restTemplate.postForObject("http://statistics/hit/" + bookId,
                null, Void.class);
    }
}
