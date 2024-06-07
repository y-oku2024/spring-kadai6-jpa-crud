package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

import jakarta.transaction.Transactional;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	// カテゴリーIDによる検索
	// SELECT * FROM items WHERE category_id = ?
	List<Item> findByid(Integer id);

	List<Item> findByCategoryId(Integer categoryId);

	@Transactional
	List<Item> deleteByid(Integer id);
}
