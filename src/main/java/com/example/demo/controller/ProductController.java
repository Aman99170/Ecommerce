package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.ProductNotFoundException;
import com.example.demo.dto.ProducRequesttDTO;
import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.dto.ProductResponseeDTO;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Qualifier("productService")
	private final ProductService productService;
	
	ProductController(ProductService productService){
		this.productService = productService;
	}
	
	
	@GetMapping("/getProducts")
	public ResponseEntity<List<ProductResponseeDTO>> getAllProduct(){
		
		List<ProductResponseeDTO> response = productService.getAllProducts1();
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/getProduct/{id}")
	public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable int id){
		ProductResponseDTO resp = productService.getProduct(id);
		if(resp==null) {
			throw new ProductNotFoundException("Product not found with id "+id);
		}
		return ResponseEntity.ok(resp);
	}
	
	@GetMapping("/getProduct/v1/{id}")
	public ResponseEntity<ProductResponseeDTO> getProduct1(@PathVariable String id){
		ProductResponseeDTO resp = productService.getProduct1(id);
		if(resp==null) {
			throw new ProductNotFoundException("Product not found with id "+id);
		}
		return ResponseEntity.ok(resp);
	}
	
	@PostMapping("/products")
	public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProducRequesttDTO producRequesttDTO){
		ProductResponseDTO resp = productService.createProduct(producRequesttDTO);
		return ResponseEntity.ok(resp);
	}
	
	@PostMapping("/products1")
	public ResponseEntity<ProductResponseeDTO> createProduct1(@RequestBody ProductRequestDTO productRequestDTO){
		ProductResponseeDTO resp = productService.createProduct(productRequestDTO);
		return ResponseEntity.ok(resp);
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<Boolean> deleteProduct(@PathVariable String id){
		boolean resp = productService.deleteProduct(id);
		return ResponseEntity.ok(resp);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Boolean> putProduct(@PathVariable Long id,@RequestBody ProducRequesttDTO producRequesttDTO){
		Boolean resp = productService.updateProduct(id,producRequesttDTO);
		return ResponseEntity.ok(resp);
	}
	
	
 }
