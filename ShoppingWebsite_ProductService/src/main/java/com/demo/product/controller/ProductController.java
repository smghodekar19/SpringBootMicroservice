package com.demo.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.product.dto.ProductWithInventoryDto;
import com.demo.product.entity.Product;
import com.demo.product.entity.service.IProductService;
import com.demo.product.exception.ProductNotFoundException;


@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	IProductService productService;
	
	public ProductController(IProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public Product saveProduct(@RequestBody @Valid Product product) {
		return productService.saveProduct(product);
	}
	
	@GetMapping
	public List<ProductWithInventoryDto> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable(value = "id") Long id) throws ProductNotFoundException {
		return productService.getProductById(id).orElseThrow(() -> new ProductNotFoundException("Product with given id not found"));
	}
	
	@GetMapping("/sku/{sku-code}")
	public Product getProductBySkuCode(@PathVariable(value = "sku-code") String skuCode) throws ProductNotFoundException {
		return productService.getProductBySkuCode(skuCode).orElseThrow(() -> new ProductNotFoundException("Product with given SKU Code not found"));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProductById(id);
		return ResponseEntity.ok().build();
	}
}
