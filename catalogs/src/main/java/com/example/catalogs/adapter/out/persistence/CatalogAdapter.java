package com.example.catalogs.adapter.out.persistence;

import com.example.catalogs.domain.Catalog;
import com.example.catalogs.port.out.CatalogFindPort;
import com.example.catalogs.port.out.CatalogSavePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CatalogAdapter implements CatalogFindPort, CatalogSavePort {
    private final CatalogRepository catalogRepository;

    @Override
    public Catalog findByProductId(String productId) {
        return null;
    }

    @Override
    public Catalog save(Catalog catalog) {
        return null;
    }
}
