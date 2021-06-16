package com.example.demo.service;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
@Service
public class ProductService implements Productrepo {
	private final String PRODUCT_CACHE = "PRODUCT";
	 
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, Product> hashOperations;
 
    // This annotation makes sure that the method needs to be executed after 
    // dependency injection is done to perform any initialization.
    @PostConstruct
    private void intializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }
 
    // Save operation.
    @Override
    public void save(final Product product) {
        hashOperations.put(PRODUCT_CACHE, product.getId(), product);
    }
    
    // Delete product by id operation.
    @Override
    public void delete(String id) {
        hashOperations.delete(PRODUCT_CACHE, id);
    }
 
    // Find by product id operation.
    @Override
    public Product findById(final String id) {
        return (Product) hashOperations.get(PRODUCT_CACHE, id);
    }
 
    // Find all product' operation.
    @Override
    public Map<String, Product> findAll() {
        return hashOperations.entries(PRODUCT_CACHE);
    }
    
    // update product by product id operation
    @Override
    public void updateById(String id, final Product product) {
        hashOperations.put(PRODUCT_CACHE, id, product);
    }
    
}
