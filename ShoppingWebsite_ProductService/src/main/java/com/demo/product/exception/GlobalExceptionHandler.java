package com.demo.product.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Product with given id Not Found")
	public ResponseEntity<?> getProductNotFoundException(ProductNotFoundException exception) {
		exception.printStackTrace();
		return ResponseEntity.badRequest().body(exception.getLocalizedMessage());
	}
}
