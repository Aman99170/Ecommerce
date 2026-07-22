package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.dto.FakeProductRequestDTO;
import com.example.demo.dto.FakeProductResponseDTO;
import com.example.demo.dto.ProducRequesttDTO;
import com.example.demo.dto.ProductResponseDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	FakeProductRequestDTO toDto(ProducRequesttDTO producRequesttDTO);
	
	ProductResponseDTO toDto(FakeProductResponseDTO FakeProductResponseDTO);
	
	List<ProductResponseDTO> toDtoList(List<FakeProductResponseDTO> fakeProductResponseDTO);
}
