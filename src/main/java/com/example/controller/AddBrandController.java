package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.AddBrandService;

/**
 * ブランド追加画面を操作するクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Controller
@RequestMapping("/add-brand")
public class AddBrandController {

	@Autowired
	private AddBrandService service;

	@GetMapping("")
	public String showAddBrandPage(Model model, String brandName) {
		return "add-brand";
	}

	@PostMapping("insert")
	public String insert(Model model, String brandName) {
		if ("".equals(brandName)) {
			model.addAttribute("message", "新規追加するブランド名を入力してください");
			model.addAttribute("brandName", brandName);
			return showAddBrandPage(model, brandName);
		}
		if (!service.existsInDb(brandName)) {
			model.addAttribute("message", "このブランド名は使用できません。[既に使用済みのブランド名です]");
			model.addAttribute("brandName", brandName);
			return showAddBrandPage(model, brandName);
		}

		service.insert(brandName);
		return "redirect:/";
	}

}
