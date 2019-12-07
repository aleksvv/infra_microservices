package it.discovery.statistics.controller;

import it.discovery.statistics.domain.Hit;
import it.discovery.statistics.repository.HitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("hits")
@RequiredArgsConstructor
public class HitController {

    private final HitRepository hitRepository;

    @GetMapping("{id}")
    public int getHitCount(@PathVariable int id) {
        return hitRepository.countByObjectId(id);
    }

    @PostMapping("{objectId}")
    public void save(@PathVariable int objectId) {
        Hit hit = new Hit();
        hit.setObjectId(objectId);
        hit.setViewed(LocalDateTime.now());
        hitRepository.save(hit);
    }
}
