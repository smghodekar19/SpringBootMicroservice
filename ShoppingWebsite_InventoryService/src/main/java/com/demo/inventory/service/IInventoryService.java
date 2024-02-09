package com.demo.inventory.service;

import java.util.List;

import com.demo.inventory.entity.Inventory;

public interface IInventoryService {
	public boolean isInStock(String skuCode);
	
	public List<Inventory> getAllInventoryDetails();
}
