package com.demo.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.inventory.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Optional<Inventory> findBySkuCode(String skuCode);

}
