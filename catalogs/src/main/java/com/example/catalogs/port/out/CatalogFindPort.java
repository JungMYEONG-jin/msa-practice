package com.example.catalogs.port.out;

import com.example.catalogs.domain.Catalog;

public interface CatalogFindPort {
    Catalog findByProductId(String productId);
}
