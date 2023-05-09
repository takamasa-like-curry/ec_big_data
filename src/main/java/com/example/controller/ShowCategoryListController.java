package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.common.pageInfo;
import com.example.domain.Category;
import com.example.domain.FilterOfCategory;
import com.example.form.CategorySearchForm;
import com.example.service.ShowCategoryListService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/category-list")
public class ShowCategoryListController {

	@Autowired
	private ShowCategoryListService service;
	@Autowired
	private HttpSession session;

	@GetMapping("")
	public String showCategoryListPage(Model model, CategorySearchForm form, Integer PageNumberInteger) {
		System.out.println("=================================");
		System.out.println(form);
		System.out.println("=================================");
		session.setAttribute("categorySearchForm", form);

		FilterOfCategory filter = FilterOfCategory.createFilterByForm(form);

		// 総ページ数の情報
		int totalPages = service.getTotalPagesByFilter(filter);
		session.setAttribute("totalPages", totalPages);
		// 表示するページの情報
		int pageNumber = getSafePage(PageNumberInteger, totalPages);
		session.setAttribute("pageNumber", pageNumber);
		filter.setPage(pageNumber);
		// 階層の最大値の情報
		model.addAttribute("maxLevel", service.getMaxLevel());

		List<Category> categoryList = service.pickUpCategoryListByFilter(filter);
		model.addAttribute("categoryList", categoryList);

		// 親カテゴリリストの処理
		model.addAttribute("topCategoryList", service.getTopCategoryList());
		// 子カテゴリリストの処理
		if (form.getTopCategoryId() != null) {
			model.addAttribute("topCategoryName", form.getTopCategoryName());
			model.addAttribute("subCategory1List", service.getSubordinateCategoryList(form.getTopCategoryId()));
		}
		// 孫カテゴリの処理
		if (form.getSubCategory1Id() != null) {
			model.addAttribute("subCategory1Name", form.getSubCategory1Name());
			model.addAttribute("subCategory2List", service.getSubordinateCategoryList(form.getSubCategory1Id()));
		}
		return "category-list";
	}

	@GetMapping("/page")
	public String page(Model model, Integer page) {
		CategorySearchForm form = (CategorySearchForm) session.getAttribute("categorySearchForm");
		return showCategoryListPage(model, form, page);
	}

	@GetMapping("/back")
	public String back(Model model) {
		return showCategoryListPage(model, (CategorySearchForm)session.getAttribute("categorySearchForm"), (Integer)session.getAttribute("pageNumber"));

	}
	
	@GetMapping("/category")
	public String category(Model model,int categoryId) {
		List<Category> categoryList = service.getAncestorCategoryListByCategoryId(categoryId);
		return showCategoryListPage(model, CategorySearchForm.createFormByCategoryList(categoryList), null);
	}

	private Integer getSafePage(Integer pageNumber, int totalPages) {
		if (pageNumber == null || pageNumber < pageInfo.FIRST_PAGE.getValue() || pageNumber > totalPages) {
			pageNumber = pageInfo.FIRST_PAGE.getValue();
		}
		return pageNumber.intValue();
	}

}
