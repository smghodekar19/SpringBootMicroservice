package com.demo.product.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductWithInventoryDto {
	private String name;
	private String description;
	
	private String skuCode;
	private BigDecimal price;
	
	private Boolean isSellable;
	
	private Integer quantity;
}
