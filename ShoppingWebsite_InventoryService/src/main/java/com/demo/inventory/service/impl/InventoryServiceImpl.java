package com.demo.inventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.inventory.entity.Inventory;
import com.demo.inventory.repository.InventoryRepository;
import com.demo.inventory.service.IInventoryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements IInventoryService{
	
	InventoryRepository inventoryRepository;
	
	

	@Override
	public boolean isInStock(String skuCode) {
		Inventory inventory =  inventoryRepository.findBySkuCode(skuCode).orElse(null);
		if(inventory == null) return false;
		return inventory.getQuantity() > 0;
	}

	@Override
	public List<Inventory> getAllInventoryDetails() {
		// TODO Auto-generated method stub
		return inventoryRepository.findAll();
	}

}
