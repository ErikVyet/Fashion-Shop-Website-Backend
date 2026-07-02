package com.code.handlers;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.code.dtos.ResponseMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseMap> handleConstraintViolation(DataIntegrityViolationException exception) {
        ResponseMap result = new ResponseMap();

        if (exception.getRootCause() instanceof DataIntegrityViolationException) {
            result.setStatus(HttpStatus.CONFLICT.value());
            result.setMessage("Không thể xử lý dữ liệu vì có dữ liệu liên quan");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        else {
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage("Dữ liệu bị vi phạm ràng buộc. Không thể thực hiện thao tác");
            
            Map<String, String> errorFields = new HashMap<>();
            String constraint = ((ConstraintViolationException) exception.getCause()).getConstraintName();
            
            if (constraint.equals("ukl256wnwfdobq071mcq7rckr9y")) {
                errorFields.put("name", "Tên tỉnh thành đã tồn tại");
            }
            if (constraint.equals("ukelfv0qxw40m1pawvfi9yx79qn")) {
                errorFields.put("name", "Tên phương thức thanh toán đã tồn tại");
            }

            result.setErrorFields(errorFields);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseMap> handleRequestBodyMissing(HttpMessageNotReadableException exception) {
        ResponseMap result = new ResponseMap();
        result.setStatus(HttpStatus.BAD_REQUEST.value());
        result.setMessage("Yêu cầu không hợp lệ. Vui lòng kiểm tra dữ liệu gửi lên");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ResponseMap> handleMissingHeader(MissingRequestHeaderException exception) {
        ResponseMap result = new ResponseMap();
        result.setStatus(HttpStatus.BAD_REQUEST.value());
        result.setMessage("Thiếu thông tin bắt buộc. Vui lòng kiểm tra lại");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMap> handleValidationFailed(MethodArgumentNotValidException exception) {
        ResponseMap result = new ResponseMap();
        Map<String, String> errorFields = new HashMap<>();
        exception.getFieldErrors().forEach(field -> {
            errorFields.put(field.getField(), field.getDefaultMessage());
        });

        result.setStatus(HttpStatus.BAD_REQUEST.value());
        result.setMessage("Dữ liệu không hợp lệ. Vui lòng kiểm tra lại");
        result.setErrorFields(errorFields);
        return ResponseEntity.badRequest().body(result);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseMap> handleTypeMismatch(MethodArgumentTypeMismatchException exception) {
        ResponseMap result = new ResponseMap();
        result.setStatus(HttpStatus.BAD_REQUEST.value());
        result.setMessage("Dữ liệu không hợp lệ. Vui lòng kiểm tra lại");
        return ResponseEntity.badRequest().body(result);
    }

}