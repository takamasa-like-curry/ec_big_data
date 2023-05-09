package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.common.pageInfo;
import com.example.domain.Category;
import com.example.domain.Item;
import com.example.filter.ItemFilter;
import com.example.form.ItemSearchForm;
import com.example.service.ShowListService;

import jakarta.servlet.http.HttpSession;

/**
 * 商品一覧画面を操作するクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Controller
@RequestMapping("")
public class ShowListController {

	@Autowired
	private ShowListService service;
	@Autowired
	private HttpSession session;

	/**
	 * 商品一覧画面を表示する.
	 * 
	 * @param model             モデル
	 * @param form              商品検索フォーム
	 * @param pageNumberInteger 表示ページ
	 * @return
	 */
	@GetMapping("")
	public String showList(Model model, ItemSearchForm form, Integer pageNumberInteger) {

		// sessionにフォームを追加
		session.setAttribute("itemSearchForm", form);

		// formからfilterへ変換
		ItemFilter filter = ItemFilter.createByForm(form);
		// 総ページ数
		int totalPages = service.getTotalPagesByFilter(filter);
		model.addAttribute("totalPages", totalPages);

		// 表示するページ
		if (pageNumberInteger == null) {
			pageNumberInteger = pageInfo.FIRST_PAGE.getValue();
		}
		int pageNumber = getSafePage(pageNumberInteger, totalPages);
		session.setAttribute("pageNumber", pageNumber);

		filter.setPage(pageNumber);

		List<Item> itemlist = service.getItemListByFilter(filter);

		model.addAttribute("itemList", itemlist);

		// 親カテゴリの処理
		List<Category> topCategoryList = service.getTopCategoryList();
		model.addAttribute("topCategoryList", topCategoryList);

		// 子カテゴリ・孫カテゴリの処理
		if (form.getTopCategoryId() != null) {
			List<Category> subCategory1List = service.getSubordinateCategoryList(form.getTopCategoryId());
			model.addAttribute("subCategory1List", subCategory1List);
		}
		if (form.getSubCategory2Id() != null) {
			List<Category> subCategory2List = service.getSubordinateCategoryList(form.getSubCategory1Id());
			model.addAttribute("subCategory2List", subCategory2List);
		}

		return "list";
	}

	@GetMapping("/back")
	public String backPage(Model model) {
		Integer page = (int) session.getAttribute("pageNumber");
		ItemSearchForm form = (ItemSearchForm) session.getAttribute("itemSearchForm");
		return showList(model, form, page);
	}

	@GetMapping("/category")
	public String serchByCategory(Model model, Integer categoryId) {
		List<Category> categoryList = service.getAncestorCategoryListWithSelf(categoryId);

		return showList(model, ItemSearchForm.createFormByCategoryList(categoryList), null);
	}

	@GetMapping("/brand")
	public String serchByBrand(Model model, Integer brandId) {
		String brandName = service.getBrandNameByBrandId(brandId);
		return showList(model, ItemSearchForm.createFormByBrandIdAndBrandName(brandId, brandName), null);
	}

	@GetMapping("/page")
	public String jumpPage(Model model, String page) {

		Integer pageInteger;
		try {
			pageInteger = Integer.valueOf(page);

		} catch (Exception e) {
			pageInteger = pageInfo.FIRST_PAGE.getValue();
		}
		ItemSearchForm form = (ItemSearchForm) session.getAttribute("itemSearchForm");

		return showList(model, form, pageInteger);
	}

	/**
	 * 安全なint型のページを取得.
	 * 
	 * @param pageNumber Integer型のページ数
	 * @param totalPages 総ページ数
	 * @return
	 */
	private int getSafePage(Integer pageNumber, int totalPages) {
		if (pageNumber == null || pageNumber < pageInfo.FIRST_PAGE.getValue() || pageNumber > totalPages) {
			pageNumber = pageInfo.FIRST_PAGE.getValue();
		}
		return pageNumber.intValue();
	}

}
