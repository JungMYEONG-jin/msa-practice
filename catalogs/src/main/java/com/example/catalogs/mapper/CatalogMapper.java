package com.example.catalogs.mapper;

import com.example.catalogs.adapter.out.persistence.CatalogEntity;
import com.example.catalogs.data.CatalogDto;
import com.example.catalogs.domain.Catalog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CatalogMapper {
    @Mapping(target = "userId", ignore = true)
    CatalogDto domainToDto(Catalog catalog);
    CatalogEntity domainToEntity(Catalog catalog);
    Catalog entityToDomain(CatalogEntity catalogEntity);
}
