package com.demo.order.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	

	private Long id;
	private String name;
	private String description;
	
	private String skuCode;
	private BigDecimal price;

	private Boolean isSellable;

}
