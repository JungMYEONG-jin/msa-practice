package com.example.catalogs.mapper;

import com.example.catalogs.data.ResponseCatalog;
import com.example.catalogs.domain.Catalog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogResponseMapper {
    ResponseCatalog domainToDto(Catalog catalog);
}
