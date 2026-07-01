package com.code.handlers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.code.dtos.ResponseMap;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class})
    public ResponseEntity<ResponseMap> handleForeignKeyViolation(Exception exception) {
        ResponseMap result = new ResponseMap();
        result.setStatus(HttpStatus.CONFLICT.value());
        result.setMessage("Không thể xóa dữ liệu vì có dữ liệu liên quan");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseMap> handleRequestBodyMissing(HttpMessageNotReadableException exception) {
        ResponseMap result = new ResponseMap();
        result.setStatus(HttpStatus.BAD_REQUEST.value());
        result.setMessage("Yêu cầu không hợp lệ. Vui lòng kiểm tra dữ liệu gửi lên");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMap> handleValidationFailed(MethodArgumentNotValidException exception) {
        ResponseMap result = new ResponseMap();
        result.setStatus(HttpStatus.BAD_REQUEST.value());
        result.setMessage("Dữ liệu không hợp lệ. Vui lòng kiểm tra lại");
        return ResponseEntity.badRequest().body(result);
    }

}