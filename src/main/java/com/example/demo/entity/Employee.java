package com.example.demo.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Employee extends UserEntity{
	private String companyName;

}
