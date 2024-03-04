package com.demo.order.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.order.clients.InventoryServiceFeignClient;
import com.demo.order.clients.ProductServiceFeignClient;
import com.demo.order.dto.OrderLineItemsDto;
import com.demo.order.dto.OrderRequestDto;
import com.demo.order.dto.ProductDto;
import com.demo.order.entity.Order;
import com.demo.order.entity.OrderLineItems;
import com.demo.order.repository.OrderRepository;
import com.demo.order.service.IOrderService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
//@NoArgsConstructor
public class OrderServiceImpl implements IOrderService {
	
	
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

//	@Autowired
	private OrderRepository orderRepository;
	
//	@Autowired
	private ProductServiceFeignClient productServiceClient;
	
//	@Autowired
	private InventoryServiceFeignClient inventoryServiceClient;
	
//	public OrderServiceImpl(OrderRepository orderRepo, ProductServiceFeignClient productServiceClient, InventoryServiceFeignClient inventoryServiceClient) {
//		this.orderRepository = orderRepo;
//		this.productServiceClient = productServiceClient;
//	}

	@Override
	public Order saveOrder(OrderRequestDto orderRequest) {
		// TODO Auto-generated method stub
		String orderNumber = UUID.randomUUID().toString();
		Order order = new Order();
		order.setOrderNumber(orderNumber);
		List<OrderLineItems> orderList = orderRequest.getOrderItems()
				.stream()
				.map(orderLineItemsDto -> mapToOrderLine(orderLineItemsDto, order))
				.filter((product) -> product != null)
				.toList();
		
		
		if(orderList.size()>0) {
			order.setOrderItems(orderList);
			orderRepository.save(order);
			return order;
		} else {
			log.error("No Order created due to not order items in list may be out of stock or no product retrieved");
			return null;
		}
		

		
	}

	private OrderLineItems mapToOrderLine(OrderLineItemsDto orderLineItemsDto, Order order) {
		// TODO Auto-generated method stub
		
		ProductDto productDto = productServiceClient.getProductBySkuCode(orderLineItemsDto.getSkuCode());
		Boolean isInStock = inventoryServiceClient.isAvailableInStock(orderLineItemsDto.getSkuCode());
		
		if(!isInStock || productDto.getSkuCode()== null)
			{
			log.error("Product Not retrieved");
			return null;
			}
		
		return OrderLineItems.builder()
		.skuCode(productDto.getSkuCode())
		.price(productDto.getPrice())
		.order(order)
		.quantity(orderLineItemsDto.getQuantity()).build();
	}

	@Override
	public Optional<Order> getOrder(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id);
	}

}
