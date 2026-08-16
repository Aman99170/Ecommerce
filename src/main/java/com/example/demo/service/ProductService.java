package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.dto.ProducRequesttDTO;
import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.dto.ProductResponseeDTO;

public interface ProductService {

	public default List<ProductResponseDTO> getAllProducts(){
		return null;
	};
	
	public default List<ProductResponseeDTO> getAllProducts1(){
		return null;
	};
	
	public default ProductResponseDTO getProduct(int id) {
		return null;
	};

	public default ProductResponseeDTO createProduct(ProductRequestDTO producRequesttDTO) {
		return null;
	};
	
	public default ProductResponseDTO createProduct(ProducRequesttDTO producRequesttDTO) {
		return null;
	};

	public default boolean deleteProduct(String id) {
		return false;
	};

	public default Boolean updateProduct(Long id,ProducRequesttDTO producRequesttDTO) {
		return false;
	};
	
	public default ProductResponseeDTO getProduct(UUID id) {
		return null;
	}

	default boolean deleteProduct(UUID id) {
		return false;
	}

	default Boolean updateProduct(UUID id, ProductRequestDTO producRequesttDTO) {
		return false;
	}

	public default ProductResponseeDTO getProduct1(String id) {
		return null;
	};
	
	public default ProductResponseeDTO getProductByTitle(String id) {
		return null;
	};
}
