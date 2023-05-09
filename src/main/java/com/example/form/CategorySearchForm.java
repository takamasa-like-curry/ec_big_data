package com.example.form;

import java.util.List;

import com.example.common.CategoryInfo;
import com.example.domain.Category;

import lombok.Data;

/**
 * ブラウザよりカテゴリの検索条件を取得するフォーム.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
public class CategorySearchForm {

	/** カテゴリ名 */
	private String name;
	/** カテゴリ階層 */
	private Integer level;
	/** 親カテゴリID */
	private Integer topCategoryId;
	/** 親カテゴリ名 */
	private String topCategoryName;
	/** 子カテゴリID */
	private Integer subCategory1Id;
	/** 子カテゴリ名 */
	private String subCategory1Name;
	/** 孫カテゴリID */
	private Integer subCategory2Id;

	public CategorySearchForm() {};

	private CategorySearchForm(List<Category> categoryList) {
		for (Category category : categoryList) {
			int level = category.getLevel();
			if (level == CategoryInfo.TOP_CATEGORY.getLevel()) {
				this.topCategoryId = category.getId();
				this.topCategoryName = category.getName();
			} else if (level == CategoryInfo.SUB_CATEGORY_1.getLevel()) {
				this.subCategory1Id = category.getId();
				this.subCategory1Name = category.getName();
			} else if (level == CategoryInfo.SUB_CATEGORY_2.getLevel()) {
				this.subCategory2Id = category.getId();
			}
		}

	}

	public static CategorySearchForm createFormByCategoryList(List<Category> categoryList) {
		return new CategorySearchForm(categoryList);
	}

}
