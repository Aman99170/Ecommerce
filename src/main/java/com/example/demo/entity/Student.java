package com.example.demo.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Student extends UserEntity{
	private int classStandard;
	private String section;

}
