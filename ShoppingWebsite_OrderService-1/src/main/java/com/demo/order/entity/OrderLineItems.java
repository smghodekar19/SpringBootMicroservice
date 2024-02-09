package com.demo.order.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "table_order_items")
@Builder
public class OrderLineItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String skuCode;
	private BigDecimal price;
	
	
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	@JsonBackReference
	private Order order;
}
