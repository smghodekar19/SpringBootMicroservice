package com.demo.order.config;

import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.client.WebClient;

import com.demo.order.clients.errorDecoder.CustomErrorDecoder;

import feign.Logger;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfiguration {

	@Bean
	ErrorDecoder errorDecoder() {
		return new CustomErrorDecoder();
	}
	
	@Bean
	Logger.Level getLoggerLevel() {
		return Logger.Level.FULL; 
	}

}
