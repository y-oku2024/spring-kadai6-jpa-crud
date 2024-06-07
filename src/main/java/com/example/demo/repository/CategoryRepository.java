package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Category;

import jakarta.transaction.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	Optional<Category> findById(Integer id);
	
	@Transactional
	void deleteById(Integer id);
}
