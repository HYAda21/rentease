package com.rentease.rentease.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private LeaseStatus leaseStatus;

    private Double rent;


    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private User tenant;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Lease() {

    }
    public Lease(  LocalDate startDate, LocalDate endDate, LeaseStatus leaseStatus,Double rent, User tenant, Room room ) {

  this.startDate = startDate;
  this.endDate = endDate;
  this.leaseStatus = leaseStatus;
  this.rent = rent;
  this.tenant = tenant;
  this.room = room;
    }
}
