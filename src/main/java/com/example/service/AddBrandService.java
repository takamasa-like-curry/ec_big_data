package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.BrandsMapper;

/**
 * ブランド追加画面の業務処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Service
@Transactional
public class AddBrandService {

	@Autowired
	private BrandsMapper brandsMapper;

	/**
	 * ブランド名の重複を確認
	 * 
	 * @param brand ブラウザから受け取ったブランド名
	 * @return 重複があればtrue,重複がなければfalse
	 */
	public boolean existsInDb(String brandName) {
		return brandsMapper.isExists(brandName);
	}

	/**
	 * 新規ブランドの追加
	 * 
	 * @param brandName
	 */
	public void insert(String brandName) {
		brandsMapper.insert(brandName);
	}
}
