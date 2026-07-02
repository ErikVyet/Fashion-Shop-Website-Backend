package com.code.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.dtos.PaymentDto;
import com.code.dtos.ResponseMap;
import com.code.services.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/fazhion/api/v1/payment")
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

    protected final PaymentService paymentService;

    @Value("${app.api.key}")
    protected String key;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseMap> readPayments(@RequestHeader("key") String apiKey) {
        ResponseMap result = new ResponseMap();
        if (key.isBlank() || key == null) {
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage("Hệ thống hiện đang gặp lỗi. Vui lòng thử lại sau");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
        if (!apiKey.equals(this.key)) {
            result.setStatus(HttpStatus.UNAUTHORIZED.value());
            result.setMessage("Bạn không có quyền truy cập vào đây");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }

        result.setStatus(HttpStatus.OK.value());
        result.setData(this.paymentService.readPayments());;
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMap> findPayment(@RequestHeader("key") String apiKey, @PathVariable int id) {
        ResponseMap result = new ResponseMap();
        if (key.isBlank() || key == null) {
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage("Hệ thống hiện đang gặp lỗi. Vui lòng thử lại sau");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
        if (!apiKey.equals(this.key)) {
            result.setStatus(HttpStatus.UNAUTHORIZED.value());
            result.setMessage("Bạn không có quyền truy cập vào đây");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }

        PaymentDto paymentDto = this.paymentService.getPayment(id);
        if (paymentDto != null) {
            result.setStatus(HttpStatus.OK.value());
            result.setData(paymentDto);
            return ResponseEntity.ok(result);
        }
        else {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setMessage("Không tìm thấy phương thức thanh toán");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMap> createPayment(@RequestHeader("key") String apiKey, @RequestBody @Valid PaymentDto paymentDto) {
        ResponseMap result = new ResponseMap();
        if (key.isBlank() || key == null) {
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage("Hệ thống hiện đang gặp lỗi. Vui lòng thử lại sau");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
        if (!apiKey.equals(this.key)) {
            result.setStatus(HttpStatus.UNAUTHORIZED.value());
            result.setMessage("Bạn không có quyền truy cập vào đây");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }

        if (this.paymentService.createPayment(paymentDto)) {
            result.setStatus(HttpStatus.CREATED.value());
            result.setMessage("Tạo phương thức thanh toán thành công");
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        else {
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage("Tạo phương thức thanh toán thất bại");
            result.setErrorFields(Map.of("id", "Mã phương thức thanh toán bị trùng"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMap> deletePayment(@RequestHeader("key") String apiKey, @PathVariable int id) {
        ResponseMap result = new ResponseMap();
        if (key.isBlank() || key == null) {
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage("Hệ thống hiện đang gặp lỗi. Vui lòng thử lại sau");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
        if (!apiKey.equals(this.key)) {
            result.setStatus(HttpStatus.UNAUTHORIZED.value());
            result.setMessage("Bạn không có quyền truy cập vào đây");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }

        if (this.paymentService.deletePayment(id)) {
            result.setStatus(HttpStatus.OK.value());
            result.setMessage("Thành công xóa phương thức thanh toán");
            return ResponseEntity.ok(result);
        }
        else {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setMessage("Không tìm thấy phương thức thanh toán");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseMap> updatePayment(@RequestHeader("key") String apiKey, @RequestBody @Valid PaymentDto paymentDto) {
        ResponseMap result = new ResponseMap();
        if (key.isBlank() || key == null) {
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage("Hệ thống hiện đang gặp lỗi. Vui lòng thử lại sau");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
        if (!apiKey.equals(this.key)) {
            result.setStatus(HttpStatus.UNAUTHORIZED.value());
            result.setMessage("Bạn không có quyền truy cập vào đây");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }

        if (this.paymentService.updatePayment(paymentDto)) {
            result.setStatus(HttpStatus.OK.value());
            result.setMessage("Thành công cập nhật phương thức thanh toán");
            return ResponseEntity.ok(result);
        }
        else {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setMessage("Không tìm thấy phương thức thanh toán");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    @PatchMapping("/update-active")
    public ResponseEntity<ResponseMap> updatePaymentActive(@RequestHeader("key") String apiKey, @RequestBody PaymentDto paymentDto) {
        ResponseMap result = new ResponseMap();
        if (key.isBlank() || key == null) {
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage("Hệ thống hiện đang gặp lỗi. Vui lòng thử lại sau");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
        if (!apiKey.equals(this.key)) {
            result.setStatus(HttpStatus.UNAUTHORIZED.value());
            result.setMessage("Bạn không có quyền truy cập vào đây");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }

        if (this.paymentService.updatePaymentActive(paymentDto.getId(), paymentDto.isIs_active())) {
            result.setStatus(HttpStatus.OK.value());
            result.setMessage("Thành công cập nhật trạng thái phương thức thanh toán");
            return ResponseEntity.ok(result);
        }
        else {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setMessage("Không tìm thấy phương thức thanh toán");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

}