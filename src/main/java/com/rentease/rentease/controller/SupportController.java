package com.rentease.rentease.controller;

import com.rentease.rentease.entity.Support;
import com.rentease.rentease.entity.SupportStatus;
import com.rentease.rentease.service.SupportService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support")
public class SupportController {
    private final SupportService supportService;

    public SupportController(SupportService supportService) {
        this.supportService = supportService;
    }

    @PostMapping
    public Support createSupport(@RequestBody Support support) {
        return supportService.CreateSupport(support);
    }

    @GetMapping
    public List<Support> getAllSupports() {
        return supportService.getAllsupports();
    }



    @GetMapping("/{id}")
    public ResponseEntity<Support> getSupportById(@PathVariable Long id) {

        return supportService.getSupportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public Support updateSupportStatus(
            @PathVariable Long id,
            @RequestParam SupportStatus status) {

        return supportService.UpdateSupportStatus(id, status);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupportById(@PathVariable Long id) {

        supportService.deleteSupportById(id);

        return ResponseEntity.noContent().build();
    }
}
