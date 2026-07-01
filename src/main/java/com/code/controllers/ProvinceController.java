package com.code.controllers;

import java.util.List;

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

import com.code.dtos.ProvinceDto;
import com.code.dtos.ResponseMap;
import com.code.services.ProvinceService;

@RestController
@RequestMapping("/fazhion/api/v1/province")
@CrossOrigin(origins = "http://localhost:5173")
public class ProvinceController {

    protected ProvinceService provinceService;

    @Value("${app.api.key}")
    protected String key;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseMap> getProvinces(@RequestHeader("key") String apiKey) {
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

        List<ProvinceDto> provinces = this.provinceService.readProvinces();
        result.setStatus(HttpStatus.OK.value());
        result.setData(provinces);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{provinceCode}")
    public ResponseEntity<ResponseMap> findProvince(@RequestHeader("key") String apiKey, @PathVariable String provinceCode) {
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

        ProvinceDto provinceDto = this.provinceService.getProvince(provinceCode);
        if (provinceDto == null) {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setMessage("Không tìm thấy tỉnh thành");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        else {
            result.setStatus(HttpStatus.OK.value());
            result.setData(provinceDto);
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMap> insertProvince(@RequestHeader("key") String apiKey, @RequestBody ProvinceDto provinceDto) {
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

        if (this.provinceService.createProvince(provinceDto)) {
            result.setStatus(HttpStatus.OK.value());
            result.setMessage("Thêm dữ liệu thành công");
            return ResponseEntity.ok(result);
        }
        else {
            result.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
            result.setMessage("Đã xảy ra lỗi khi thêm dữ liệu");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(result);
        }
    }

    @DeleteMapping("/delete/{provinceCode}")
    public ResponseEntity<ResponseMap> removeProvince(@RequestHeader("key") String apiKey, @PathVariable String provinceCode) {
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

        if (this.provinceService.deleteProvince(provinceCode)) {
            result.setStatus(HttpStatus.OK.value());
            result.setMessage("Xóa dữ liệu thành công");
            return ResponseEntity.ok(result);
        }
        else {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setMessage("Không tìm thấy dữ liệu để xóa");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseMap> updateProvince(@RequestHeader("key") String apiKey, @RequestBody ProvinceDto provinceDto) {
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

        if (this.provinceService.updateProvince(provinceDto)) {
            result.setStatus(HttpStatus.OK.value());
            result.setMessage("Cập nhật dữ liệu thành công");
            return ResponseEntity.ok(result);
        }
        else {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setMessage("Không tìm thấy dữ liệu để cập nhật");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    @PatchMapping("/update-visibility")
    public ResponseEntity<ResponseMap> updateProvinceVisibility(@RequestHeader("key") String apiKey, @RequestBody ProvinceDto data) {
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

        if (this.provinceService.updateProvinceActive(data.getCode(), data.isIs_active())) {
            result.setStatus(HttpStatus.OK.value());
            result.setMessage("Cập nhật dữ liệu thành công");
            return ResponseEntity.ok(result);
        }
        else {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setMessage("Không tìm thấy dữ liệu để cập nhật");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

}