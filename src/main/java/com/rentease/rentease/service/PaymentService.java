package com.rentease.rentease.service;


import com.rentease.rentease.entity.Lease;
import com.rentease.rentease.entity.Payment;
import com.rentease.rentease.entity.PaymentStatus;
import com.rentease.rentease.entity.User;
import com.rentease.rentease.repository.LeaseReposiitory;
import com.rentease.rentease.repository.PaymentRepository;
import com.rentease.rentease.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final LeaseReposiitory leaseReposiitory;


    public PaymentService(PaymentRepository paymentRepository, LeaseReposiitory leaseReposiitory) {
        this.paymentRepository = paymentRepository;
        this.leaseReposiitory = leaseReposiitory;

    }
    public Payment createpayment(Payment payment){
        Long leaseId = payment.getLease().getId();

        Lease lease = leaseReposiitory.findById(leaseId)
                .orElseThrow(() ->
                        new RuntimeException("Lease not found"));

        payment.setLease(lease);

        return paymentRepository.save(payment);
    }


    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }
    public Optional<Payment> getPaymentById(Long id){
        return paymentRepository.findById(id);
    }
    public void deletePaymentById(Long id){
        paymentRepository.deleteById(id);
    }
    public Payment updatePaymentStatus(Long id, PaymentStatus status) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setPaymentStatus(status);

        if (status == PaymentStatus.Paid) {
            payment.setPaymentDate(LocalDate.now());
        }

        return paymentRepository.save(payment);
    }


}
