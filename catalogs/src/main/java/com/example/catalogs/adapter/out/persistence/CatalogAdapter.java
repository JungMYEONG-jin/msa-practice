package com.example.catalogs.adapter.out.persistence;

import com.example.catalogs.domain.Catalog;
import com.example.catalogs.mapper.CatalogMapper;
import com.example.catalogs.port.out.CatalogFindPort;
import com.example.catalogs.port.out.CatalogSavePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CatalogAdapter implements CatalogFindPort, CatalogSavePort {
    private final CatalogRepository catalogRepository;
    private final CatalogMapper catalogMapper;

    @Override
    public Catalog findByProductId(String productId) {
        return catalogRepository.findByProductId(productId).map(it -> catalogMapper.entityToDomain(it)).orElse(null);
    }

    @Override
    public List<Catalog> findAll() {
        return catalogRepository.findAll().stream().map(it -> catalogMapper.entityToDomain(it)).collect(Collectors.toList());
    }

    @Override
    public Catalog save(Catalog catalog) {
        return catalogMapper.entityToDomain(catalogRepository.save(catalogMapper.domainToEntity(catalog)));
    }
}
