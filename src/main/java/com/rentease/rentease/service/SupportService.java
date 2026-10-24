package com.rentease.rentease.service;


import com.rentease.rentease.entity.Room;
import com.rentease.rentease.entity.Support;
import com.rentease.rentease.entity.SupportStatus;
import com.rentease.rentease.entity.User;
import com.rentease.rentease.repository.RoomRepository;
import com.rentease.rentease.repository.SupportRepository;
import com.rentease.rentease.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportService {

    public final SupportRepository supportRepository;
    public final RoomRepository roomRepository;
    public final UserRepository userRepository;
    public SupportService(SupportRepository supportRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.supportRepository = supportRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;

    }
    public Support CreateSupport(Support support){
        // Get tenant ID from request
        Long tenantId = support.getTenant().getId();

        // Find complete tenant from database
        User tenant = userRepository.findById(tenantId)
                .orElseThrow(() ->
                        new RuntimeException("Tenant not found"));

        // Get room ID from request
        Long roomId = support.getRoom().getId();

        // Find complete room from database
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() ->
                        new RuntimeException("Room not found"));

        // Put complete objects into Support
        support.setTenant(tenant);
        support.setRoom(room);
        return supportRepository.save(support);
    }


    public List<Support> getAllsupports(){
        return supportRepository.findAll();
    }

    public Optional<Support> getSupportById(Long id){
        return supportRepository.findById(id);
    }
    public Support UpdateSupportStatus(Long id, SupportStatus status){
        Support support = supportRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Support ticket not found"));

        support.setStatus(status);

        return supportRepository.save(support);
    }
    public void deleteSupportById(Long id) {
        supportRepository.deleteById(id);
    }
}
