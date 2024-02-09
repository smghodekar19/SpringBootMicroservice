package com.demo.product.entity.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.demo.product.dto.InventoryDetailsDto;
import com.demo.product.dto.ProductWithInventoryDto;
import com.demo.product.entity.Product;
import com.demo.product.entity.repository.ProductRepository;
import com.demo.product.entity.service.IProductService;
import com.demo.product.feign.client.InventoryServiceFeignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
public class ProductServiceImpl implements IProductService {
	
	ProductRepository productRepo;
	InventoryServiceFeignClient inventoryServiceClient;
	
	public ProductServiceImpl(ProductRepository repo, InventoryServiceFeignClient client){
		productRepo = repo;
		inventoryServiceClient = client;
	}

	@Override
	public List<ProductWithInventoryDto> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> list = productRepo.findAll();
		List<InventoryDetailsDto> inventory = this.getInventoyDetailsFromInventory();
		Map<String, Integer> inventoryMap = inventory.stream().collect(Collectors.toMap(InventoryDetailsDto::getSkuCode, InventoryDetailsDto::getQuantity));
		
		List<ProductWithInventoryDto> listWithStock = list.stream().map(t -> ProductWithInventoryDto
				.builder()
				.name(t.getName())
				.description(t.getDescription())
				.price(t.getPrice())
				.isSellable(t.getIsSellable())
				.skuCode(t.getSkuCode())
				.quantity(inventoryMap.getOrDefault(t.getSkuCode(), Integer.valueOf(0)))
				.build()
				).toList();
		return listWithStock;
	}
	
	@CircuitBreaker(name = "inventory-service", fallbackMethod = "getDefaultInventory")
	private List<InventoryDetailsDto> getInventoyDetailsFromInventory(){
		return inventoryServiceClient.retrieveInventoryDetails();
	}
	
	private List<InventoryDetailsDto> getDefaultInventory(Throwable e) {
		return new ArrayList<>();
	}

	@Override
	public Optional<Product> getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id);
	}
	
	@Override
	public Optional<Product> getProductBySkuCode(String skuCode) {
		// TODO Auto-generated method stub
		return productRepo.findBySkuCode(skuCode);
	}

	@Override
	public Product saveProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepo.save(product);
	}

	@Override
	public void deleteProductById(Long id) {
		// TODO Auto-generated method stub
		productRepo.deleteById(id);;
	}

	@Override
	public Product updateProductById(Product product) {
		// TODO Auto-generated method stub
		return productRepo.saveAndFlush(product);
	}

}
