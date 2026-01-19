package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")

//Swagger - http://localhost:9091/swagger-ui/index.html
//Actuator- http://localhost:9091/actuator/health

@RestController
@RequestMapping("/api") //controller level mapping
@Tag(name = "Product Controller1", description = "APIs for Product Management")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private IProductService productService;

	@Operation(summary = "Greeting endpoint")
	@GetMapping("/hello") //Method level mapping
	public String printHello() {

		logger.info("Info log from Product controller endpoint");
		logger.debug("Debug log from Product controller endpoint");
		logger.error("Error log from Product controller endpoint");
		return "Hi Spring";
	}

	@Operation(summary = "Get all products")
	@GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getAllProducts() {
		return productService.getProductsFromDatabase();
	}

	@Operation(summary = "Get product by ID from DB with exception")
	@GetMapping("/products/{id}")
	public Product getProductByIdFromDB(@PathVariable int id) throws ResourceNotFoundException {
		return productService.getProductById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for id: " + id));
	}
//http://localhost:9091/api/products/by-id?id=2
	@Operation(summary = "Get product by ID using query param")
	@GetMapping("/products/by-id")
	public Product getProductByIdQueryParam(@RequestParam int id) throws ResourceNotFoundException {
		return productService.getProductById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for id: " + id));
	}

//	@Operation(summary = "Create a new product")
	//http://localhost:9292/api/products
	@PostMapping("/products")
	public Product createProduct(@Valid @RequestBody Product newProduct) {
		return productService.createProduct(newProduct);
	}

	//@Operation(summary = "Update an existing product")
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(
			@PathVariable("id") Integer productId,
			@Valid @RequestBody Product newProduct) throws ResourceNotFoundException {
		return productService.updateProduct(productId, newProduct);
	}

	@Operation(summary = "Delete a product by ID")
	@DeleteMapping("/products/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable("id") Integer productId)
			throws ResourceNotFoundException {
		return productService.deleteProduct(productId);
	}
}
