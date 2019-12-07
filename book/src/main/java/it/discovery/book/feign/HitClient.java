package it.discovery.book.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("statistics")
public interface HitClient {
    @GetMapping("hits/{id}")
    int getHitCount(@PathVariable("id") int id);

    @PostMapping("hits/{bookId}")
    void saveHit(@PathVariable("bookId") int bookId);
}
