package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.Exception.ProductNotFoundException;
import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseeDTO;
import com.example.demo.service.ProductService;

import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// this class is for integration testing which tests controller classes 

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockitoBean
	private ProductService productService;
	
	@Test
	public void testGetAllProductEmptyResponse() throws Exception {
		List<ProductResponseeDTO> productResponseeDTO = new ArrayList<ProductResponseeDTO>();
		when(productService.getAllProducts1()).thenReturn(productResponseeDTO);
		mockMvc.perform(get("/api/getProducts"))
		.andExpect(status().is(200))
		.andExpect(content().string("[]"));
	}
	
	@Test
	public void testGetAllProductResponse() throws Exception {
		List<ProductResponseeDTO> productResponseeDTO = new ArrayList<ProductResponseeDTO>();
		ProductResponseeDTO productResponseDTO = new ProductResponseeDTO();
		productResponseDTO.setId(UUID.fromString("17f88b58-c6b1-4342-8930-00d119189de5"));;
		productResponseDTO.setDescription("testDescription");
		productResponseDTO.setImage("http://s3.cdwvhdb.com");
		productResponseDTO.setTitle("testTitle");
		productResponseeDTO.add(productResponseDTO);
		when(productService.getAllProducts1()).thenReturn(productResponseeDTO);
		mockMvc.perform(get("/api/getProducts"))
		.andExpect(status().is(200))
		.andExpect(content().string("[{\"category\":null,\"description\":\"testDescription\",\"id\":\"17f88b58-c6b1-4342-8930-00d119189de5\",\"image\":\"http://s3.cdwvhdb.com\",\"price\":null,\"title\":\"testTitle\"}]"));
	}
	
	@Test
	public void testCreateProductSuccess() throws Exception {
		ProductRequestDTO productRequestDTO = new ProductRequestDTO();
		productRequestDTO.setTitle("Laptop");
		productRequestDTO.setDescription("Best Laptop");
		productRequestDTO.setImage("http://s3.dhjdbhd.com");
		
		ProductResponseeDTO productResponseeDTO = new ProductResponseeDTO();
		productResponseeDTO.setId(UUID.fromString("17f88b58-c6b1-4342-8930-00d119189de5"));
		productResponseeDTO.setDescription("Best Laptop");
		productResponseeDTO.setTitle("Laptop");
		productResponseeDTO.setImage("http://s3.dhjdbhd.com");
		
		String requestJson = convertToJson(productRequestDTO);
		String responseJson = convertToJson(productResponseeDTO);
		
		when(productService.createProduct(productRequestDTO)).thenReturn(productResponseeDTO);
		
		mockMvc.perform(post("/api/products1").contentType(MediaType.APPLICATION_JSON).content(requestJson))
		.andExpect(status().isOk())
		.andExpect(content().string(responseJson));
	}
	
	
	@Test
	public void findProductByIDFailure() throws Exception {
		when(productService.getAllProducts1()).thenThrow(new ProductNotFoundException("product not found Exception"));
		mockMvc.perform(get("/api/getProduct/v1/17f88b58-c6b1-4342-8930-00d119189de5"))
		.andExpect(status().is(404))
		.andExpect(content().string("Product not found with id 17f88b58-c6b1-4342-8930-00d119189de5"));
		
	}
	
	private String convertToJson(Object object) throws JsonParseException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
	
	
	
	

}
