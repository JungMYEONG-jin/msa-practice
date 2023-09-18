package com.example.catalogs.service;

import com.example.catalogs.domain.Catalog;
import com.example.catalogs.port.in.CatalogService;
import com.example.catalogs.port.out.CatalogFindPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final CatalogFindPort catalogFindPort;

    @Override
    public List<Catalog> getAllCatalogs() {
        List<Catalog> catalogs = catalogFindPort.findAll();
        return catalogs;
    }
}
