package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	private final ProductRepository productRepo;

	public ProductService(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	public List<Product> getProductsFromDatabase() {
		logger.info("Fetching all products...");
		return productRepo.findAll();
	}

	// Step 3: Add new method that fetches category too
	public List<Product> getProductsWithCategory() {
		return productRepo.getProductsWithCategory();
	}

	/*public Optional<Product> getProductById(Long id) {
		return productRepo.findById(id);
	}*/

	public Product createProduct(Product product) {
		return productRepo.save(product);
	}

	@Transactional
	public Optional<Product> getProductById(Long id) {
		return productRepo.findByIdWithCategory(id);
	}
	
	public Product updateProduct(Long productId, Product changedProduct)
			throws ResourceNotFoundException {

		Product updatedProduct = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

		updatedProduct.setPname(changedProduct.getPname());
		updatedProduct.setPrice(changedProduct.getPrice());
		productRepo.save(updatedProduct);

		return updatedProduct;
	}

	public void deleteProduct(Long id) {
		 productRepo.deleteById(id);
		 System.out.println(" Deleted at service");
	}

	/*public Map<String, Boolean> deleteProduct(Long productId) throws ResourceNotFoundException {

		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

		productRepo.delete(product);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	public Product getProductByName(String pname) throws ResourceNotFoundException {

		logger.info("Fetching product with name: {}", pname);

		return productRepo.findByPname(pname)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with name: " + pname));
	}

	public List<Product> getProductsByName(String pname) throws ResourceNotFoundException {

		List<Product> products = productRepo.findAllByPname(pname);

		if (products.isEmpty()) {
			throw new ResourceNotFoundException("No products found with name: " + pname);
		}
		return products;
	}

	public Long countProductsByName(String pname) {
		return productRepo.countByPname(pname);
	}
*/
	
}
