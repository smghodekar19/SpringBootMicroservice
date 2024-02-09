package com.demo.order.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(OrderNotFoundException.class)
public ResponseEntity<?> handleOrderNotFoundException(OrderNotFoundException exception) {
	exception.printStackTrace();
	return ResponseEntity.badRequest().body(exception.getMessage());
}

}
