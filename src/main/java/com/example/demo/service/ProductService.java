package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ProducRequesttDTO;
import com.example.demo.dto.ProductResponseDTO;

public interface ProductService {

	public List<ProductResponseDTO> getAllProducts();
	
	public ProductResponseDTO getProduct(int id);

	public ProductResponseDTO createProduct(ProducRequesttDTO producRequesttDTO);

	public boolean deleteProduct(String id);

	public Boolean updateProduct(Long id,ProducRequesttDTO producRequesttDTO);
}
