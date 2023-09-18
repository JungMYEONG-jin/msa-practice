package com.example.catalogs.port.out;

import com.example.catalogs.domain.Catalog;

import java.util.List;

public interface CatalogFindPort {
    Catalog findByProductId(String productId);
    List<Catalog> findAll();
}
