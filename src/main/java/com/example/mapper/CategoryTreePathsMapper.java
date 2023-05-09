package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CategoryTreePathsMapper {

	void insert(@Param("ancestorId") int ancestorId, @Param("descendantId") int descendantId);

	/**
	 * カテゴリとその子孫カテゴリを削除(論理削除).
	 * 
	 * @param categoryId 削除するカテゴリID
	 */
	void logicalDeleteCategoryWithDescendants(int categoryId);
}
