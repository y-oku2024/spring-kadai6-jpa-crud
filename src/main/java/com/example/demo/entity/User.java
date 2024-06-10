package com.example.demo.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Component
@Data
@Entity
@Table(name = "Users")
public class User {
	@Id
	private Integer id;
	private String name;
	private Integer age;

}
