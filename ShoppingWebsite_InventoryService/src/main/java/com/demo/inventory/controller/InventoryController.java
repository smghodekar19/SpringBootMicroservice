package com.demo.inventory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.inventory.entity.Inventory;
import com.demo.inventory.service.IInventoryService;

import jakarta.ws.rs.BadRequestException;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryController {
	
	IInventoryService service;

	@GetMapping("/{sku-code}")
	@ResponseStatus(HttpStatus.OK)
	public Boolean isInStock(@PathVariable("sku-code") String skuCode) {
		return service.isInStock(skuCode);
	}
	
	@GetMapping()
	public List<Inventory> getAllStock() {
//		throw new RuntimeException("Bad request Exception Thrown");
		return service.getAllInventoryDetails();
	}
}
