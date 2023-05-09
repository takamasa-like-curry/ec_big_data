package com.example.domain;

import java.util.List;

import lombok.Data;

/**
 * カテゴリーの情報を扱うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
public class Category {

	/** カテゴリID */
	private int id;
	/** カテゴリ名 */
	private String name;
	/** カテゴリ概要 */
	private String description;
	/** カテゴリ階層 */
	private int level;
	/** 先祖カテゴリマップ */
	private List<Category> ancestorCategoryList;

}
