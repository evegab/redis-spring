package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(ProductController.class);
	
	ArrayList<Product> list = new ArrayList<>();
	 
    @Autowired
    //ProductService service;
 
    // Save a new product.
    @PostMapping
    public String save(@RequestBody final Product product) {
        //LOG.info("Saving the new product to the redis.");
        //service.save(product);
    	if(product.getId() != null)
    		this.list.add(product);
        return "Successfully added. PRODUCT with id= " + product.getId();
    }
 
    // Get all products
    @GetMapping()
    public ArrayList<Product> findAll() {
        LOG.info("Fetching all products from the redis.");
        //final Map<String, Product> productMap = service.findAll();
        //return productMap;
        return this.list;
    }
 
    // Get product by id
    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") final String id) {
        LOG.info("Fetching product with id= " + id);
        //return service.findById(id);
        for (Product product : list) {
			if(product.getId() != null && product.getId().equals(id))
				return product;
		}
        return null;
    }
    
    // update product by id
    @PutMapping("/{id}")
    public String update(@PathVariable("id") final String id, @RequestBody final Product newProduct) {
        LOG.info("Deleting product with id= " + id);
        //service.updateById(id, product);
        for (Product product : list) {
			if(product.getId() != null && product.getId().equals(id)) {
				product.setName(newProduct.getName());
				product.setPrice(newProduct.getPrice());
				product.setQuantity(newProduct.getQuantity());
			}
		}
        return "Successfully added. product with id= " + newProduct.getId();
    }
 
    // Delete product by id
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") final String id) {
        LOG.info("Deleting product with id= " + id);
        //service.delete(id);
        for (int i= 0; i< this.list.size(); i++) {
			if(list.get(i).getId() != null && list.get(i).getId().equals(id)) {
				this.list.remove(i);
			}
		}
        return "product" + id + ", eliminado";
    }
    
}
