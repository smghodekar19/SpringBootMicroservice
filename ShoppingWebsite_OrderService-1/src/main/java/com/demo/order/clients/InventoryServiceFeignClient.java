package com.demo.order.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.order.clients.errorDecoder.CustomErrorDecoder;
import com.demo.order.config.FeignConfiguration;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name="inventory-service",configuration = {FeignConfiguration.class, CustomErrorDecoder.class})
public interface InventoryServiceFeignClient {
	
	@GetMapping("/api/inventory/{sku-code}")
	@CircuitBreaker(name = "inventory-service", fallbackMethod = "getDefaultStockStatus")
	Boolean isAvailableInStock(@PathVariable("sku-code") String skuCode);
	
	default Boolean getDefaultStockStatus(Throwable e) {
		return false;
	}
}
