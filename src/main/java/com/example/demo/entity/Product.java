package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "category")
public class Product extends BaseEntity{

	private String title;
	private String description;
	private String image;
	@ManyToOne
	private Category category;
	@OneToOne
	private Price price;
}
