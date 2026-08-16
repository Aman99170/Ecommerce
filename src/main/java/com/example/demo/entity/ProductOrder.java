package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class ProductOrder extends BaseEntity{

	@ManyToMany
	private List<Product> productList;
}
