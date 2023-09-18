package com.example.catalogs.port.in;

import com.example.catalogs.adapter.out.persistence.CatalogEntity;

import java.util.List;

public interface CatalogService {
    List<CatalogEntity> getAllCatalogs();
}
