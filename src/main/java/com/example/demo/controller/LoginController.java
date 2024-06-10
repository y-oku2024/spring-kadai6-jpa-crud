package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Account account;

	@GetMapping({"/login", "/logout"})
	public String index() {
		session.invalidate();
		
		return "login";
	}

	@PostMapping("/login")
	public String login(
		@RequestParam("id") Integer id,
		Model model
	) {
		User user = null;
		
		Optional<User> record = userRepository.findById(id);
		
		if (record.isEmpty() == false) {
			user = record.get();
		}
		
		if (user == null) {
			model.addAttribute("error", "ログインできません");
			return "login";
		}
		
		String name = user.getName();
		account.setName(name);
		
		return "redirect:/items";
	}
}
