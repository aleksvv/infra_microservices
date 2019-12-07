package it.discovery.book.controller;

import it.discovery.book.dto.BookDTO;
import it.discovery.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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

    private int getHitCount(int id) {
        return restTemplate.getForObject("http://statistics/hit/" + id, Integer.class);
    }
}
