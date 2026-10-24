package com.rentease.rentease.controller;


import com.rentease.rentease.entity.Payment;

import com.rentease.rentease.entity.PaymentStatus;
import com.rentease.rentease.service.PaymentService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment){
        return paymentService.createpayment(payment);
    }
    @GetMapping
    public List<Payment> getAllPayments(){
        return paymentService.getAllPayments();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id){
        return paymentService.getPaymentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentById(@PathVariable Long id){
        paymentService.deletePaymentById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/status")
    public Payment updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam PaymentStatus status) {

        return paymentService.updatePaymentStatus(id, status);
    }
}
