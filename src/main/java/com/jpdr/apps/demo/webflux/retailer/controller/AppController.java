package com.jpdr.apps.demo.webflux.retailer.controller;

import com.jpdr.apps.demo.webflux.retailer.service.AppService;
import com.jpdr.apps.demo.webflux.retailer.service.dto.RetailerDto;
import com.jpdr.apps.demo.webflux.retailer.service.dto.SectorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AppController {
  
  private final AppService appService;
  
  @GetMapping("/retailers")
  public ResponseEntity<Flux<RetailerDto>> findAllRetailers(){
    return new ResponseEntity<>(appService.findAllRetailers(), HttpStatus.OK);
  }
  
  @GetMapping("/retailers/{retailerId}")
  public ResponseEntity<Mono<RetailerDto>> findRetailerById(@PathVariable(name="retailerId") Integer retailerId){
    return new ResponseEntity<>(appService.findRetailerById(retailerId), HttpStatus.OK);
  }
  
  @PostMapping("/retailers")
  public ResponseEntity<Mono<RetailerDto>> createRetailer(@RequestBody RetailerDto retailerDto){
    return new ResponseEntity<>(appService.createRetailer(retailerDto), HttpStatus.CREATED);
  }
  
  @GetMapping("/sectors")
  public ResponseEntity<Flux<SectorDto>> findAllSectors(){
    return new ResponseEntity<>(appService.findAllSectors(), HttpStatus.OK);
  }
  
  @GetMapping("/sectors/{sectorId}")
  public ResponseEntity<Mono<SectorDto>> findSectorById(@PathVariable(name="sectorId") Integer sectorId){
    return new ResponseEntity<>(appService.findSectorById(sectorId), HttpStatus.OK);
  }
  
  @PostMapping("/sectors")
  public ResponseEntity<Mono<SectorDto>> createSector(@RequestBody SectorDto sectorDto){
    return new ResponseEntity<>(appService.createSector(sectorDto), HttpStatus.CREATED);
  }
  
}
