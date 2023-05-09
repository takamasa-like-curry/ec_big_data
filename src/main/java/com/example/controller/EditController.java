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

import com.example.common.CategoryInfo;
import com.example.common.NullValue;
import com.example.domain.Category;
import com.example.domain.Item;
import com.example.form.ItemForm;
import com.example.service.EditService;

@Controller
@RequestMapping("/edit")
public class EditController {

	@Autowired
	private EditService service;

	@GetMapping("")
	public String showEditPage(Model model, ItemForm form, Integer itemId, BindingResult br) {

		model.addAttribute("itemId", itemId);
		Item item = service.load(itemId);

		if (!br.hasErrors()) {
			form.setId(itemId);
			form.setInputName(item.getName());
			form.setPrice(String.valueOf(item.getPrice()));

			form.setParentCategoryId(item.getCategoryList().get(CategoryInfo.TOP_CATEGORY.getLevel()).getId());
			form.setChildCategoryId(item.getCategoryList().get(CategoryInfo.SUB_CATEGORY_1.getLevel()).getId());
			form.setGrandChildCategoryId(item.getCategoryList().get(CategoryInfo.SUB_CATEGORY_2.getLevel()).getId());

			if (item.getBrand() != null) {
				form.setBrandId(item.getBrand().getId());
				form.setBrandName(item.getBrand().getName());
			}
			form.setCondition(item.getCondition());
			form.setDescription(item.getDescription());

		}
		// 親カテゴリの処理
		List<Category> parentCategoryList = service.pickUpCategoryListByAncestorIdAndLevel(
				NullValue.CATEGORY_ID.getValue(), CategoryInfo.TOP_CATEGORY.getLevel());
		model.addAttribute("parentCategoryList", parentCategoryList);

		if (form.getParentCategoryId() != null) {
			List<Category> childCategoryList = service
					.pickUpCategoryListByAncestorIdAndLevel(form.getParentCategoryId(), CategoryInfo.SUB_CATEGORY_1.getLevel());
			model.addAttribute("childCategoryList", childCategoryList);
		}
		if (form.getChildCategoryId() != null) {
			List<Category> grandChildCategoryList = service.pickUpCategoryListByAncestorIdAndLevel(
					form.getChildCategoryId(), CategoryInfo.SUB_CATEGORY_2.getLevel());
			model.addAttribute("grandChildCategoryList", grandChildCategoryList);
		}

		return "edit";
	}

	@PostMapping("/update")
	public String insert(Model model, @Validated ItemForm form, BindingResult br) {

		// 金額のチェック
		if (!("".equals(form.getPrice()))) {
			try {
				Double.parseDouble(form.getPrice());
			} catch (Exception e) {
				br.rejectValue("price", null, "価格を数値で入力してください(単位は$です)");
			}
		}

		// エラーがあれば入力画面に遷移
		if (br.hasErrors()) {
			return showEditPage(model, form, form.getId(), br);
		}

		service.upDateItem(form); // updateに書き換え

		return "redirect:/";
	}
}
