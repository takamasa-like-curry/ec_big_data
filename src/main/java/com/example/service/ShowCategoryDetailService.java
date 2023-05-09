package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Category;
import com.example.mapper.CategoriesMapper;
import com.example.mapper.CategoryTreePathsMapper;

/**
 * カテゴリ詳細表示ページの業務処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Service
@Transactional
public class ShowCategoryDetailService {

	@Autowired
	private CategoriesMapper categoriesMapper;
	@Autowired
	private CategoryTreePathsMapper categoryTreePathsMapper;

	/**
	 * カテゴリIDからカテゴリ情報を取得.
	 * 
	 * @param categoryId カテゴリID
	 * @return 該当カテゴリ情報
	 */
	public Category getCategoryByCategoryId(int categoryId) {
		Category category = categoriesMapper.load(categoryId);
		category.setAncestorCategoryList(categoriesMapper.findByDescendantId(category.getId()));
		return category;
	}
	
	public void logicalDelete(int categoryId) {
		List<Category> logicalDeleteCategoryList = categoriesMapper.findCategoryWithDescendants(categoryId);
		System.out.println(logicalDeleteCategoryList);
		categoryTreePathsMapper.logicalDeleteCategoryWithDescendants(categoryId);
		for(Category category : logicalDeleteCategoryList) {
			categoriesMapper.logicalDelete(category.getId());
		}
		
	}
	
	

}
