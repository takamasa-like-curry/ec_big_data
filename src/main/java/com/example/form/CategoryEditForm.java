package com.example.form;

import com.example.common.NullValue;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * カテゴリ編集用の情報を取得するフォーム.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
public class CategoryEditForm {

	private int id;
	@NotBlank(message = "入力必須項目です")
	private String name;
	@NotBlank(message = "入力必須項目です")
	private String description;

	public CategoryEditForm() {
		this.id = NullValue.CATEGORY_ID.getValue();
	}

}
