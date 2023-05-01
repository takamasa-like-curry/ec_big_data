package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Brand;

/**
 * brandテーブルの操作を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Mapper
public interface BrandsMapper {

	String pickUpNameById(int id);

	List<Brand> findByName(String name);

	/**
	 * 新規ブランドを追加.
	 * 
	 * @param brandName
	 */
	void insert(String brandName);

	/**
	 * ブランド名がDB内に存在するかを確認.
	 * 
	 * @param brandName 検索するブランド名
	 * @return 存在していればtrue,していなければfalse
	 */
	boolean isExists(String brandName);
}