package com.rentease.rentease.controller;


import com.rentease.rentease.entity.Lease;
import com.rentease.rentease.entity.LeaseStatus;
import com.rentease.rentease.service.LeaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/lease")
public class LeaseController {

    private final LeaseService leaseService;

    public LeaseController(LeaseService leaseService){
        this.leaseService = leaseService;
    }

    @PostMapping
    public Lease createLease(@RequestBody Lease lease){
        return leaseService.createLease(lease);
    }
    @GetMapping
    public List<Lease> getAllLease(){
        return leaseService.getAllLease();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lease> getLeaseById(@PathVariable Long id){
        return leaseService.getLeaseById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}/status")
    public Lease updateLeaseStatus(
            @PathVariable Long id,
            @RequestParam LeaseStatus status) {

        return leaseService.updateLeaseStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteLeaseById(@PathVariable Long id){
        leaseService.deleteLeaseById(id);
        return ResponseEntity.noContent().build();
    }




}
