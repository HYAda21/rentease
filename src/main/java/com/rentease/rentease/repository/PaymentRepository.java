package com.rentease.rentease.repository;

import com.rentease.rentease.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByLeaseTenantId(Long tenantId);
    List<Payment> findByLeaseRoomPropertyOwnerId(Long ownerId);
}
