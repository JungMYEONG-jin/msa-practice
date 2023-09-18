package com.example.catalogs.adapter.in;

import com.example.catalogs.data.ResponseCatalog;
import com.example.catalogs.domain.Catalog;
import com.example.catalogs.mapper.CatalogResponseMapper;
import com.example.catalogs.port.in.CatalogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog-service")
@RequiredArgsConstructor
public class CatalogController {
    private final Environment environment;
    private final CatalogService catalogService;
    private final CatalogResponseMapper catalogResponseMapper;

    @GetMapping("/health-check")
    public String healthCheck(HttpServletRequest request) {
        return String.format("Working my port is %s", request.getServerPort());
    }

    @GetMapping("/welcome")
    public String welcome() {
        return environment.getProperty("greeting.message");
    }

    @GetMapping("/catalogs")
    public ResponseEntity getAllCatalogs() {
        List<Catalog> catalogs = catalogService.getAllCatalogs();
        List<ResponseCatalog> result = catalogs.stream().map(catalog -> catalogResponseMapper.domainToDto(catalog)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
