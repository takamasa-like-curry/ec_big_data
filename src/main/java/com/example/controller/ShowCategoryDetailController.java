package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.ShowCategoryDetailService;

/**
 * カテゴリ詳細画面を表示するクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Controller
@RequestMapping("/category-detail")
public class ShowCategoryDetailController {

	@Autowired
	private ShowCategoryDetailService service;

	/**
	 * カテゴリ詳細画面を表示.
	 * 
	 * @param model      モデル
	 * @param categoryId 詳細情報を表示するカテゴリID
	 * @return カテゴリ詳細画面
	 */
	@GetMapping("")
	public String showCategoryDetail(Model model, int categoryId) {
		model.addAttribute("category", service.getCategoryByCategoryId(categoryId));
		model.addAttribute("categoryId", categoryId);
		return "category-detail";
	}

	/**
	 * カテゴリの論理削除.
	 * 
	 * @param categoryId カテゴリID
	 * @return 商品一覧画面
	 */
	@GetMapping("/delete")
	public String logicalDelete(int categoryId) {
		
		service.logicalDelete(categoryId);
		
		return "redirect:/category-list"; //ここは後ほど変更の可能性あり！！！！！！！！！！！！

	}
}
