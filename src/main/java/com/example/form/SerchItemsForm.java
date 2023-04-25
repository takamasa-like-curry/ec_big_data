package com.example.form;

import java.util.List;

import com.example.common.CategoryLevel;
import com.example.domain.Category;

import lombok.Data;

@Data
public class SerchItemsForm {

	/** 商品名 */
	private String name;
	/** 親カテゴリID */
	private Integer parentCategoryId;
	/** 子カテゴリID */
	private Integer childCategoryId;
	/** 孫カテゴリID */
	private Integer grandChildCategoryId;
	/** ブランド名 */
	private String brandName;
	/** ブランドID */
	private Integer brandId;

	public SerchItemsForm() {

	};

	private SerchItemsForm(Integer brandId) {
		this.brandId = brandId;
	}

	private SerchItemsForm(Integer brandId, String brandName) {
		this.brandId = brandId;
		this.brandName = brandName;
	}

	private SerchItemsForm(List<Category> categoryList) {
		for (Category category : categoryList) {
			Integer level = category.getLevel();
			if (level == CategoryLevel.PARENT.getLevel()) {
				this.parentCategoryId = category.getId();
			} else if (level == CategoryLevel.CHILD.getLevel()) {
				this.childCategoryId = category.getId();
			} else if (level == CategoryLevel.GRAND_CHILD.getLevel()) {
				this.grandChildCategoryId = category.getId();
			} else {
				// エラー処理
			}

		}
	}

	public static SerchItemsForm createFormByBrandId(Integer brandId) {
		return new SerchItemsForm(brandId);

	}

	public static SerchItemsForm createFormByBrandIdAndBrandName(Integer brandId, String name) {
		return new SerchItemsForm(brandId, name);

	}

	public static SerchItemsForm createFormByCategoryList(List<Category> categoryList) {
		return new SerchItemsForm(categoryList);

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
