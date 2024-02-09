package com.demo.product.entity.service;

import java.util.List;
import java.util.Optional;

import com.demo.product.dto.ProductWithInventoryDto;
import com.demo.product.entity.Product;

public interface IProductService {

	public List<ProductWithInventoryDto> getAllProducts();
	
	public Optional<Product> getProductById(Long id);
	
	public Optional<Product> getProductBySkuCode(String skuCode);
	
	public Product saveProduct(Product product);
	
	public void deleteProductById(Long id);
	
	public Product updateProductById(Product product);
	
}
