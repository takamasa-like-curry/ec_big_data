package com.example.form;

import java.util.List;

import com.example.common.CategoryInfo;
import com.example.domain.Category;

import lombok.Data;

@Data
public class ItemSearchForm {

	/** 商品名 */
	private String name;
	/** 親カテゴリID */
	private Integer topCategoryId;
	/** 子カテゴリID */
	private Integer subCategory1Id;
	/** 孫カテゴリID */
	private Integer subCategory2Id;
	/** ブランド名 */
	private String brandName;
	/** ブランドID */
	private Integer brandId;

	public ItemSearchForm() {

	};

	private ItemSearchForm(Integer brandId) {
		this.brandId = brandId;
	}

	private ItemSearchForm(Integer brandId, String brandName) {
		this.brandId = brandId;
		this.brandName = brandName;
	}

	private ItemSearchForm(List<Category> categoryList) {
		for (Category category : categoryList) {
			Integer level = category.getLevel();
			if (level == CategoryInfo.TOP_CATEGORY.getLevel()) {
				this.topCategoryId = category.getId();
			} else if (level == CategoryInfo.SUB_CATEGORY_1.getLevel()) {
				this.subCategory1Id = category.getId();
			} else if (level == CategoryInfo.SUB_CATEGORY_2.getLevel()) {
				this.subCategory2Id = category.getId();
			} else {
				// エラー処理
			}

		}
	}

	public static ItemSearchForm createFormByBrandId(Integer brandId) {
		return new ItemSearchForm(brandId);

	}

	public static ItemSearchForm createFormByBrandIdAndBrandName(Integer brandId, String name) {
		return new ItemSearchForm(brandId, name);

	}

	public static ItemSearchForm createFormByCategoryList(List<Category> categoryList) {
		return new ItemSearchForm(categoryList);

	}

	/////////////////// セッター////////////////////////////
	public void setName(String name) {
		if ("".equals(name)) {
			this.name = null;
		} else {
			this.name = name;
		}
	}

	public void setBrandName(String brandName) {
		if ("".equals(brandName)) {
			this.brandName = null;
		} else {
			this.brandName = brandName;
		}
	}

}
