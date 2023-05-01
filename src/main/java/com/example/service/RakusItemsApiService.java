package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.common.CategoryLevel;
import com.example.domain.Brand;
import com.example.domain.Category;
import com.example.mapper.BrandsMapper;
import com.example.mapper.CategoriesMapper;

@Service
@Transactional
public class RakusItemsApiService {

	@Autowired
	private CategoriesMapper categoriesMapper;
	@Autowired
	private BrandsMapper brandsMapper;

	public Boolean checkCategoryNameDuplication(String categoryName, Integer ancestorId) {
		int level;
		if (ancestorId == null) {
			level = CategoryLevel.PARENT.getLevel();
		} else {
			level = categoriesMapper.pickUpLevelById(ancestorId);
			level++;
		}
		return categoriesMapper.checkCategoryNameDuplication(categoryName, ancestorId, level);
	}

	public List<Category> pickUpSubordinateCategoryList(int CategoryId) {
		List<Category> subordinateCategoryList = categoriesMapper.pickUpSubordinateCategoryList(CategoryId);
		//孫カテゴリを取得の際はこれで終わり
		if(subordinateCategoryList.get(0).getLevel() == CategoryLevel.GRAND_CHILD.getLevel()) {
			return subordinateCategoryList;
		}
		for (int i = 0; i < subordinateCategoryList.size(); i++) {
			if (!categoriesMapper.existsDescendantCategory(subordinateCategoryList.get(i).getId())) {
				subordinateCategoryList.remove(i);
				i--;
			}
		}
		return subordinateCategoryList;
	}
	
	public List<Brand> pickUpBrandListByBrandName(String brandName){
		brandName = "%" + brandName + "%";
		return brandsMapper.findByName(brandName);
	}
}
