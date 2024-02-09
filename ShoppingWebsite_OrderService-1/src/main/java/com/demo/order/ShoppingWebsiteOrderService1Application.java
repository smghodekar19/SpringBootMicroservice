package com.demo.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.demo.order.clients"})
//@EnableAutoConfiguration(exclude = {FeignAutoConfiguration.class})
public class ShoppingWebsiteOrderService1Application {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingWebsiteOrderService1Application.class, args);
	}

}
