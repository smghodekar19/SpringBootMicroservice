package com.demo.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.order.dto.OrderRequestDto;
import com.demo.order.entity.Order;
import com.demo.order.exception.OrderNotFoundException;
import com.demo.order.service.IOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

	IOrderService orderService;
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	
	ObjectMapper mapper;
	
	@PostMapping
	public String placeOrder(@RequestBody OrderRequestDto orderRequest) {
		log.error("Order create request received for : " + orderRequest.getOrderItems().toString());
		Order order = orderService.saveOrder(orderRequest);
		if(order ==null) {
			return "Order creation failed";
		}
		return "Order Saved Successfully : "+ order.getOrderNumber();
	}
	
	@GetMapping("/{id}")
	public OrderRequestDto getOrder(@PathVariable("id") Long id) {
		Order order = orderService.getOrder(id).orElseThrow(() -> new OrderNotFoundException("Order with given id not found"));
		OrderRequestDto orderRequestDto = mapper.convertValue(order, OrderRequestDto.class);
		return orderRequestDto;
	}
}
