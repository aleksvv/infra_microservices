package it.discovery.book.controller;

import it.discovery.book.dto.BookDTO;
import it.discovery.book.feign.HitClient;
import it.discovery.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    @Value("${library}")
    private String libraryName;

    private final BookRepository bookRepository;

    private final HitClient hitClient;

    @GetMapping("library")
    public String getLibraryName() {
        return libraryName;
    }

    @GetMapping
    public List<BookDTO> findAll() {
        return bookRepository.getBooks()
                .stream()
                .map(book -> new BookDTO(book, hitClient.getHitCount(book.getId())))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public BookDTO findOne(@PathVariable int id) {
        //TODO handle 404
        BookDTO bookDTO = new BookDTO(bookRepository.findBookById(id),
                hitClient.getHitCount(id));
        CompletableFuture.runAsync(() -> hitClient.saveHit(id));
        return bookDTO;
    }

}
