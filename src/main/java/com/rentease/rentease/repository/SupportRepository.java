package com.rentease.rentease.repository;

import com.rentease.rentease.entity.Support;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SupportRepository extends JpaRepository<Support,Long>{
    List<Support> findByTenantId(Long tenantId);
    List<Support> findByRoomPropertyOwnerId(Long ownerId);
}
