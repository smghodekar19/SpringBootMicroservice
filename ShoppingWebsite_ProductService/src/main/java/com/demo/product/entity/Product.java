package com.demo.product.entity;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	
	@Column(unique = true, nullable = false)
	@NotBlank(message = "SKU Code is mandatory for product")
	private String skuCode;
	private BigDecimal price;
	
	@ColumnDefault(value = "true")
	private Boolean isSellable;

}
