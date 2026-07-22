package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductResponseDTO {
	private int id;
	private String title;
	private double price;
	private String description;
	private String Category;
	private String image;
}
