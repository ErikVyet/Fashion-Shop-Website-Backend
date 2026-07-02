package com.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    public boolean existsByName(String name);

}