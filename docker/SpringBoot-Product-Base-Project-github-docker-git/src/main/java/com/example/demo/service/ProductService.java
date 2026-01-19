package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProductRepository;
@Service
public class ProductService implements IProductService {	
	@Autowired
	ProductRepository productRepo;
	
	@Override
	public List<Product> getProductsFromDatabase() {
		return productRepo.findAll();
	}
	

@Override
	public Optional<Product> getProductById(int id) {
		
		return productRepo.findById(id);
	}	
	


	@Override
	 public Product createProduct(Product product) {
	        return productRepo.save(product);
	    }


	@Override
		public ResponseEntity<Product> updateProduct(Integer productId, Product changedPoduct)
			throws ResourceNotFoundException {
	Product updatedProduct = productRepo.findById(productId)
						.orElseThrow(()-> new ResourceNotFoundException("Product is not avaialble:"+ productId));
	updatedProduct.setPname(changedPoduct.getPname());
	updatedProduct.setPrice(changedPoduct.getPrice());
	productRepo.save(updatedProduct);
		return ResponseEntity.ok(updatedProduct);
	}

	@Override
	public Map<String, Boolean> deleteProduct(Integer productId) throws ResourceNotFoundException {
		Product updatedProduct = productRepo.findById(productId)
				.orElseThrow(()-> new ResourceNotFoundException("Product is not avaialble:"+ productId));
		productRepo.delete(updatedProduct);
		Map<String,Boolean> response  = new HashMap<>();
		response.put("Product has been Deleted", Boolean.TRUE);
		return response;
	}
}
