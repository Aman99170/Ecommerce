package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.client.FakeDemoApiClient;
import com.example.demo.dto.FakeProductRequestDTO;
import com.example.demo.dto.FakeProductResponseDTO;
import com.example.demo.dto.ProducRequesttDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.mapper.ProductMapper;

@Service("fakeProductService")
public class FakeProductServiceImpl implements ProductService {
	
	private FakeDemoApiClient fakeDemoApiClient;
	private ProductMapper productMapper;
	
	public FakeProductServiceImpl(FakeDemoApiClient fakeDemoApiClient,ProductMapper productMapper) {
		this.fakeDemoApiClient = fakeDemoApiClient;
		this.productMapper = productMapper;
	}

	@Override
	public List<ProductResponseDTO> getAllProducts() {
		List<FakeProductResponseDTO> resp=  fakeDemoApiClient.getAllProducts();
		return productMapper.toDtoList(resp);
	}

	@Override
	public ProductResponseDTO getProduct(int id) {
		FakeProductResponseDTO resp = fakeDemoApiClient.getProduct(id);
		ProductResponseDTO productResponseDTO = productMapper.toDto(resp);
		return productResponseDTO;
	}

	@Override
	public ProductResponseDTO createProduct(ProducRequesttDTO producRequesttDTO) {
		FakeProductRequestDTO fakeProductRequestDTO = productMapper.toDto(producRequesttDTO);
		FakeProductResponseDTO resp = fakeDemoApiClient.createProduct(fakeProductRequestDTO);
		ProductResponseDTO productResponseDTO = productMapper.toDto(resp);
		return productResponseDTO;
		
	}

	@Override
	public boolean deleteProduct(String id) {
		return fakeDemoApiClient.deleteProduct(id);
	}

	@Override
	public Boolean updateProduct(Long id,ProducRequesttDTO producRequesttDTO) {
		FakeProductRequestDTO fakeProductRequestDTO = productMapper.toDto(producRequesttDTO);
		return fakeDemoApiClient.updateProduct(id, fakeProductRequestDTO);
	}

}
