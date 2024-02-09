package com.demo.order.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.order.clients.errorDecoder.CustomErrorDecoder;
import com.demo.order.config.FeignConfiguration;
import com.demo.order.dto.ProductDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name="product-service",configuration = FeignConfiguration.class)
public interface ProductServiceFeignClient {
	
	@GetMapping("/api/product/sku/{sku-code}")
	@CircuitBreaker(name = "product-service", fallbackMethod = "getDefaultProductDto")
	ProductDto getProductBySkuCode(@PathVariable("sku-code") String skuCode);
	
	default ProductDto getDefaultProductDto(Throwable e) {
		return new ProductDto();
	}

}
