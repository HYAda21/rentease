package com.rentease.rentease.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name;
    private String address;
    private String city;
    private String state;
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")

    private User owner;

    @OneToMany(mappedBy = "property")
    @JsonIgnore
    private List<Room> rooms =new ArrayList<>();
    public Property() {
    }
    public Property(String name, String address, String city, String state, String description) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.description = description;
    }
}
