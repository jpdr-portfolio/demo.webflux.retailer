package com.jpdr.apps.demo.webflux.retailer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNull;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RetailerDto implements Serializable {
  
  @JsonInclude(Include.NON_NULL)
  Integer id;
  @NonNull
  String name;
  @JsonInclude(Include.NON_NULL)
  Integer sectorId;
  @JsonInclude(Include.NON_NULL)
  String sectorName;
  @NonNull
  String email;
  @NonNull
  String address;
  @JsonInclude(Include.NON_NULL)
  Boolean isActive;
  @JsonInclude(Include.NON_EMPTY)
  String creationDate;
  @JsonInclude(Include.NON_EMPTY)
  String deletionDate;
  
}
