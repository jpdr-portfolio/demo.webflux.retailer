package com.jpdr.apps.demo.webflux.retailer.service.mapper;

import com.jpdr.apps.demo.webflux.retailer.model.Retailer;
import com.jpdr.apps.demo.webflux.retailer.service.dto.RetailerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = java.util.Objects.class)
public interface RetailerMapper {
  
  RetailerMapper INSTANCE = Mappers.getMapper(RetailerMapper.class);
  
  @Mapping(target = "id", expression = "java(null)")
  @Mapping(target = "isActive", expression = "java(null)")
  @Mapping(target = "creationDate", expression = "java(null)")
  @Mapping(target = "deletionDate", expression = "java(null)")
  Retailer dtoToEntity(RetailerDto dto);
  
  @Mapping(target = "sectorId", expression = "java(null)")
  @Mapping(target = "sectorName", expression = "java(null)")
  @Mapping(target = "creationDate", expression = "java(Objects.toString(entity.getCreationDate(),null))" )
  @Mapping(target = "deletionDate", expression = "java(Objects.toString(entity.getDeletionDate(),null))" )
  RetailerDto entityToDto(Retailer entity);
  
}
