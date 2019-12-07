package it.discovery.book.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import it.discovery.book.feign.HitClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final HitClient hitClient;

    @HystrixCommand(fallbackMethod = "getDefaultHitCount",
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "5")
            })
    public String getHitCount(int bookId) {
        return hitClient.getHitCount(bookId);
    }

    public String getDefaultHitCount(int bookId,
                                     Throwable t) {
        return "N/A";
    }
}
