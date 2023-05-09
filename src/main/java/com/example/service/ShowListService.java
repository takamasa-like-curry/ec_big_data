package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.common.CategoryInfo;
import com.example.common.NullValue;
import com.example.common.pageInfo;
import com.example.domain.Category;
import com.example.domain.Item;
import com.example.filter.ItemFilter;
import com.example.mapper.BrandsMapper;
import com.example.mapper.CategoriesMapper;
import com.example.mapper.ItemsMapper;

@Service
@Transactional
public class ShowListService {

	@Autowired
	private ItemsMapper itemsMapper;
	@Autowired
	private CategoriesMapper categoriesMapper;
	@Autowired
	private BrandsMapper brandsMapper;

	/**
	 * 指定階層のカテゴリリストを取得.
	 * 
	 * 主に、検索カテゴリ選択フォームの表示に使用
	 * 
	 * @param level カテゴリの階層
	 * @return 該当階層のカテゴリリスト
	 */

	public List<Category> pickUpCategoryListByDescendantId(Integer descendantId) {
		List<Category> categoryList = categoriesMapper.findByDescendantId(descendantId);
		return categoryList;
	}

	public List<Category> pickUpCategoryListByAncestorIdAndLevel(Integer ancestorId, Integer level) {
		List<Category> categoryList = categoriesMapper.findByAncestorIdAndLevel(ancestorId, level);
		return categoryList;
	}

	public List<Category> getTopCategoryList() {
		return categoriesMapper.findByAncestorIdAndLevel(NullValue.CATEGORY_ID.getValue(),
				CategoryInfo.TOP_CATEGORY.getLevel());
	}

	public int getTotalPagesByFilter(ItemFilter filter) {

		return itemsMapper.countTotalQuantity(filter) / pageInfo.PAGE_SIZE.getValue() + 1;
	}

	public List<Item> getCategoryListByFilter(ItemFilter filter) {

		List<Item> itemList = itemsMapper.findByFilter(filter);
		itemList = createCategoryList(itemList);
		return itemList;
	}

	public String pickUpBrandNameByBrandId(int BrandId) {
		return brandsMapper.pickUpNameById(BrandId);
	}

	private List<Item> createCategoryList(List<Item> itemList) {

		for (Item item : itemList) {
			Integer categoryId = item.getCategoryId();
			List<Category> categoryList = categoriesMapper.findByDescendantId(categoryId);
			item.setCategoryList(categoryList);
		}

		return itemList;
	}

}
