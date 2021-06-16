package com.example.demo.service;

import java.util.Map;

import com.example.demo.model.Product;

public interface Productrepo {
	
    // Save operation.
    public void save(final Product product);
    
    // Find by product id operation.
    public Product findById(final String id);
    
    // Find all products' operation.
    public Map<String, Product> findAll();
 
    // Delete product by id operation.
    public void delete(String id);
    
 // update product by id operation.
    public void updateById(String id, final Product product);
}
