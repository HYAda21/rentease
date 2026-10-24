package com.rentease.rentease.service;

import com.rentease.rentease.entity.Lease;
import com.rentease.rentease.entity.TenantDetails;
import com.rentease.rentease.repository.LeaseReposiitory;
import com.rentease.rentease.repository.TenantDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantDetailsService {

    private final TenantDetailsRepository tenantDetailsRepository;
    private final LeaseReposiitory leaseRepository;

    public TenantDetailsService(
            TenantDetailsRepository tenantDetailsRepository,
            LeaseReposiitory leaseRepository) {

        this.tenantDetailsRepository = tenantDetailsRepository;
        this.leaseRepository = leaseRepository;
    }

    public TenantDetails createTenantDetails(TenantDetails tenantDetails) {

        Long leaseId = tenantDetails.getLease().getId();

        Lease lease = leaseRepository.findById(leaseId)
                .orElseThrow(() -> new RuntimeException("Lease not found"));

        tenantDetails.setLease(lease);

        return tenantDetailsRepository.save(tenantDetails);
    }

    public List<TenantDetails> getAllTenantDetails() {
        return tenantDetailsRepository.findAll();
    }

    public Optional<TenantDetails> getTenantDetailsById(Long id) {
        return tenantDetailsRepository.findById(id);
    }

    public void deleteTenantDetailsById(Long id) {
        tenantDetailsRepository.deleteById(id);
    }
}