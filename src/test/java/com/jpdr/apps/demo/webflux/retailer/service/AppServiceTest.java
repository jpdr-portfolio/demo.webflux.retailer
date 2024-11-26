package com.jpdr.apps.demo.webflux.retailer.service;

import com.jpdr.apps.demo.webflux.eventlogger.component.EventLogger;
import com.jpdr.apps.demo.webflux.retailer.exception.SectorNotFoundException;
import com.jpdr.apps.demo.webflux.retailer.model.Retailer;
import com.jpdr.apps.demo.webflux.retailer.model.Sector;
import com.jpdr.apps.demo.webflux.retailer.repository.RetailerRepository;
import com.jpdr.apps.demo.webflux.retailer.repository.SectorRepository;
import com.jpdr.apps.demo.webflux.retailer.service.dto.RetailerDto;
import com.jpdr.apps.demo.webflux.retailer.service.dto.SectorDto;
import com.jpdr.apps.demo.webflux.retailer.service.impl.AppServiceImpl;
import jakarta.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.jpdr.apps.demo.webflux.retailer.util.TestDataGenerator.getNewRetailerDto;
import static com.jpdr.apps.demo.webflux.retailer.util.TestDataGenerator.getNewSectorDto;
import static com.jpdr.apps.demo.webflux.retailer.util.TestDataGenerator.getRetailer;
import static com.jpdr.apps.demo.webflux.retailer.util.TestDataGenerator.getRetailers;
import static com.jpdr.apps.demo.webflux.retailer.util.TestDataGenerator.getSector;
import static com.jpdr.apps.demo.webflux.retailer.util.TestDataGenerator.getSectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AppServiceTest {
  
  @InjectMocks
  private AppServiceImpl appService;
  
  @Mock
  private RetailerRepository retailerRepository;
  
  @Mock
  private SectorRepository sectorRepository;
  
  @Mock
  private EventLogger eventLogger;
  
  @Test
  @DisplayName("OK - Find Retailer By Id")
  void givenIdWhenFindRetailerByIdThenReturnRetailer(){
    
    Retailer expectedRetailer = getRetailer();
    Sector expectedSector = getSector();
    
    when(this.retailerRepository.findByIdAndIsActiveIsTrue(anyInt()))
      .thenReturn(Mono.just(expectedRetailer));
    when(this.sectorRepository.findByIdAndIsActiveIsTrue(anyInt()))
      .thenReturn(Mono.just(expectedSector));
    
    StepVerifier.create(appService.findRetailerById(1))
      .assertNext(receivedRetailer -> assertRetailer(expectedRetailer, receivedRetailer))
      .expectComplete()
      .verify();
  }
  
  @Test
  @DisplayName("Error - Find By Retailer Id - Not Found")
  void givenNotFoundIdWhenFindRetailerByIdThenReturnError(){
    
    Retailer expectedRetailer = getRetailer();

    when(this.retailerRepository.findByIdAndIsActiveIsTrue(anyInt()))
      .thenReturn(Mono.just(expectedRetailer));
    when(this.sectorRepository.findByIdAndIsActiveIsTrue(anyInt()))
      .thenReturn(Mono.error(new SectorNotFoundException(1)));
    
    StepVerifier.create(appService.findRetailerById(1))
      .expectError(SectorNotFoundException.class)
      .verify();
    
  }
  
  @Test
  @DisplayName("OK - Create Retailer")
  void givenRetailerWhenCreateRetailerThenReturnRetailer(){
    
    RetailerDto requestRetailer = getNewRetailerDto();
    Retailer expectedRetailer = getRetailer();
    Sector expectedSector = getSector();
    
    when(this.sectorRepository.findByIdAndIsActiveIsTrue(anyInt()))
      .thenReturn(Mono.just(expectedSector));
    when(this.retailerRepository.save(any(Retailer.class)))
      .thenReturn(Mono.just(expectedRetailer));
    
    StepVerifier.create(appService.createRetailer(requestRetailer))
      .assertNext(receivedRetailer -> {
        assertNotNull(receivedRetailer.getId());
        assertEquals(expectedRetailer.getName(), receivedRetailer.getName());
        assertEquals(expectedRetailer.getEmail(), receivedRetailer.getEmail());
        assertEquals(expectedRetailer.getAddress(), receivedRetailer.getAddress());
        assertTrue(receivedRetailer.getIsActive());
        assertNotNull(receivedRetailer.getCreationDate());
        assertTrue(StringUtils.isBlank(receivedRetailer.getDeletionDate()));
      })
      .expectComplete()
      .verify();
    
  }
  
  @Test
  @DisplayName("Error - Create Retailer - Null Sector")
  void givenNullSectorWhenCreateRetailerThenReturnError(){
    
    RetailerDto requestRetailer = getNewRetailerDto();
    requestRetailer.setSectorId(null);
    
    StepVerifier.create(appService.createRetailer(requestRetailer))
      .expectError(ValidationException.class)
      .verify();
    
  }
  
  
  @Test
  @DisplayName("Error - Create Retailer - Sector not found")
  void givenNotFoundSectorWhenCreateRetailerThenReturnError(){
    
    RetailerDto requestRetailer = getNewRetailerDto();
    
    when(this.sectorRepository.findByIdAndIsActiveIsTrue(anyInt()))
      .thenReturn(Mono.error(new SectorNotFoundException(1)));
    
    StepVerifier.create(appService.createRetailer(requestRetailer))
      .expectError(ValidationException.class)
      .verify();
  
  }
  
  @Test
  @DisplayName("OK - Find All Retailers")
  void givenRetailersWhenFindRetailersThenReturnRetailers(){
    
    List<Retailer> expectedRetailers = getRetailers();
    Map<Integer, Retailer> expectedRetailersMap = expectedRetailers.stream()
      .collect(Collectors.toMap(Retailer::getId, Function.identity()));
    Sector expectedSector = getSector();
    
    when(retailerRepository.findAllByIsActiveIsTrue())
      .thenReturn(Flux.fromIterable(expectedRetailers));
    when(sectorRepository.findByIdAndIsActiveIsTrue(anyInt()))
      .thenReturn(Mono.just(expectedSector));
    
    StepVerifier.create(appService.findAllRetailers())
      .assertNext(receivedRetailers -> {
        for(RetailerDto receivedRetailer : receivedRetailers){
          assertRetailer(expectedRetailersMap.get(receivedRetailer.getId()),
            receivedRetailer);
        }
      })
      .expectComplete()
      .verify();
    
  }
  
  @Test
  @DisplayName("OK - Find Sector By Id")
  void givenIdWhenFindSectorByIdThenReturnSector(){
    
    Sector expectedSector = getSector();
    
    when(sectorRepository.findByIdAndIsActiveIsTrue(anyInt()))
      .thenReturn(Mono.just(expectedSector));
    
    StepVerifier.create(appService.findSectorById(1))
      .assertNext(receivedSector -> assertSector(expectedSector, receivedSector))
      .expectComplete()
      .verify();
  
  }
  
  @Test
  @DisplayName("Error - Find Sector By Id - Not Found")
  void givenNotFoundIdWhenFindSectorByIdThenReturnError(){
    
    when(sectorRepository.findByIdAndIsActiveIsTrue(anyInt()))
      .thenReturn(Mono.error(new SectorNotFoundException(1)));
    
    StepVerifier.create(appService.findSectorById(1))
      .expectError(SectorNotFoundException.class)
      .verify();
  }
  
  @Test
  @DisplayName("OK - Find All Sectors")
  void givenSectorsWhenFindSectorsThenReturnSectors(){
    
    List<Sector> expectedSectors = getSectors();
    Map<Integer, Sector> expectedSectorsMap = expectedSectors.stream()
      .collect(Collectors.toMap(Sector::getId, Function.identity()));
    
    when(sectorRepository.findAllByIsActiveIsTrue())
      .thenReturn(Flux.fromIterable(expectedSectors));
    
    StepVerifier.create(appService.findAllSectors())
      .assertNext(receivedSectors -> {
        for(SectorDto receivedSector : receivedSectors){
          assertSector(expectedSectorsMap.get(receivedSector.getId()), receivedSector);
        }
      })
      .expectComplete()
      .verify();
  }
  
  @Test
  @DisplayName("OK - Create Sector")
  void givenSectorWhenCreateSectorThenReturnSector(){
    
    SectorDto requestSector = getNewSectorDto();
    Sector expectedSector = getSector();
    
    when(sectorRepository.save(any(Sector.class)))
      .thenReturn(Mono.just(expectedSector));
    
    StepVerifier.create(appService.createSector(requestSector))
      .assertNext(receivedSector -> {
        assertNotNull(receivedSector.getId());
        assertEquals(expectedSector.getName(), receivedSector.getName());
        assertTrue(receivedSector.getIsActive());
        assertNotNull(receivedSector.getCreationDate());
        assertTrue(StringUtils.isBlank(receivedSector.getDeletionDate()));
      })
      .expectComplete()
      .verify();
  }
  
  private static void assertRetailer(Retailer entity, RetailerDto dto){
    assertEquals(entity.getId(), dto.getId());
    assertEquals(entity.getName(), dto.getName());
    assertNull(dto.getSectorId());
    assertEquals(entity.getAddress(), dto.getAddress());
    assertTrue(dto.getIsActive());
    assertNotNull(dto.getCreationDate());
    assertTrue(StringUtils.isBlank(dto.getDeletionDate()));
    assertNotNull(dto.getSectorName());
  }
  
  private static void assertSector(Sector entity, SectorDto dto){
    assertEquals(entity.getId(), dto.getId());
    assertEquals(entity.getName(), dto.getName());
    assertTrue(dto.getIsActive());
    assertNotNull(dto.getCreationDate());
    assertTrue(StringUtils.isBlank(dto.getDeletionDate()));
  }

}
