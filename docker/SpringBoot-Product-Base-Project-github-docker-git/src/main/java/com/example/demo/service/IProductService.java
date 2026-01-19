package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.Product;
import com.example.demo.exception.ResourceNotFoundException;

public interface  IProductService {
	List<Product> getProductsFromDatabase();
	Optional<Product> getProductById(int id);
   Product createProduct( Product product);
   ResponseEntity<Product> updateProduct(Integer productId, Product productDetails) throws ResourceNotFoundException;
   Map<String, Boolean> deleteProduct (Integer productId) throws  ResourceNotFoundException;
   
   
}


