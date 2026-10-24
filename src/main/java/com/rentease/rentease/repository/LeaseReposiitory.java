package com.rentease.rentease.repository;

import com.rentease.rentease.entity.Lease;
import com.rentease.rentease.entity.LeaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaseReposiitory extends JpaRepository<Lease,Long> {
    List<Lease> findByTenantId(Long tenantId);

    List<Lease> findByRoomPropertyOwnerId(Long ownerId);

    List<Lease> findByRoomPropertyOwnerIdAndLeaseStatus(
            Long ownerId,
            LeaseStatus leaseStatus);
}
