package com.rentease.rentease.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Room {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;

    private String roomType;

    private Double  rent;

@Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

    @ManyToOne
    @JoinColumn(name = "property_id")
     private Property property;

    public Room(){

    }
    public Room(String roomNumber, String roomType, Double rent, RoomStatus roomStatus) {

        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.rent = rent;
        this.roomStatus = roomStatus;
    }


}
