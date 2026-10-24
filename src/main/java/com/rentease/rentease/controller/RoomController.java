package com.rentease.rentease.controller;


import com.rentease.rentease.entity.Room;
import com.rentease.rentease.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    @PostMapping
    public Room crateRoom(@RequestBody Room room){
        return roomService.createRoom(room);
    }
    @GetMapping
    public List<Room> getAllRoom(){
        return roomService.getAllRoom();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomByid(@PathVariable Long id) {
        return roomService.getRoomById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable  Long id){
        roomService.deleteRoomById(id);
        return ResponseEntity.noContent().build();
        }

}
