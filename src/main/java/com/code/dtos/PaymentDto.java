package com.code.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.code.enums.PaymentMethod;
import com.code.models.Payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PaymentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    @NotNull(message = "Phương thức thanh toán không được để trống")
    private PaymentMethod method;

    @Length(max = 255, message = "Tên phương thức thanh toán không được vượt quá 255 ký tự")
    @NotBlank(message = "Tên phương thức thanh toán không được để trống")
    private String name;

    @NotNull(message = "Trạng thái phương thức thanh toán không được để trống")
    private Boolean is_active;

    public PaymentDto() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public static PaymentDto toDto(Payment payment) {
        PaymentDto dto = new PaymentDto();
        dto.setId(payment.getId());
        dto.setMethod(payment.getMethod());
        dto.setName(payment.getName());
        dto.setIs_active(payment.isActive());
        return dto;
    }

    public static Payment toEntity(PaymentDto dto) {
        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setMethod(dto.getMethod());
        payment.setName(dto.getName());
        payment.setActive(dto.isIs_active());
        return payment;
    }

}