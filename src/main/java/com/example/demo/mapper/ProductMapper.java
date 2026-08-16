package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.dto.FakeProductRequestDTO;
import com.example.demo.dto.FakeProductResponseDTO;
import com.example.demo.dto.ProducRequesttDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.dto.ProductResponseeDTO;
import com.example.demo.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	FakeProductRequestDTO toDto(ProducRequesttDTO producRequesttDTO);
	
	ProductResponseDTO toDto(FakeProductResponseDTO FakeProductResponseDTO);
	
	ProductResponseeDTO toDto(Product product);
	
	List<ProductResponseDTO> toDtoList(List<FakeProductResponseDTO> fakeProductResponseDTO);
	
	List<ProductResponseeDTO> toDtoProductList(List<Product> product);
}
