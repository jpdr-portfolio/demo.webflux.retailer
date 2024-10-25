package com.jpdr.apps.demo.webflux.retailer.service;

import com.jpdr.apps.demo.webflux.retailer.service.dto.RetailerDto;
import com.jpdr.apps.demo.webflux.retailer.service.dto.SectorDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AppService {
  
  Flux<RetailerDto> findAllRetailers();
  Mono<RetailerDto> findRetailerById(Integer retailerId);
  Mono<RetailerDto> createRetailer(RetailerDto retailerDto);
  
  Flux<SectorDto> findAllSectors();
  Mono<SectorDto> findSectorById(Integer retailerId);
  Mono<SectorDto> createSector(SectorDto sectorDto);
  
}
