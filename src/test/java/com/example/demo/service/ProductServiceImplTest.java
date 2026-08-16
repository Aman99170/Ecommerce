package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.Exception.InvalidTitleException;
import com.example.demo.Exception.ProductNotFoundException;
import com.example.demo.dto.ProductResponseeDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Price;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repo.ProductRepo;


// This class is made for unit testing of product service method.
public class ProductServiceImplTest {

	@Mock
	private ProductRepo productRepo; // This is the dependency which we want to mock for our test.
	
	@Mock
    private ProductMapper productMapper; // This is the dependency which we want to mock for our test.
	
	@InjectMocks
	private ProductServiceImpl productServiceImpl; // This class is being tested and the above mock dependency will inject in this class.
	
	@BeforeEach // this will run before each test of this class.
	public void setup() {
		MockitoAnnotations.openMocks(this); // creates auto closable resources for each test method
	}
	
	@Test
	public void testFindProductByTitleSuccess() {
		String title = "testProdct";
		Product product = new Product();
		product.setId(UUID.randomUUID());
		product.setDescription("testDescription");
		product.setTitle(title);
		product.setImage("http://s3.djhdh.com");
		Category category = new Category();
		category.setCategoryType("mockCategory");
		Price price = new Price();
		price.setAmount(10000);
		product.setCategory(category);
		product.setPrice(price);
		Optional<Product> mockProduct = Optional.of(product);
		when(productRepo.findByTitle(any())).thenReturn(mockProduct);
		
		ProductResponseeDTO responseDTO = new ProductResponseeDTO();
	    responseDTO.setTitle(title);

	    when(productMapper.toDto(product))
	            .thenReturn(responseDTO);
		
		ProductResponseeDTO actualResponse = productServiceImpl.getProductByTitle(title);
		
		assertEquals(actualResponse.getTitle(),product.getTitle());
		
	}
	
	@Test
	public void testFindByProductNotFound() {
		String title="testProduct";
		when(productRepo.findByTitle(title)).thenReturn(null);
		assertThrows(ProductNotFoundException.class, ()->productServiceImpl.getProductByTitle(title));
	}
	
	
	@Test
	public void testFindProductByTitleInvalidTtile() {
		String title = "";
		Product product = new Product();
		product.setId(UUID.randomUUID());
		product.setDescription("testDescription");
		product.setTitle(title);
		product.setImage("http://s3.djhdh.com");
		Category category = new Category();
		category.setCategoryType("mockCategory");
		Price price = new Price();
		price.setAmount(10000);
		product.setCategory(category);
		product.setPrice(price);
		assertThrows(InvalidTitleException.class, ()->productServiceImpl.getProductByTitle(title));
	}
}
