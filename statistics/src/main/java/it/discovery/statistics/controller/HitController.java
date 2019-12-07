package it.discovery.statistics.controller;

import it.discovery.statistics.repository.HitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hits")
@RequiredArgsConstructor
public class HitController {

    private final HitRepository hitRepository;

    @GetMapping("{id}")
    public int getHitCount(@PathVariable int id) {
        return hitRepository.countByObjectId(id);
    }
}
