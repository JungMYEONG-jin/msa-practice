package com.example.catalogs.port.out;

import com.example.catalogs.domain.Catalog;

public interface CatalogSavePort {
    Catalog save(Catalog catalog);
}
