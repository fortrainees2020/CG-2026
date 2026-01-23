package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ViewResolver;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private CategoryService categoryService;
    
    // ================= GET: Add Product =================
    @Test
    void shouldLoadAddProductPage() throws Exception {

        Category category = new Category();
        category.setId(1L);
        category.setCname("Electronics");

        when(categoryService.getAllCategories())
                .thenReturn(List.of(category));

        mockMvc.perform(get("/api/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/add-product"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attributeExists("categories"));
    }
    

    // ================= POST: Add Product =================
    @Test
    void shouldCreateProduct() throws Exception {

        Category category = new Category();
        category.setId(1L);

        when(categoryService.getCategoryById(1L))
                .thenReturn(Optional.of(category));

        mockMvc.perform(post("/api/add")
                        .param("pname", "Laptop")
                        .param("price", "75000")
                        .param("categoryId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/api/list"));

        verify(productService, times(1)).createProduct(any(Product.class));
    }

    // ================= GET: List Products =================
    @Test
    void shouldListProducts() throws Exception {

        Product product = new Product();
        product.setId(1L);
        product.setPname("Phone");

        when(productService.getProductsWithCategory())
                .thenReturn(List.of(product));

        mockMvc.perform(get("/api/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/list-product"))
                .andExpect(model().attributeExists("products"));
    }
 
    

    // ================= POST: Update Product =================
    @Test
    void shouldUpdateProduct() throws Exception {

        Category category = new Category();
        category.setId(1L);

        when(categoryService.getCategoryById(1L))
                .thenReturn(Optional.of(category));

        mockMvc.perform(post("/api/update/5")
                        .param("pname", "Updated Product")
                        .param("price", "50000")
                        .param("categoryId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/api/list"));

        verify(productService).createProduct(any(Product.class));
    }

    // ================= GET: Delete Product =================
    @Test
    void shouldDeleteProduct() throws Exception {

        doNothing().when(productService).deleteProduct(5L);

        mockMvc.perform(get("/api/delete/5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/api/list"));

        verify(productService, times(1)).deleteProduct(5L);
    }

    
    
}
