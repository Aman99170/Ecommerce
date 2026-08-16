package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "product")
public class Category extends BaseEntity{
	private String categoryType;
	@OneToMany
	@JoinColumn(name="category_id")
	@JsonIgnore
	private List<Product> product;
}


// 