package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.common.CategoryInfo;
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
			level = CategoryInfo.TOP_CATEGORY.getLevel();
		} else {
			level = categoriesMapper.pickUpLevelById(ancestorId);
			level++;
		}
		return categoriesMapper.checkCategoryNameDuplication(categoryName, ancestorId, level);
	}

	/**
	 * 2階層下には要素を持たない1階層下のカテゴリリストを取得.
	 * 
	 * @param CategoryId 親となるカテゴリID
	 * @return 該当カテゴリリスト
	 */
	public List<Category> getSubordinateBranchCategoryList(int CategoryId) {
		List<Category> subordinateCategoryList = categoriesMapper.findSubordinateCategoryList(CategoryId);
		// 孫カテゴリを取得の際はこれで終わり
		if (subordinateCategoryList.get(0).getLevel() == CategoryInfo.SUB_CATEGORY_2.getLevel()) {
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

	/**
	 * 一つ下の階層のカテゴリリストを取得.
	 * 
	 * @param CategoryId 親となるカテゴリID
	 * @return 該当カテゴリリスト
	 */
	public List<Category> getSubordinateCategoryList(int CategoryId) {
		List<Category> subordinateCategoryList = categoriesMapper.findSubordinateCategoryList(CategoryId);
		return subordinateCategoryList;
	}

	public List<Brand> pickUpBrandListByBrandName(String brandName) {
		brandName = "%" + brandName + "%";
		return brandsMapper.findByName(brandName);
	}

	/**
	 * ブランド名の重複を確認
	 * 
	 * @param brand ブラウザから受け取ったブランド名
	 * @return 重複があればtrue,重複がなければfalse
	 */
	public boolean brandNameExistsInDb(String brandName) {
		return brandsMapper.isExists(brandName);
	}
}
