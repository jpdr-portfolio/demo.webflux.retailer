package com.jpdr.apps.demo.webflux.retailer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpdr.apps.demo.webflux.retailer.service.AppService;
import com.jpdr.apps.demo.webflux.retailer.service.dto.RetailerDto;
import com.jpdr.apps.demo.webflux.retailer.service.dto.SectorDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.List;

import static com.jpdr.apps.demo.webflux.retailer.util.TestDataGenerator.getNewRetailerDto;
import static com.jpdr.apps.demo.webflux.retailer.util.TestDataGenerator.getNewSectorDto;
import static com.jpdr.apps.demo.webflux.retailer.util.TestDataGenerator.getRetailerDto;
import static com.jpdr.apps.demo.webflux.retailer.util.TestDataGenerator.getRetailerDtos;
import static com.jpdr.apps.demo.webflux.retailer.util.TestDataGenerator.getSectorDto;
import static com.jpdr.apps.demo.webflux.retailer.util.TestDataGenerator.getSectorDtos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class AppControllerTest {
  
  @Autowired
  private WebTestClient webTestClient;
  @MockBean
  private AppService appService;
  @Autowired
  private ObjectMapper objectMapper;
  
  @Test
  @DisplayName("OK - Find Retailer By Id")
  void givenRetailerWhenFindRetailerByIdThenReturnRetailer(){
    
    RetailerDto expectedRetailer = getRetailerDto();
    
    when(appService.findRetailerById(anyInt()))
      .thenReturn(Mono.just(expectedRetailer));
    
    FluxExchangeResult<RetailerDto> exchangeResult = this.webTestClient.get()
      .uri("/retailers" + "/" + 1)
      .exchange()
      .expectHeader()
        .contentType(MediaType.APPLICATION_JSON)
      .expectStatus()
        .isOk()
      .returnResult(RetailerDto.class);
    
    StepVerifier.create(exchangeResult.getResponseBody())
      .assertNext(receivedRetailer -> assertEquals(expectedRetailer, receivedRetailer))
      .expectComplete()
      .verify();
  
  }
  
  @Test
  @DisplayName("OK - Find Retailers")
  void givenRetailersWhenFindRetailersThenReturnRetailers() throws IOException{
    
    List<RetailerDto> expectedRetailers = getRetailerDtos();
    String expectedBody = objectMapper.writeValueAsString(expectedRetailers);
    
    when(appService.findAllRetailers())
      .thenReturn(Mono.just(expectedRetailers));
    
    FluxExchangeResult<String> exchangeResult = this.webTestClient.get()
      .uri("/retailers")
      .exchange()
      .expectHeader()
        .contentType(MediaType.APPLICATION_JSON)
      .expectStatus()
        .isOk()
      .returnResult(String.class);
    
    StepVerifier.create(exchangeResult.getResponseBody())
      .assertNext(receivedBody -> assertEquals(expectedBody, receivedBody))
      .expectComplete()
      .verify();
    
  }
  
  @Test
  @DisplayName("OK - Create Retailer")
  void givenRetailerWhenCreateRetailerThenReturnRetailer(){
    
    RetailerDto requestRetailer = getNewRetailerDto();
    RetailerDto expectedRetailer = getRetailerDto();
    
    when(appService.createRetailer(any(RetailerDto.class)))
      .thenReturn(Mono.just(expectedRetailer));
    
    FluxExchangeResult<RetailerDto> exchangeResult = this.webTestClient.post()
      .uri("/retailers")
      .accept(MediaType.APPLICATION_JSON)
      .bodyValue(requestRetailer)
      .exchange()
      .expectHeader()
        .contentType(MediaType.APPLICATION_JSON)
      .expectStatus()
        .isCreated()
      .returnResult(RetailerDto.class);
    
    StepVerifier.create(exchangeResult.getResponseBody())
      .assertNext(receivedRetailer -> assertEquals(expectedRetailer, receivedRetailer))
      .expectComplete()
      .verify();
    
  }
  
  
  
  
  
  
  @Test
  @DisplayName("OK - Find Sector By Id")
  void givenSectorWhenFindSectorByIdThenReturnSector(){
    
    SectorDto expectedSector = getSectorDto();
    
    when(appService.findSectorById(anyInt()))
      .thenReturn(Mono.just(expectedSector));
    
    FluxExchangeResult<SectorDto> exchangeResult = this.webTestClient.get()
      .uri("/sectors" + "/" + 1)
      .exchange()
      .expectHeader()
      .contentType(MediaType.APPLICATION_JSON)
      .expectStatus()
      .isOk()
      .returnResult(SectorDto.class);
    
    StepVerifier.create(exchangeResult.getResponseBody())
      .assertNext(receivedSector -> assertEquals(expectedSector, receivedSector))
      .expectComplete()
      .verify();
    
  }
  
  @Test
  @DisplayName("OK - Find Sectors")
  void givenSectorsWhenFindSectorsThenReturnSectors() throws IOException {
    
    List<SectorDto> expectedSectors = getSectorDtos();
    String expectedBody = objectMapper.writeValueAsString(expectedSectors);
    
    when(appService.findAllSectors())
      .thenReturn(Mono.just(expectedSectors));
    
    FluxExchangeResult<String> exchangeResult = this.webTestClient.get()
      .uri("/sectors")
      .exchange()
      .expectHeader()
        .contentType(MediaType.APPLICATION_JSON)
      .expectStatus()
        .isOk()
      .returnResult(String.class);
    
    StepVerifier.create(exchangeResult.getResponseBody())
      .assertNext(receivedBody -> assertEquals(expectedBody, receivedBody))
      .expectComplete()
      .verify();
    
  }
  
  @Test
  @DisplayName("OK - Create Sector")
  void givenSectorWhenCreateSectorThenReturnSector(){
    
    SectorDto requestSector = getNewSectorDto();
    SectorDto expectedSector = getSectorDto();
    
    when(appService.createSector(any(SectorDto.class)))
      .thenReturn(Mono.just(expectedSector));
    
    FluxExchangeResult<SectorDto> exchangeResult = this.webTestClient.post()
      .uri("/sectors")
      .accept(MediaType.APPLICATION_JSON)
      .bodyValue(requestSector)
      .exchange()
      .expectHeader()
        .contentType(MediaType.APPLICATION_JSON)
      .expectStatus()
        .isCreated()
      .returnResult(SectorDto.class);
    
    StepVerifier.create(exchangeResult.getResponseBody())
      .assertNext(receivedSector -> assertEquals(expectedSector, receivedSector))
      .expectComplete()
      .verify();
    
  }
  
  
}


