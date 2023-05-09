package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Category;
import com.example.form.CategoryEditForm;
import com.example.mapper.CategoriesMapper;

@Service
@Transactional
public class CategoryEditService {

	@Autowired
	private CategoriesMapper categoriesMapper;

	public Category getCategoryByCategoryId(int categoryId) {
		return categoriesMapper.load(categoryId);
	}

	public void updateCategoryByForm(CategoryEditForm from) {
		categoriesMapper.update(from);
	}

}
