package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByPname(String pname);

    List<Product> findAllByPname(String pname);

    //Long countByPname(String pname);
    @Query("SELECT COUNT(p) FROM Product p WHERE p.pname = :pname")
    Long countByPname(String pname);
    
    
    // Step 2: Add a custom query to fetch products with their category
    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category")
    List<Product> getProductsWithCategory();
    
 // FIX: Edit page (single product with category)
    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category WHERE p.id = :id")
    Optional<Product> findByIdWithCategory(Long id);
}
    
