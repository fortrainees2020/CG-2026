package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService; // New Service

    // ===================== THYMELEAF (MVC) =====================

    // Show Add Product form with categories
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        
        List<Category> categories = categoryService.getAllCategories(); // To display all categories in product list page
        System.out.println(categories);
        
        model.addAttribute("categories", categories); // Pass categories for dropdown
        return "product/add-product";
    }

    // Handle form submission
    @PostMapping("/add")
    public String createProduct(
            @Valid @ModelAttribute("product") Product product,
            BindingResult result,
            @RequestParam Long categoryId) throws ResourceNotFoundException {

        if (result.hasErrors()) {
            result.getAllErrors()
                  .forEach(error -> System.out.println(error.getDefaultMessage()));
            return "product/add-product";
        }

        // Set category before saving
        Category category = categoryService.getCategoryById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        product.setCategory(category);

        productService.createProduct(product);
        return "redirect:/api/list";
    }

    /* Show product list page
    @GetMapping("/list")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getProductsFromDatabase());
        return "product/list-product";
    }*/
    
 // Show product list page with categories loaded
    @GetMapping("/list")
    public String listProducts(Model model) {
        // Fetch products along with their categories using JOIN FETCH
        model.addAttribute("products", productService.getProductsWithCategory());
        return "product/list-product"; // Thymeleaf template
    }


    // Edit page
   /* @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model)
            throws ResourceNotFoundException {

        System.out.println("EDIT PAGE HIT FOR ID = " + id);

        Product product = productService.getProductById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        System.out.println(categoryService.getAllCategories());
        return "product/edit-product";
    }*/
    // Implementing DTO
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model)
            throws ResourceNotFoundException {

        Product product = productService.getProductById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));

        // Entity → DTO
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setPname(product.getPname());
        dto.setPrice(product.getPrice());
        dto.setCategoryId(product.getCategory().getId());

        model.addAttribute("productDto", dto);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "product/edit-product";
    }


    
    @PostMapping("/update/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @ModelAttribute("product") Product product,
            @RequestParam("categoryId") Long categoryId
    ) throws ResourceNotFoundException {

        // Fetch Category
        Category category = categoryService.getCategoryById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found with id: " + categoryId));

        // Attach category to product
        product.setCategory(category);

        // Ensure ID consistency
        product.setId(id);

        productService.createProduct(product);

        return "redirect:/api/list";
    }
    // Delete
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id)
            throws ResourceNotFoundException {

        productService.deleteProduct(id);
        System.out.println(" Deleted ");
        return "redirect:/api/list";
    }

  
}
