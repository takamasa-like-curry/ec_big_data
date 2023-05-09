package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.CategoryInfo;
import com.example.common.NullValue;
import com.example.domain.Brand;
import com.example.domain.Category;
import com.example.domain.Item;
import com.example.form.ItemForm;
import com.example.mapper.CategoriesMapper;
import com.example.mapper.ItemsMapper;

@Service
public class AddNewItemService {

	@Autowired
	private ItemsMapper itemsMapper;
	@Autowired
	private CategoriesMapper categoriesMapper;

	public void insertItem(ItemForm form) {
		Item item = createItem(form);
		itemsMapper.deleteIndexForItemId();
		itemsMapper.insert(item);
		itemsMapper.createIndexForItemId();
	}

	private Item createItem(ItemForm form) {
		Item item = new Item();
		item.setName(form.getInputName());
		item.setCondition(form.getCondition());
		if (form.getBrandId() == null) {
			item.setBrand(Brand.create());
		} else {
			item.setBrand(Brand.createWithIdAndName(form.getBrandId(), form.getBrandName()));
		}
		item.setPrice(Double.parseDouble(form.getPrice()));
		item.setShipping(NullValue.SHIPPING.getValue());
		item.setDescription(form.getDescription());
		item.setCategoryId(form.getGrandChildCategoryId());
		Integer itemId = itemsMapper.pickUpLatestItemId();
		item.setItemId(++itemId);

		return item;
	}

	public boolean existsDescendantCategory(int categoryId) {
		return categoriesMapper.existsDescendantCategory(categoryId);
	}

	public List<Category> pickUpParentCategoryList() {
		List<Category> parentCategoryList = categoriesMapper.findByAncestorIdAndLevel(NullValue.CATEGORY_ID.getValue(),
				CategoryInfo.TOP_CATEGORY.getLevel());
		parentCategoryList = removeIncompleteCategory(parentCategoryList);
		return parentCategoryList;
	}

	public List<Category> pickUpSubordinateCategoryList(int CategoryId) {
		List<Category> subordinateCategoryList = categoriesMapper.findSubordinateCategoryList(CategoryId);
		subordinateCategoryList = removeIncompleteCategory(subordinateCategoryList);
		return subordinateCategoryList;
	}

	private List<Category> removeIncompleteCategory(List<Category> categoryList) {
		for (int i = 0; i < categoryList.size(); i++) {
			if (!categoriesMapper.existsDescendantCategory(categoryList.get(i).getId())) {
				categoryList.remove(i);
				i--;
			}
		}
		return categoryList;
	}

}
