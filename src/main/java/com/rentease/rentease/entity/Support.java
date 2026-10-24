package com.rentease.rentease.entity;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@Entity
public class Support {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime createAt;

    @Enumerated(EnumType.STRING)
    private SupportStatus status;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    @JsonIgnoreProperties({"password"})
    private User tenant;


    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnoreProperties({"property"})
    private Room room;


    public Support() {

    }
    public Support(Long id, String title, String description, LocalDateTime createAt, SupportStatus status, User tenant, Room room) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createAt = createAt;
        this.status = status;
        this.tenant = tenant;
        this.room = room;
    }
}
