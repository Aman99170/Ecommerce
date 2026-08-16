package com.example.demo.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Product;

public interface ProductRepo extends JpaRepository<Product,UUID>{

	Optional<Product> findByTitle(String title);

}
