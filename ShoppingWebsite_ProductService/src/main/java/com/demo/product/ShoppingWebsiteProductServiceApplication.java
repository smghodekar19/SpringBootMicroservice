package com.demo.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.demo.product.feign.client")
public class ShoppingWebsiteProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingWebsiteProductServiceApplication.class, args);
	}

}
