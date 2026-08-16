package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Price extends BaseEntity{
	
	private String currency;
	private double amount;
	private double discount;
	
}
