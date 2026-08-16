package com.example.demo.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ProductOrder;

public interface OrderRepo extends JpaRepository<ProductOrder,UUID>{

}
