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
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AppController {
  
  private final AppService appService;
  
  @GetMapping("/retailers")
  public Mono<ResponseEntity<List<RetailerDto>>> findAllRetailers(){
    return this.appService.findAllRetailers()
      .map(retailers -> new ResponseEntity<>(retailers, HttpStatus.OK));
  }
  
  @GetMapping("/retailers/{retailerId}")
  public Mono<ResponseEntity<RetailerDto>> findRetailerById(@PathVariable(name="retailerId") Integer retailerId){
    return this.appService.findRetailerById(retailerId)
      .map(retailer -> new ResponseEntity<>(retailer, HttpStatus.OK));
  }
  
  @PostMapping("/retailers")
  public Mono<ResponseEntity<RetailerDto>> createRetailer(@RequestBody RetailerDto retailerDto){
    return this.appService.createRetailer(retailerDto)
      .map(retailer -> new ResponseEntity<>(retailer, HttpStatus.CREATED));
  }
  
  @GetMapping("/sectors")
  public Mono<ResponseEntity<List<SectorDto>>> findAllSectors(){
    return this.appService.findAllSectors()
      .map(sectors -> new ResponseEntity<>(sectors, HttpStatus.OK));
  }
  
  @GetMapping("/sectors/{sectorId}")
  public Mono<ResponseEntity<SectorDto>> findSectorById(
    @PathVariable(name="sectorId") Integer sectorId){
    return this.appService.findSectorById(sectorId)
      .map(sector -> new ResponseEntity<>(sector, HttpStatus.OK));
  }
  
  @PostMapping("/sectors")
  public Mono<ResponseEntity<SectorDto>> createSector(@RequestBody SectorDto sectorDto){
    return this.appService.createSector(sectorDto)
      .map(sector -> new ResponseEntity<>(sector, HttpStatus.CREATED));
  }
  
}
