package com.jpdr.apps.demo.webflux.retailer.util;

import com.jpdr.apps.demo.webflux.retailer.model.Retailer;
import com.jpdr.apps.demo.webflux.retailer.model.Sector;
import com.jpdr.apps.demo.webflux.retailer.service.dto.RetailerDto;
import com.jpdr.apps.demo.webflux.retailer.service.dto.SectorDto;
import com.jpdr.apps.demo.webflux.retailer.service.mapper.RetailerMapper;
import com.jpdr.apps.demo.webflux.retailer.service.mapper.SectorMapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Stream;

public class TestDataGenerator {
  
  public static final String RETAILER_NAME = "Retailer Name";
  public static final String ADDRESS = "123 Street, City, State";
  public static final String SECTOR_NAME = "Sector Name";
  public static final String EMAIL = "johnsmith@mail.com";
  public static final String CREATION_DATE = "2024-10-14T10:39:45.732446-03:00";
  
  public static List<RetailerDto> getRetailerDtos(){
    return Stream.iterate(1, n -> n+1)
      .limit(3)
      .map(TestDataGenerator::getRetailerDto)
      .toList();
  }
  
  public static RetailerDto getRetailerDto(){
    return RetailerMapper.INSTANCE.entityToDto(getRetailer(1));
  }
  
  public static RetailerDto getRetailerDto(int i){
    return RetailerMapper.INSTANCE.entityToDto(getRetailer(i));
  }
  
  public static RetailerDto getNewRetailerDto(){
    RetailerDto retailerDto = RetailerMapper.INSTANCE.entityToDto(getRetailer());
    retailerDto.setId(null);
    retailerDto.setSectorId(1);
    retailerDto.setSectorName(null);
    retailerDto.setIsActive(null);
    retailerDto.setCreationDate(null);
    retailerDto.setDeletionDate(null);
    return retailerDto;
  }
  
  public static List<Retailer> getRetailers(){
    return Stream.iterate(1, n -> n+1)
      .limit(3)
      .map(TestDataGenerator::getRetailer)
      .toList();
  }
  
  public static Retailer getRetailer(){
    return getRetailer(1);
  }
  
  public static Retailer getRetailer(int retailerId){
    return Retailer.builder()
      .id(retailerId)
      .name(RETAILER_NAME)
      .email(EMAIL)
      .address(ADDRESS)
      .sectorId(1)
      .isActive(true)
      .creationDate(OffsetDateTime.parse(CREATION_DATE))
      .deletionDate(null)
      .build();
  }
  
  public static List<SectorDto> getSectorDtos(){
    return Stream.iterate(1 , n -> n+1)
      .limit(3)
      .map(TestDataGenerator::getSectorDto)
      .toList();
  }
  
  
  public static SectorDto getSectorDto(){
    return getSectorDto(1);
  }
  
  public static SectorDto getSectorDto(int sectorId){
    return SectorMapper.INSTANCE.entityToDto(getSector(sectorId));
  }
  
  
  public static SectorDto getNewSectorDto(){
    return SectorDto.builder()
      .id(null)
      .name(SECTOR_NAME)
      .isActive(null)
      .creationDate(null)
      .deletionDate(null)
      .build();
  }
  
  
  public static List<Sector> getSectors(){
    return Stream.iterate(1, n -> n + 1)
      .limit(3)
      .map(TestDataGenerator::getSector)
      .toList();
  }
  
  public static Sector getSector(){
    return getSector(1);
  }
  
  public static Sector getSector(int sectorId){
    return Sector.builder()
      .id(sectorId)
      .name(SECTOR_NAME)
      .isActive(true)
      .creationDate(OffsetDateTime.parse(CREATION_DATE))
      .deletionDate(null)
      .build();
  }
  
}
