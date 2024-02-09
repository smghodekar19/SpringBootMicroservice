package com.demo.order.controller.advice;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> badRequestException(BadRequestException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
