package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.ProductNotFoundException;
import com.example.demo.Model.Product;
import com.example.demo.Service.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	/**
     * Endpoint to save a new product.
     * 
     * @param product The product to be saved.
     * @return The saved product along with an HTTP status code.
     * @throws ProductNotFoundException if the product cannot be saved.
     */
	@PostMapping("/saveproduct")
	public ResponseEntity<Product> saveProductHandler(@RequestBody Product product) throws ProductNotFoundException{
		return new ResponseEntity<Product>(productServiceImpl.addProduct(product), HttpStatus.OK);
	}
	
	/**
     * Endpoint to retrieve products based on optional filter and sort parameters.
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
	 @GetMapping("/getproduct")
	 public List<Product> getProducts(
           @RequestParam(required = false) String category,
           @RequestParam(required = false) Double minPrice,
           @RequestParam(required = false) Double maxPrice,
	       @RequestParam(required = false) Boolean inStock,
	       @RequestParam(required = false, defaultValue = "createdAt") String sortField,
	       @RequestParam(required = false) String sortOrder) throws ProductNotFoundException {
	        
	        return productServiceImpl.getProducts(category, minPrice, maxPrice, inStock, sortField, sortOrder);
	  }
	
}
