package com.example.common;

public enum CategoryInfo {

	TOP_CATEGORY(0),SUB_CATEGORY_1(1),SUB_CATEGORY_2(2),SUB_CATEGORY_3(3),SUB_CATEGORY_4(4), ;

	private final int level;

	private CategoryInfo(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}
}
