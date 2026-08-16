package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Exception.InvalidTitleException;
import com.example.demo.Exception.ProductNotFoundException;
import com.example.demo.client.FakeDemoApiClient;
import com.example.demo.dto.FakeProductRequestDTO;
import com.example.demo.dto.FakeProductResponseDTO;
import com.example.demo.dto.ProducRequesttDTO;
import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.dto.ProductResponseeDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Price;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.PriceRepo;
import com.example.demo.repo.ProductRepo;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	private ProductMapper productMapper;
	private ProductRepo productRepo;
	private CategoryRepo categoryRepo;
	private PriceRepo priceRepo;
	
	public ProductServiceImpl(ProductMapper productMapper,ProductRepo productRepo,CategoryRepo categoryRepo,PriceRepo priceRepo ) {
		this.productMapper = productMapper;
		this.productRepo = productRepo;
		this.categoryRepo = categoryRepo;
		this.priceRepo = priceRepo;
	}

	@Override
	public List<ProductResponseeDTO> getAllProducts1() {
		List<Product> products= productRepo.findAll();
		List<ProductResponseeDTO> resp = productMapper.toDtoProductList(products);
		return resp;
	}

	@Override
	public ProductResponseeDTO getProduct1(String id) {
		UUID id1 = UUID.fromString(id);
		Optional<Product> product = productRepo.findById(id1);
		if(product.isPresent()) {
			ProductResponseeDTO productResponseDTO = productMapper.toDto(product.get());
			return productResponseDTO;
		}else {
			throw new ProductNotFoundException("Product not found with id "+id);
		}
		
	}
	
	@Override
	public ProductResponseeDTO getProductByTitle(String title) {
		if(title==null || title=="") {
			throw new InvalidTitleException("Invalid title"+title);
		}
		Optional<Product> product = productRepo.findByTitle(title);
		System.out.println(product);
		if(product!=null && product.isPresent()) {
			ProductResponseeDTO productResponseDTO = productMapper.toDto(product.get());
			return productResponseDTO;
		}else {
			throw new ProductNotFoundException("Product not found with title "+title);
		}
		
	}

	@Override
	public ProductResponseeDTO createProduct(ProductRequestDTO producRequesttDTO) {
		Product product = new Product();
		Category category = categoryRepo.findByCategoryType(producRequesttDTO.getCategory());
		Price price = new Price();
		price.setAmount(producRequesttDTO.getPrice().getAmount());
		price.setCurrency(producRequesttDTO.getPrice().getCurrency());
		price.setDiscount(producRequesttDTO.getPrice().getDiscount());
		Price p =priceRepo.save(price);
		product.setCategory(category);
		product.setDescription(producRequesttDTO.getDescription());
		product.setImage(producRequesttDTO.getImage());
		product.setTitle(producRequesttDTO.getTitle());
		product.setPrice(p);
		Product respProduct = productRepo.save(product);
		return productMapper.toDto(respProduct);
		
	}

	@Override
	public boolean deleteProduct(String id) {
		UUID id1 = UUID.fromString(id);
		Optional<Product> product = productRepo.findById(id1);
		if(product.isPresent()) {
			productRepo.delete(product.get());
			return true;
		}else {
			throw new ProductNotFoundException("Product not found with id "+id);
		}
		
		
	}

	@Override
	public Boolean updateProduct(UUID id,ProductRequestDTO producRequesttDTO) {
		Optional<Product> product = productRepo.findById(id);
		if(product.isPresent()) {
			Product p = product.get();
			Category category = categoryRepo.findByCategoryType(producRequesttDTO.getCategory());
			p.setCategory(category);
			p.setDescription(producRequesttDTO.getDescription());
			p.setImage(producRequesttDTO.getImage());
			p.setPrice(producRequesttDTO.getPrice());
			p.setTitle(producRequesttDTO.getTitle());
			productRepo.save(p);
			return true;
		}else {
			throw new ProductNotFoundException("Product not found with id "+id);
		}
	}

}

