package com.rentease.rentease.repository;

import com.rentease.rentease.entity.Payment;
import com.rentease.rentease.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findByPropertyOwnerId(Long ownerId);
}
