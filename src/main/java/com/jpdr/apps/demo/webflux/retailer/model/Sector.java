package com.jpdr.apps.demo.webflux.retailer.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;

@Data
@Table("sector")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sector {
  
  @Id
  @Column("id")
  Integer id;
  @Column("name")
  String name;
  @Column("is_active")
  Boolean isActive;
  @Column("creation_date")
  OffsetDateTime creationDate;
  @Column("deletion_date")
  OffsetDateTime deletionDate;
  
}
