package com.example.domain;

import com.example.common.NullValue;
import com.example.common.pageInfo;
import com.example.form.CategorySearchForm;

import lombok.Data;

/**
 * カテゴリ検索フィルタ
 * 
 * @author sugaharatakamasa
 *
 */
@Data
public class FilterOfCategory {

	/** カテゴリ名 */
	private String name;
	/** カテゴリ名 */
	private int level;
	/** 先祖カテゴリID */
	private int ancestorCategoryId;
	/** 表示するページ */
	private int page;
	/** オフセット値 */
	private int offset;

	private FilterOfCategory(String name, int level, int ancestorId) {
		if(name != null) {
			this.name = "%" + name + "%";
		}
		this.level = level;
		this.ancestorCategoryId = ancestorId;
	}

	/**
	 * カテゴリ検索フォームからフィルターを作成.
	 * 
	 * @param form ブラウザより検索条件を受け取るフォーム
	 * @return フォームの検索条件に対応するフィルター
	 */
	public static FilterOfCategory createFilterByForm(CategorySearchForm form) {
		int ancestorCategoryId;
		if (form.getSubCategory2Id() != null) {
			ancestorCategoryId = form.getSubCategory2Id();
		} else if (form.getSubCategory1Id() != null) {
			ancestorCategoryId = form.getSubCategory1Id();
		} else if (form.getTopCategoryId() != null) {
			ancestorCategoryId = form.getTopCategoryId();
		} else {
			ancestorCategoryId = NullValue.CATEGORY_ID.getValue();
		}
		int level;
		if (form.getLevel() == null) {
			level = NullValue.LEVEL.getValue();
		} else {
			level = form.getLevel();
		}

		return new FilterOfCategory(form.getName(), level, ancestorCategoryId);

	}

	///////////// setter & getter ///////////////////////
	public void setPage(Integer page) {
		if (page == null) {
			page = pageInfo.FIRST_PAGE.getValue();
		}
		this.page = page;
		this.offset = --page * pageInfo.PAGE_SIZE.getValue();
	}

}
