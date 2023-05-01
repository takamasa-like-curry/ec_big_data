package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Category;
import com.example.form.ItemForm;
import com.example.service.AddNewItemService;

@Controller
@RequestMapping("/add")
public class AddNewItemController {

	@Autowired
	private AddNewItemService service;

	/**
	 * 追加商品入力画面を表示.
	 * 
	 * @param model モデル
	 * @param form  追加商品情報フォーム
	 * @return 追加商品入力画面
	 */
	@GetMapping("")
	public String showAddNewItemPage(Model model, ItemForm form) {
		
		// 親カテゴリの処理
		List<Category> parentCategoryList = service.pickUpParentCategoryList();
		model.addAttribute("parentCategoryList", parentCategoryList);

		if (form.getParentCategoryId() != null) {
			List<Category> childCategoryList = service.pickUpSubordinateCategoryList(form.getParentCategoryId());
			model.addAttribute("childCategoryList", childCategoryList);
		}
		if (form.getChildCategoryId() != null) {
			List<Category> grandChildCategoryList = service.pickUpSubordinateCategoryList(form.getChildCategoryId());
			model.addAttribute("grandChildCategoryList", grandChildCategoryList);
		}
		
		model.addAttribute("brandName",form.getBrandName());

		return "add-item";
	}

	@PostMapping("/insert")
	public String insert(Model model, @Validated ItemForm form, BindingResult br) {
		
		// エラーがあれば入力画面に遷移
		if (br.hasErrors()) {
			return showAddNewItemPage(model, form);
		}

		// カテゴリの入力値チェック
		if (!service.existsDescendantCategory(form.getChildCategoryId())) {
			br.rejectValue("childCategoryId", null, "現在、このカテゴリは選択いただけません。");
			System.out.println("孫カテゴリが存在しません");
			return showAddNewItemPage(model, form);
		}

		// 金額のチェック
		if (!("".equals(form.getPrice()))) {
			try {
				Double.parseDouble(form.getPrice());
			} catch (Exception e) {
				br.rejectValue("price", null, "価格を数値で入力してください(単位は$です)");
				return showAddNewItemPage(model, form);
			}
		}
		service.insertItem(form);

		return "redirect:/";
	}
}
