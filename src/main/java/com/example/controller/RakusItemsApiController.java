package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Category;
import com.example.service.RakusItemsApiService;

@RestController
@RequestMapping("/api")
public class RakusItemsApiController {

	@Autowired
	private RakusItemsApiService service;

	@GetMapping("/check-category-name")
	public ResponseEntity<Boolean> checkChildCategory(String categoryName, Integer categoryId) {
		Boolean result = service.checkCategoryNameDuplication(categoryName, categoryId);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/pick-up-subordinate-category-list")
	public Map<String, List<Category>> pickUpSubordinateCategoryList(Integer categoryId) {

		Map<String, List<Category>> map = new HashMap<>();
		if (categoryId != null) {
			map.put("categoryList", service.pickUpSubordinateCategoryList(categoryId));
		}
		return map;

	}

}
