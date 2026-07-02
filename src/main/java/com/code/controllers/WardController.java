package com.code.controllers;

import java.util.List;
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

import com.code.dtos.ResponseMap;
import com.code.dtos.WardDto;
import com.code.services.WardService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/fazhion/api/v1/ward")
@CrossOrigin(origins = "http://localhost:5173")
public class WardController {

    protected final WardService wardService;

    @Value("${app.api.key}")
    protected String key;    

    public WardController(WardService wardService) {
        this.wardService = wardService;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseMap> readWards(@RequestHeader("key") String apiKey) {
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

        List<WardDto> wards = this.wardService.readWards();
        result.setStatus(HttpStatus.OK.value());
        result.setData(wards);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{code}")
    public ResponseEntity<ResponseMap> getWard(@RequestHeader("key") String apiKey, @PathVariable String code) {
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

        WardDto ward = this.wardService.getWard(code);
        if (ward != null) {
            result.setStatus(HttpStatus.OK.value());
            result.setData(ward);
            return ResponseEntity.ok(result);
        }
        else {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setMessage("Không tìm thấy thông tin phường");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMap> insertWard(@RequestHeader("key") String apiKey, @RequestBody @Valid WardDto wardDto) {
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

        if (this.wardService.createWard(wardDto)) {
            result.setStatus(HttpStatus.CREATED.value());
            result.setMessage("Thành công thêm thông tin phường mới.");
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        else {
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage("Thông tin phường không hợp lệ. Vui lòng kiểm tra lại.");
            result.setErrorFields(Map.of("code", "Mã phường đã tồn tại"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<ResponseMap> deleteWard(@RequestHeader("key") String apiKey, @PathVariable String code) {
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

        if (this.wardService.deleteWard(code)) {
            result.setStatus(HttpStatus.OK.value());
            result.setMessage("Thành công xóa thông tin phường");
            return ResponseEntity.ok(result);
        }
        else {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setMessage("Không tìm thấy thông tin phường");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseMap> updateWard(@RequestHeader("key") String apiKey, @RequestBody @Valid WardDto wardDto) {
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

        if (this.wardService.updateWard(wardDto)) {
            result.setStatus(HttpStatus.OK.value());
            result.setMessage("Thành công cập nhật thông tin phường");
            return ResponseEntity.ok(result);
        }
        else {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setMessage("Không tìm thấy thông tin phường");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    @PatchMapping("/update-active")
    public ResponseEntity<ResponseMap> updateActive(@RequestHeader("key") String apiKey, @RequestBody WardDto wardDto) {
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

        if (this.wardService.updateWardActive(wardDto.getCode(), wardDto.isIs_active())) {
            result.setStatus(HttpStatus.OK.value());
            result.setMessage("Thành công cập nhật trạng thái hoạt động của phường");
            return ResponseEntity.ok(result);
        }
        else {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setMessage("Không tìm thấy thông tin phường");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

}