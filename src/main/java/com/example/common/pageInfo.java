package com.example.common;

public enum pageInfo {

	FIRST_PAGE(1), PAGE_SIZE(30),;

	private final int value;

	private pageInfo(Integer page) {
		this.value = page;
	}

	public Integer getValue() {
		return value;
	}

}
