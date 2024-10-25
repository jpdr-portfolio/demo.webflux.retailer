package com.jpdr.apps.demo.webflux.retailer.service.mapper;

import com.jpdr.apps.demo.webflux.retailer.model.Sector;
import com.jpdr.apps.demo.webflux.retailer.service.dto.SectorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = java.util.Objects.class)
public interface SectorMapper {
  
  SectorMapper INSTANCE = Mappers.getMapper(SectorMapper.class);
  
  @Mapping(target = "id", expression = "java(null)")
  @Mapping(target = "isActive", expression = "java(null)")
  @Mapping(target = "creationDate", expression = "java(null)")
  @Mapping(target = "deletionDate", expression = "java(null)")
  Sector dtoToEntity(SectorDto dto);
  
  @Mapping(target = "creationDate", expression = "java(Objects.toString(entity.getCreationDate(),null))" )
  @Mapping(target = "deletionDate", expression = "java(Objects.toString(entity.getDeletionDate(),null))" )
  SectorDto entityToDto(Sector entity);
  
}
