package com.example.demo.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Price;

public interface PriceRepo extends JpaRepository<Price,UUID>{

}
