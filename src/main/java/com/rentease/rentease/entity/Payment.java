package com.rentease.rentease.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter @Getter
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dueDate;
    private LocalDate paymentDate;

    private Double amount;


    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "lease_id")
    private Lease lease;

    public  Payment(){

    }
    public Payment( LocalDate dueDate, LocalDate paymentDate, double amount, PaymentStatus paymentStatus, Lease lease)
    {
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.lease = lease;

    }
}
