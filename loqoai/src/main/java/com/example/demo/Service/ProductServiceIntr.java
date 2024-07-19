package com.example.demo.Service;

import java.util.List;

import com.example.demo.Exception.ProductNotFoundException;
import com.example.demo.Model.Product;

public interface ProductServiceIntr {
	
	public Product addProduct(Product product) throws ProductNotFoundException; 
	public List<Product> getProducts(String category, Double minPrice, Double maxPrice, Boolean inStock, String sortField, String sortOrder) throws ProductNotFoundException;
	

}
