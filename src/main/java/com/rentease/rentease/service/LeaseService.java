package com.rentease.rentease.service;


import com.rentease.rentease.entity.*;
import com.rentease.rentease.repository.LeaseReposiitory;
import com.rentease.rentease.repository.RoomRepository;
import com.rentease.rentease.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaseService {
    private final LeaseReposiitory leaseRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public LeaseService(LeaseReposiitory leaseRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.leaseRepository = leaseRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

        public Lease createLease(Lease lease){
            Long tenantId = lease.getTenant().getId();

            User tenant = userRepository.findById(tenantId)
                    .orElseThrow(() ->
                            new RuntimeException("Tenant not found"));

            // Get the real room from database
            Long roomId = lease.getRoom().getId();

            Room room = roomRepository.findById(roomId)
                    .orElseThrow(() ->
                            new RuntimeException("Room not found"));

            if (room.getRoomStatus() == RoomStatus.Occupid) {
                throw new RuntimeException("Room is already occupied");
            }

            // Set complete objects into Lease
            lease.setTenant(tenant);
            lease.setRoom(room);

            if (lease.getLeaseStatus() == null)
            { lease.setLeaseStatus(LeaseStatus.Active); }

            if (lease.getLeaseStatus() == LeaseStatus.Active) {
                room.setRoomStatus(RoomStatus.Occupid);
                roomRepository.save(room);
            }

            return leaseRepository.save(lease);

    }
    public Lease updateLeaseStatus(Long leaseId, LeaseStatus status) {

        Lease lease = leaseRepository.findById(leaseId)
                .orElseThrow(() ->
                        new RuntimeException("Lease not found"));

        lease.setLeaseStatus(status);

        Room room = lease.getRoom();

        if (status == LeaseStatus.Active) {
            room.setRoomStatus(RoomStatus.Occupid);
        } else {
            room.setRoomStatus(RoomStatus.Available);
        }

        roomRepository.save(room);

        return leaseRepository.save(lease);
    }


    public List<Lease> getAllLease(){
        return leaseRepository.findAll();
    }
    public Optional<Lease> getLeaseById(Long id){
        return leaseRepository.findById(id);
    }
    public void deleteLeaseById(Long id){
        leaseRepository.deleteById(id);
    }
}
