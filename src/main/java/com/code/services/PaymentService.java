package com.code.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.dtos.PaymentDto;
import com.code.models.Payment;
import com.code.repositories.PaymentRepository;

@Service
public class PaymentService {

    protected final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional(readOnly = true)
    public List<PaymentDto> readPayments() {
        return paymentRepository.findAll().stream().map(PaymentDto::toDto).toList();
    }

    @Transactional(readOnly = true)
    public PaymentDto getPayment(int id) {
        try {
            return paymentRepository.findById(id).map(PaymentDto::toDto).orElse(null);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Transactional
    public boolean createPayment(PaymentDto paymentDto) {
        try {
            if (this.paymentRepository.existsById(paymentDto.getId())) {
                return false;
            }
            paymentRepository.save(PaymentDto.toEntity(paymentDto));
            return true;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean deletePayment(int id) {
        try {
            if (!this.paymentRepository.existsById(id)) {
                return false;
            }
            paymentRepository.deleteById(id);
            return true;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean updatePayment(PaymentDto paymentDto) {
        try {
            if (!this.paymentRepository.existsById(paymentDto.getId())) {
                return false;
            }
            paymentRepository.save(PaymentDto.toEntity(paymentDto));
            return true;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean updatePaymentActive(int id, boolean active) {
        try {
            Payment payment = this.paymentRepository.findById(id).orElse(null);
            if (payment == null) {
                return false;
            }
            payment.setActive(active);
            this.paymentRepository.save(payment);
            return true;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

}