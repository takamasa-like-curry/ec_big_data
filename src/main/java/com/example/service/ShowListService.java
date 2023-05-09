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

/**
 * 商品一覧ページの業務処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
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
	 * トップカテゴリのカテゴリリストを取得.
	 *
	 * @return トップカテゴリのリスト.
	 */
	public List<Category> getTopCategoryList() {
		return categoriesMapper.findByAncestorIdAndLevel(NullValue.CATEGORY_ID.getValue(),
				CategoryInfo.TOP_CATEGORY.getLevel());
	}

	/**
	 * 自身を含む先祖カテゴリのリストを取得.
	 * 
	 * @param categoryId カテゴリID
	 * @return
	 */
	public List<Category> getAncestorCategoryListWithSelf(Integer categoryId) {
		List<Category> categoryList = categoriesMapper.findByDescendantId(categoryId);
		return categoryList;
	}

	/**
	 * 指定されたカテゴリを親に持つ、1つ下の階層のカテゴリリストを取得.
	 * 
	 * @param categoryId カテゴリID
	 * @param level
	 * @return
	 */
	public List<Category> getSubordinateCategoryList(Integer categoryId) {
		return categoriesMapper.findSubordinateCategoryList(categoryId);
	}

	/**
	 * 該当商品を表示するのに必要な総ページ数を取得.
	 * 
	 * @param filter 検索条件フィルター
	 * @return 該当商品を表示するのに必要な総ページ数
	 */
	public int getTotalPagesByFilter(ItemFilter filter) {
		return itemsMapper.countTotalQuantity(filter) / pageInfo.PAGE_SIZE.getValue() + 1;
	}

	/**
	 * 商品リストを取得.
	 * 
	 * @param filter 商品検索フィルター
	 * @return 該当商品のリスト
	 */
	public List<Item> getItemListByFilter(ItemFilter filter) {

		List<Item> itemList = itemsMapper.findByFilter(filter);
		itemList = createCategoryList(itemList);
		return itemList;
	}

	/**
	 * ブランドIDより、ブランド名を取得.
	 * 
	 * @param BrandId ブランドID
	 * @return 対応するブランド名
	 */
	public String getBrandNameByBrandId(int BrandId) {
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
