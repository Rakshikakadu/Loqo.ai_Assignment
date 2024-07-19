package com.example.demo.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ProductNotFoundException;
import com.example.demo.Model.Product;
import com.example.demo.Repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductServiceIntr {

	@Autowired
	private ProductRepository productRepository;

	
	 /**
     * Adds a new product to the repository.
     * 
     * @param product The product to be added.
     * @return The saved product.
     * @throws ProductNotFoundException if the product details are not found.
     */
	@Override
	public Product addProduct(Product product) throws ProductNotFoundException {

		if (product == null) {
			throw new ProductNotFoundException("Product details not found");
		}

		return productRepository.save(product);

	}

	 /**
     * Retrieves products based on provided filter and sort criteria.
     * 
     * @param category  Optional parameter to filter products by category.
     * @param minPrice  Optional parameter to filter products by minimum price.
     * @param maxPrice  Optional parameter to filter products by maximum price.
     * @param inStock   Optional parameter to filter products based on stock availability.
     * @param sortField Optional parameter to sort the products by a specific field. Defaults to "createdAt".
     * @param sortOrder Optional parameter to define the sort order ("asc" or "desc"). Case-insensitive.
     * @return A list of products that match the filtering and sorting criteria.
     * @throws ProductNotFoundException if no products are found.
     */
	@Override
	public List<Product> getProducts(String category, Double minPrice, Double maxPrice, Boolean inStock,
			String sortField, String sortOrder) throws ProductNotFoundException {

		// Fetch all products from the database
		List<Product> products = productRepository.findAll();

		// Filter products based on the provided criteria
		if (category != null && !category.isEmpty()) {
			products = products.stream().filter(product -> category.equals(product.getCategory()))
					.collect(Collectors.toList());
		}
		if (minPrice != null) {
			products = products.stream().filter(product -> product.getPrice() >= minPrice).collect(Collectors.toList());
		}
		if (maxPrice != null) {
			products = products.stream().filter(product -> product.getPrice() <= maxPrice).collect(Collectors.toList());
		}
		if (inStock != null) {
			products = products.stream().filter(product -> product.isInStock() == inStock).collect(Collectors.toList());
		}

		// Sort products based on the provided sorting field and order
		if (sortField != null && !sortField.isEmpty()) {
			Comparator<Product> comparator = getComparator(sortField, sortOrder);
			if (comparator != null) {
				products = products.stream().sorted(comparator).collect(Collectors.toList());
			}
		}

		return products;

	}

	/**
     * Returns a comparator for the given sort field and sort order.
     * 
     * @param sortField The field to sort by (e.g., "price", "rating", "createdAt").
     * @param sortOrder The sort order ("asc" or "desc").
     * @return A comparator for sorting products.
     */
	private Comparator<Product> getComparator(String sortField, String sortOrder) {
		Comparator<Product> comparator = null;

		switch (sortField) {
		case "price":
			comparator = Comparator.comparing(Product::getPrice);
			break;
		case "rating":
			comparator = Comparator.comparing(Product::getRating);
			break;
		case "createdAt":
			comparator = Comparator.comparing(Product::getCreatedAt);
			break;
		}

		if (sortOrder != null && sortOrder.trim().equalsIgnoreCase("desc")) {
			return comparator.reversed();
		} else {
			return comparator;
		}
	}

}
