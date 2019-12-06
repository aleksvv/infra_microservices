package it.discovery.statistics.repository;

import it.discovery.statistics.domain.Hit;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HitRepository extends PagingAndSortingRepository<Hit, Integer> {
}
