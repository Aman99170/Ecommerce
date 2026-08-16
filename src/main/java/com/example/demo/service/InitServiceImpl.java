package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Price;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductOrder;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.OrderRepo;
import com.example.demo.repo.PriceRepo;
import com.example.demo.repo.ProductRepo;

@Service
public class InitServiceImpl implements InitService{
	
	private final CategoryRepo categoryRepo;
	private final OrderRepo orderRepo;
	private final ProductRepo productRepo;
	private final PriceRepo priceRepo;
	
	public InitServiceImpl(CategoryRepo categoryRepo,OrderRepo orderRepo,ProductRepo productRepo,PriceRepo priceRepo) {
		this.categoryRepo = categoryRepo;
		this.orderRepo = orderRepo;
		this.productRepo = productRepo;
		this.priceRepo = priceRepo;
	}

	@Override
	public void initialize() {
		Category category = new Category();
		category.setCategoryType("Electornics");
		categoryRepo.save(category);
		
		Price iphone = new Price();
		iphone.setCurrency("INR");
		iphone.setAmount(100000);
		iphone.setDiscount(0);
		
		Price macbook = new Price();
		macbook.setCurrency("INR");
		macbook.setAmount(150000);
		macbook.setDiscount(10);
		
		Price smartWatch = new Price();
		smartWatch.setCurrency("INR");
		smartWatch.setAmount(10000);
		smartWatch.setDiscount(0);
		
		priceRepo.save(iphone);
		priceRepo.save(macbook);
		priceRepo.save(smartWatch);
		
		
		Product iphoneProduct = new Product();
		iphoneProduct.setTitle("Apple Iphone");
		iphoneProduct.setDescription("A mobile phone by Apple brand");
		iphoneProduct.setImage("http://s3.dhkbk.com");
		iphoneProduct.setPrice(iphone);
		iphoneProduct.setCategory(category);
		
		Product macbookProduct = new Product();
		macbookProduct.setTitle("Apple Macbook");
		macbookProduct.setDescription("A Laptop by Apple brand");
		macbookProduct.setImage("http://s3.dhkbkee.com");
		macbookProduct.setPrice(macbook);
		macbookProduct.setCategory(category);
		
		Product smartWatchProduct = new Product();
		smartWatchProduct.setTitle("Apple Watch");
		smartWatchProduct.setDescription("A smart watch by Apple brand");
		smartWatchProduct.setImage("http://s3.dhkbk3r4.com");
		smartWatchProduct.setPrice(smartWatch);
		smartWatchProduct.setCategory(category);
		
		productRepo.save(iphoneProduct);
		productRepo.save(macbookProduct);
		productRepo.save(smartWatchProduct);
		
		
		ProductOrder order = new ProductOrder();
		order.setProductList(List.of(iphoneProduct,macbookProduct,smartWatchProduct));
		
		orderRepo.save(order);
		
		
		
	}

}
