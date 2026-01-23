package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Category;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category) throws ResourceNotFoundException {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        existing.setCname(category.getCname());
        return categoryRepository.save(existing);
    }
 
    public void deleteCategory(Long id) throws ResourceNotFoundException {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        categoryRepository.delete(existing);
    }
 
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public List<Category> getAllCategories() { // USed in Add Product form
        return categoryRepository.findAll();
    }

    public Category getCategoryByName(String cname) throws ResourceNotFoundException {
        Category category = categoryRepository.findByCname(cname);
        if (category == null) {
            throw new ResourceNotFoundException("Category not found with name: " + cname);
        }
        return category;
    }
}
