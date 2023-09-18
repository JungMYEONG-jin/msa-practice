package com.example.catalogs.port.in;

import com.example.catalogs.domain.Catalog;

import java.util.List;

public interface CatalogService {
    List<Catalog> getAllCatalogs();
}
