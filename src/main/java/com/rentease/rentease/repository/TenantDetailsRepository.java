package com.rentease.rentease.repository;

import com.rentease.rentease.entity.TenantDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantDetailsRepository extends JpaRepository<TenantDetails, Long> {
}