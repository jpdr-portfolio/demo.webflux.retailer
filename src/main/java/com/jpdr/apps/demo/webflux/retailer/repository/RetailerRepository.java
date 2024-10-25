package com.jpdr.apps.demo.webflux.retailer.repository;

import com.jpdr.apps.demo.webflux.retailer.model.Retailer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RetailerRepository extends ReactiveCrudRepository<Retailer, Integer> {
  
  Mono<Retailer> findByIdAndIsActiveIsTrue(Integer id);
  Flux<Retailer> findAllByIsActiveIsTrue();
  
}
