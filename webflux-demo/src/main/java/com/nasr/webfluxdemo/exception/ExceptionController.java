package com.nasr.webfluxdemo.exception;

import com.nasr.webfluxdemo.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseDto<?>> productNotFoundException(ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDto<>(false, null, e.getMessage()));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ResponseDto<?>> orderNotFoundException(OrderNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDto<>(false, null, e.getMessage()));
    }

    @ExceptionHandler(IllegalProductException.class)
    public ResponseEntity<ResponseDto<?>> illegalProductException(IllegalProductException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto<>(false, null, e.getMessage()));
    }
}
