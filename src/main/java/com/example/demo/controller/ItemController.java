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
import com.example.demo.entity.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	@Autowired
	ItemRepository itemRepository; // itemsテーブル操作用

	@Autowired
	CategoryRepository categoryRepository;

	// 商品一覧表示
	@GetMapping("/")
	public String index(Model model) {
		// itemsテーブルから全商品の一覧を取得
		List<Item> itemList = itemRepository.findAll();
		// Thymeleafにデータを渡す
		model.addAttribute("items", itemList);
		// items.htmlを出力
		return "items";
	}

	// 商品一覧表示（カテゴリーによる絞り込み）
	@GetMapping("/items")
	public String items(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {

		// categoryテーブルから全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		// 商品一覧情報の取得
		List<Item> itemList = null;
		if (categoryId == null) {
			itemList = itemRepository.findAll();
		} else {
			// itemsテーブルをカテゴリーIDを指定して一覧を取得
			itemList = itemRepository.findByCategoryId(categoryId);
		}
		model.addAttribute("items", itemList);

		return "items";
	}

	// 商品追加表示
	@GetMapping("/items/add")
	public String creater(
			Model model) {

		return "addItem";
	}

	// 商品追加
	@PostMapping("/items/add")
	public String store(
			@RequestParam("categoryId") Integer categoryId,
			@RequestParam("name") String name,
			@RequestParam("price") Integer price,
			Model model) {

		Item i = new Item(categoryId, name, price);

		itemRepository.save(i);

		index(model);
		return "items";

		//return "redirect:/items";
	}

	// 指定した商品IDの更新画面を表示
	@GetMapping("/items/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			Model model) {

		Item item = itemRepository.findById(id).get();
		model.addAttribute("item", item);

		return "editItem";

		//		return "redirect/items";
	}

	// 更新処理
	@PostMapping("/items/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam("categoryId") Integer categoryId,
			@RequestParam("name") String name,
			@RequestParam("price") Integer price,
			Model model) {

		Item i = new Item(id, categoryId, name, price);

		itemRepository.save(i);

		index(model);
		return "items";
		//		return "redirect/items";
	}
	// 削除処理

	@PostMapping("/items/{id}/delete")
	public String delete(
			@PathVariable("id") Integer id, Model model) {

		itemRepository.deleteByid(id);

		index(model);
		return "items";
		//		return "redirect/items";
	}

}
