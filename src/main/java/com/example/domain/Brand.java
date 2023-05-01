package com.example.domain;

import com.example.common.NullValue;

import lombok.Data;

@Data
public class Brand {

	private int id;
	private String name;
	
	private Brand() {
		this.id = NullValue.BRAND_ID.getValue();
	}

	private Brand(String name) {
		this.id = NullValue.BRAND_ID.getValue();
		this.name = name;
	}
	private Brand(Integer id) {
		setId(id);
	}
	
	private Brand(Integer id,String name) {
		this.id = id;
		this.name = name;
	}
	
	

	public static Brand createWithName(String name) {
		return new Brand(name);
	}
	public static Brand createWithId(Integer id) {
		return new Brand(id);
	}
	public static Brand create() {
		return new Brand();
	}
	
	public static Brand createWithIdAndName(Integer id,String name) {
		return new Brand(id, name);
	}
//////////////////セッター・セッター///////////////////////////////////
	
	public void setId(Integer id) {
		if (id == null) {
			this.id = NullValue.BRAND_ID.getValue();
		} else {
			this.id = id;
		}
	}
}
