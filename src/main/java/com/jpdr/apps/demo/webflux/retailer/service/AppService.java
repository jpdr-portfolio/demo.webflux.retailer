package com.jpdr.apps.demo.webflux.retailer.service;

import com.jpdr.apps.demo.webflux.retailer.service.dto.RetailerDto;
import com.jpdr.apps.demo.webflux.retailer.service.dto.SectorDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AppService {
  
  Mono<List<RetailerDto>> findAllRetailers();
  Mono<RetailerDto> findRetailerById(Integer retailerId);
  Mono<RetailerDto> createRetailer(RetailerDto retailerDto);
  
  Mono<List<SectorDto>> findAllSectors();
  Mono<SectorDto> findSectorById(Integer retailerId);
  Mono<SectorDto> createSector(SectorDto sectorDto);
  
}
