package com.example.demo.dto;

import java.util.UUID;

import com.example.demo.entity.Category;
import com.example.demo.entity.Price;

import lombok.Data;

@Data
public class ProductResponseeDTO {
	private UUID id;
	private String title;
	private Price price;
	private String description;
	private Category category;
	private String image;
}
