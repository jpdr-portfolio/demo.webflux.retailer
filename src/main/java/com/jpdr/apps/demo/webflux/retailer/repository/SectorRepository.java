package com.jpdr.apps.demo.webflux.retailer.repository;

import com.jpdr.apps.demo.webflux.retailer.model.Sector;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SectorRepository extends ReactiveCrudRepository<Sector, Integer> {
  
  Mono<Sector> findByIdAndIsActiveIsTrue(Integer id);
  Flux<Sector> findAllByIsActiveIsTrue();
  
}
