package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.common.NullValue;
import com.example.domain.Category;
import com.example.form.CategoryEditForm;
import com.example.service.CategoryEditService;

@Controller
@RequestMapping("category-edit")
public class CategoryEditController {

	@Autowired
	private CategoryEditService service;

	@GetMapping("")
	public String showCategoryEditPage(Model model, CategoryEditForm form, int categoryId) {

		if (form.getId() == NullValue.CATEGORY_ID.getValue()) {
			Category category = service.getCategoryByCategoryId(categoryId);
			form.setId(category.getId());
			form.setName(category.getName());
			form.setDescription(category.getDescription());
		}

		return "category-edit";
	}

	@PostMapping("/update")
	public String update(Model model, @Validated CategoryEditForm form, BindingResult br) {
		if (br.hasErrors()) {
			return showCategoryEditPage(model, form, form.getId());
		}
		service.updateCategoryByForm(form);

		return "redirect:/category-list";
	}

}
