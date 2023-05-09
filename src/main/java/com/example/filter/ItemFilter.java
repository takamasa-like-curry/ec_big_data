package com.example.filter;

import com.example.common.NullValue;
import com.example.common.pageInfo;
import com.example.domain.Brand;
import com.example.form.ItemSearchForm;

import lombok.Data;

/**
 * 該当ページに表示する商品の検索フィルター.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
public final class ItemFilter {

	/** 検索する商品名(曖昧も可) */
	private String name;
	/** 検索するカテゴリID */
	private int categoryId;
	/** 検索するブランド名(曖昧も可) */
	private Brand brand;
	/** 表示するページ */
	private int page;
	/** オフセット値 */
	private int offset;

	public ItemFilter() {
	};

	private ItemFilter(String name, int categoryId, String brand, int page) {
		this.name = name;
		this.categoryId = categoryId;
		this.brand = Brand.createWithName(brand);
		this.page = page;
	}

	private ItemFilter(ItemSearchForm form) {
		this.name = form.getName();
		this.brand = Brand.createWithIdAndName(form.getBrandId(), form.getBrandName());
		if (form.getSubCategory2Id() != null) {
			this.categoryId = form.getSubCategory2Id();
		} else if (form.getSubCategory1Id() != null) {
			this.categoryId = form.getSubCategory1Id();
		} else if (form.getTopCategoryId() != null) {
			this.categoryId = form.getTopCategoryId();
		} else {
			this.categoryId = NullValue.CATEGORY_ID.getValue();
		}
	}

	public static ItemFilter createFilter(String name, int categoryId, String brand, int page) {
		return new ItemFilter(name, categoryId, brand, page);
	}

	public static ItemFilter createByForm(ItemSearchForm form) {
		return new ItemFilter(form);
	}

	////////////////////////// setter・getter ////////////////////////////////
	public void setPage(int page) {
		this.page = page;
		this.offset = pageInfo.PAGE_SIZE.getValue() * --page;
	}

	public void setName(String name) {
		if (name != null) {
			this.name = "%" + name + "%";
		}
	}

	public void setBrand(Brand brand) {
		String brandName = brand.getName();
		if (brandName != null) {
			brand.setName("%" + brandName + "%");
		}
		this.brand = brand;
	}
}
