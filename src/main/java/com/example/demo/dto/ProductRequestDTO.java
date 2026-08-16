package com.example.demo.dto;

import com.example.demo.entity.Price;

import lombok.Data;

@Data
public class ProductRequestDTO {
	private String title;
	private Price price;
	private String description;
	private String Category;
	private String image;
}
