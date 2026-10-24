package com.rentease.rentease.controller;

import com.rentease.rentease.entity.TenantDetails;
import com.rentease.rentease.service.TenantDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenant-details")
public class TenantDetailsController {

    private final TenantDetailsService tenantDetailsService;

    public TenantDetailsController(TenantDetailsService tenantDetailsService) {
        this.tenantDetailsService = tenantDetailsService;
    }

    @PostMapping
    public TenantDetails createTenantDetails(
            @RequestBody TenantDetails tenantDetails) {

        return tenantDetailsService.createTenantDetails(tenantDetails);
    }

    @GetMapping
    public List<TenantDetails> getAllTenantDetails() {
        return tenantDetailsService.getAllTenantDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantDetails> getTenantDetailsById(
            @PathVariable Long id) {

        return tenantDetailsService.getTenantDetailsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTenantDetailsById(
            @PathVariable Long id) {

        tenantDetailsService.deleteTenantDetailsById(id);

        return ResponseEntity.noContent().build();
    }
}