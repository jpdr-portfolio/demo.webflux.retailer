package com.jpdr.apps.demo.webflux.retailer.service.impl;

import com.jpdr.apps.demo.webflux.retailer.exception.RetailerNotFoundException;
import com.jpdr.apps.demo.webflux.retailer.exception.SectorNotFoundException;
import com.jpdr.apps.demo.webflux.retailer.model.Retailer;
import com.jpdr.apps.demo.webflux.retailer.model.Sector;
import com.jpdr.apps.demo.webflux.retailer.repository.RetailerRepository;
import com.jpdr.apps.demo.webflux.retailer.repository.SectorRepository;
import com.jpdr.apps.demo.webflux.retailer.service.AppService;
import com.jpdr.apps.demo.webflux.retailer.service.dto.RetailerDto;
import com.jpdr.apps.demo.webflux.retailer.service.dto.SectorDto;
import com.jpdr.apps.demo.webflux.retailer.service.mapper.RetailerMapper;
import com.jpdr.apps.demo.webflux.retailer.service.mapper.SectorMapper;
import com.jpdr.apps.demo.webflux.retailer.util.InputValidator;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {
  
  private final RetailerRepository retailerRepository;
  private final SectorRepository sectorRepository;
  
  @Override
  public Mono<List<RetailerDto>> findAllRetailers() {
    log.debug("findAllRetailers");
    return this.retailerRepository.findAllByIsActiveIsTrue()
      .flatMap(retailer ->
        Mono.zip(
          Mono.just(retailer),
          findSectorById(retailer.getSectorId())))
      .map(tuple -> {
        RetailerDto retailerDto = RetailerMapper.INSTANCE.entityToDto(tuple.getT1());
        retailerDto.setSectorName(tuple.getT2().getName());
        return retailerDto;
      })
      .doOnNext(retailer -> log.debug(retailer.toString()))
      .collectList();
  }
  
  @Override
  @Cacheable(key = "#retailerId", value = "retailers", sync = true)
  public Mono<RetailerDto> findRetailerById(Integer retailerId) {
    log.debug("findRetailerById");
    return this.retailerRepository.findByIdAndIsActiveIsTrue(retailerId)
      .switchIfEmpty(Mono.error(new RetailerNotFoundException(retailerId)))
      .flatMap(retailer ->
        Mono.zip(
          Mono.just(retailer),
          findSectorById(retailer.getSectorId())))
      .map(tuple -> {
        RetailerDto retailerDto = RetailerMapper.INSTANCE.entityToDto(tuple.getT1());
        retailerDto.setSectorName(tuple.getT2().getName());
        return retailerDto;
      })
      .doOnNext(retailer -> log.debug(retailer.toString()));
  }
  
  @Override
  @Transactional
  public Mono<RetailerDto> createRetailer(RetailerDto retailerDto) {
    log.debug("createRetailer");
    return Mono.from(validateRetailer(retailerDto))
      .map(validRetailer -> {
        Retailer retailerToSave = RetailerMapper.INSTANCE.dtoToEntity(validRetailer);
        retailerToSave.setCreationDate(OffsetDateTime.now());
        retailerToSave.setIsActive(true);
        return retailerToSave;
      })
      .flatMap(this.retailerRepository::save)
      .doOnNext(retailer -> log.debug(retailer.toString()))
      .map(RetailerMapper.INSTANCE::entityToDto);
  }
  
  @Override
  public Mono<List<SectorDto>> findAllSectors() {
    log.debug("findAllSectors");
    return this.sectorRepository.findAllByIsActiveIsTrue()
      .doOnNext(sector -> log.debug(sector.toString()))
      .map(SectorMapper.INSTANCE::entityToDto)
      .collectList();
  }
  
  @Override
  @Cacheable(key = "#sectorId", value = "sectors", sync = true)
  public Mono<SectorDto> findSectorById(Integer sectorId) {
    log.debug("findSectorById");
    return this.sectorRepository.findByIdAndIsActiveIsTrue(sectorId)
      .switchIfEmpty(Mono.error(new SectorNotFoundException(sectorId)))
      .doOnNext(sector -> log.debug(sector.toString()))
      .map(SectorMapper.INSTANCE::entityToDto);
  }
  
  @Override
  @Transactional
  public Mono<SectorDto> createSector(SectorDto sectorDto) {
    log.debug("createSector");
    return Mono.from(validateSector(sectorDto))
      .map(validSector -> {
        Sector sectorToSave = SectorMapper.INSTANCE.dtoToEntity(validSector);
          sectorToSave.setCreationDate(OffsetDateTime.now());
          sectorToSave.setIsActive(true);
          return sectorToSave;
        })
      .flatMap(sectorRepository::save)
      .doOnNext(sector -> log.debug(sector.toString()))
      .map(SectorMapper.INSTANCE::entityToDto);
  }
  
  private Mono<RetailerDto> validateRetailer(RetailerDto retailerDto){
    return Mono.from(validateRetailerData(retailerDto))
      .flatMap(validRetailer -> Mono.zip(
        Mono.just(validRetailer),
        Mono.from(findSectorById(validRetailer.getSectorId()))))
      .onErrorResume(ex -> {
          if(ex instanceof SectorNotFoundException){
            return Mono.error(new ValidationException(ex.getMessage()));
          }
          return Mono.error(ex);
        })
      .map(Tuple2::getT1);
  }
  
  private Mono<RetailerDto> validateRetailerData(RetailerDto retailerDto){
    return Mono.just(retailerDto)
      .filter(retailer -> (retailer != null &&
        InputValidator.isValidName(retailer.getName()) &&
        InputValidator.isValidEmail(retailer.getEmail()) &&
        InputValidator.isValidName(retailer.getAddress()) &&
        retailer.getSectorId() != null
      ))
      .switchIfEmpty(Mono.error(new ValidationException("Invalid retailer value.")));
  }
  
  private Mono<SectorDto> validateSector(SectorDto sectorDto){
    return Mono.just(sectorDto)
      .filter(sector -> (InputValidator.isValidName(sectorDto.getName())))
      .switchIfEmpty(Mono.error(new ValidationException("Invalid sector value.")));
  }
  
}
