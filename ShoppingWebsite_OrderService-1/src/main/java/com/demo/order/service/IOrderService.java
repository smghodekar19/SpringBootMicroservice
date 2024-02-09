package com.demo.order.service;

import java.util.Optional;

import com.demo.order.dto.OrderRequestDto;
import com.demo.order.entity.Order;

public interface IOrderService {
	Order saveOrder(OrderRequestDto orderRequest);

	Optional<Order> getOrder(Long id);
}
