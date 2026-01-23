package com.example.demo.dto;

	import jakarta.validation.constraints.Min;
	import jakarta.validation.constraints.NotBlank;
	import jakarta.validation.constraints.NotNull;

public class ProductDTO {
	    private Long id;

	    @NotBlank(message = "Product name is required")
	    private String pname;

	    @Min(value = 1, message = "Price must be greater than 0")
	    private double price;

	    // Instead of Category entity, use only categoryId
	    @NotNull(message = "Category is required")
	    private Long categoryId;

	    // Optional: for display purpose in Thymeleaf
	    private String categoryName;

	    // Getters & Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getPname() {
	        return pname;
	    }

	    public void setPname(String pname) {
	        this.pname = pname;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public void setPrice(double price) {
	        this.price = price;
	    }

	    public Long getCategoryId() {
	        return categoryId;
	    }

	    public void setCategoryId(Long categoryId) {
	        this.categoryId = categoryId;
	    }

	    public String getCategoryName() {
	        return categoryName;
	    }

	    public void setCategoryName(String categoryName) {
	        this.categoryName = categoryName;
	    }
	}
