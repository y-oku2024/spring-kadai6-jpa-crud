package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;

@Controller
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;

	// カテゴリ一覧表示
	@GetMapping("/categories/index")
	public String index(Model model) {
		// itemsテーブルから全商品の一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		// Thymeleafにデータを渡す
		model.addAttribute("categories", categoryList);
		// items.htmlを出力
		return "categories";
	}

	// カテゴリー追加表示
	@GetMapping("/categories/add")
	public String creater(
			Model model) {

		return "addCategory";
	}

	// カテゴリー追加
	@PostMapping("/categories/add")
	public String store(
			@RequestParam("name") String name,
			Model model) {

		Category c = new Category(name);

		categoryRepository.save(c);

		return "redirect:/categories/index";
	}

	// 指定した商品IDの更新画面を表示
	@GetMapping("/categories/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			Model model) {

		Category category = categoryRepository.findById(id).get();
		model.addAttribute("category", category);

		return "editCategory";

		//		return "redirect/items";
	}

	// 更新処理
	@PostMapping("/categories/{id}/edit")
	public String update(
			@RequestParam("name") String name,
			Model model) {

		Category category = new Category(name);

		categoryRepository.save(category);

		return "redirect:/categories/index";
	}

	// 削除処理
	@PostMapping("/categories/{id}/delete")
	public String delete(
			@PathVariable("id") Integer id, Model model) {

		categoryRepository.deleteById(id);

		return "redirect:/categories/index";
	}

}
