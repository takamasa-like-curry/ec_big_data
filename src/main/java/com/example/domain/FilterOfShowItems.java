package com.example.domain;

import com.example.common.PasingConstants;

import lombok.Data;

/**
 * 該当ページに表示する商品の検索フィルター.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
public final class FilterOfShowItems {

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

	public FilterOfShowItems() {};

	private FilterOfShowItems(String name, int categoryId, String brand, int page) {
		this.name = name;
		this.categoryId = categoryId;
		this.brand = Brand.createWithName(brand);
		this.page = page;
	}

	public static FilterOfShowItems createFilter(String name, int categoryId, String brand, int page) {
		return new FilterOfShowItems(name, categoryId, brand, page);
	}

	public void setPage(int page) {
		this.page = page;
		this.offset = PasingConstants.SIZE.getPage() * --page;
	}
	public void setName(String name) {
		if(name != null) {
			this.name = "%" + name + "%";
		}
	}
	public void setBrand(Brand brand) {
		String brandName = brand.getName();
		if(brandName != null) {
			brand.setName("%" + brandName + "%");
		}
		this.brand = brand;
	}
}
