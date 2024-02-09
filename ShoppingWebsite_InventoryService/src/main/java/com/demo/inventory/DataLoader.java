package com.demo.inventory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.inventory.entity.Inventory;
import com.demo.inventory.repository.InventoryRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
	
	InventoryRepository repo;

	@Override
	public void run(String... args) throws Exception {
		if (repo.findAll().size() > 0) return;
		repo.save(Inventory.builder().skuCode("iphone_13_red_128_gb").quantity(45).build());
		repo.save(Inventory.builder().skuCode("iphone_13_red").quantity(0).build());
		
	}

}
