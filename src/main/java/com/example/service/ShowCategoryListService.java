package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.common.CategoryInfo;
import com.example.common.NullValue;
import com.example.common.pageInfo;
import com.example.domain.Category;
import com.example.filter.FilterOfCategory;
import com.example.mapper.CategoriesMapper;

@Service
@Transactional
public class ShowCategoryListService {

	@Autowired
	private CategoriesMapper categoriesMapper;

	public List<Category> pickUpCategoryListByFilter(FilterOfCategory filter) {

		List<Category> categoryList = categoriesMapper.findByFilter(filter);
		return addAncestorCategoryList(categoryList);
	}

	private List<Category> addAncestorCategoryList(List<Category> categoryList) {

		for (Category category : categoryList) {
			category.setAncestorCategoryList(categoriesMapper.findByDescendantId(category.getId()));
		}
		return categoryList;
	}

	public int getTotalPagesByFilter(FilterOfCategory filter) {
		return categoriesMapper.countQuantiryByFilter(filter) / pageInfo.PAGE_SIZE.getValue() + 1;

	}

	public int getMaxLevel() {
		return categoriesMapper.findMaxLevel();
	}

	public List<Category> getTopCategoryList() {
		return categoriesMapper.findByAncestorIdAndLevel(NullValue.CATEGORY_ID.getValue(),
				CategoryInfo.TOP_CATEGORY.getLevel());
	}

	public List<Category> getSubordinateCategoryList(int categoryId) {
		return categoriesMapper.findSubordinateCategoryList(categoryId);
	}

	public List<Category> getAncestorCategoryListByCategoryId(int categoryId) {
		return categoriesMapper.findByDescendantId(categoryId);
	}
}
