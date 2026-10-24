package com.rentease.rentease.service;




import com.rentease.rentease.entity.Property;
import com.rentease.rentease.entity.Room;


import com.rentease.rentease.repository.PropertyRepository;
import com.rentease.rentease.repository.RoomRepository;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final PropertyRepository propertyRepository;


    public RoomService(RoomRepository roomRepository, PropertyRepository propertyRepository) {
        this.roomRepository = roomRepository;
        this.propertyRepository = propertyRepository;


    }
    public Room createRoom(Room room){
        Long propertyId = room.getProperty().getId();

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() ->
                        new RuntimeException("Property not found"));

        room.setProperty(property);
        return roomRepository.save(room);
    }



    public List<Room> getAllRoom(){
        return roomRepository.findAll();
    }
    public Optional<Room> getRoomById(Long id){
        return roomRepository.findById(id);
    }
    public void deleteRoomById(Long id){
         roomRepository.deleteById(id);
    }
}
