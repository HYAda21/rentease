package com.rentease.rentease.repository;

import com.rentease.rentease.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository  extends JpaRepository<Property,Long> {

    List<Property> findByOwnerId(Long ownerId);
   // List<Property> findByOwnerId(Integer ownerId);
}
