package com.demo.product.feign.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.product.dto.InventoryDetailsDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@FeignClient(name = "inventory-service")
public interface InventoryServiceFeignClient {
	
	
	public static final Logger log = LoggerFactory.getLogger(InventoryServiceFeignClient.class);


	@GetMapping("api/inventory")
	@CircuitBreaker(name = "inventory-service", fallbackMethod = "getDefaultInventory")
	@RateLimiter(name = "inventory-service" , fallbackMethod="getDefaultInventoryTimeLimit")
	List<InventoryDetailsDto> retrieveInventoryDetails();
	
	 default List<InventoryDetailsDto> getDefaultInventory(Throwable e) {
		return new ArrayList<>();
	}
	
	 default List<InventoryDetailsDto> getDefaultInventoryTimeLimit(Throwable e) {
		 log.error("getDefaultInventoryTimeLimit Exceeded");
		 return new ArrayList<>();
	 }
}
