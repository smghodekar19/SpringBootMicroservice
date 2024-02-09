package com.demo.order.clients.errorDecoder;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class CustomErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		// TODO Auto-generated method stub
		switch (response.status()) {
		
		case 400: return new BadRequestException("Bad Request *****");
		default:
			throw new IllegalArgumentException("Exception while executing service call");
		}
	}

}
