package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.Category;
import com.example.filter.FilterOfCategory;
import com.example.form.CategoryEditForm;

@Mapper
public interface CategoriesMapper {

	/**
	 * フィルタ検索.
	 * 
	 * @param filter 検索条件フィルタ
	 * @return 該当カテゴリリスト
	 */
	List<Category> findByFilter(@Param("filter") FilterOfCategory filter);

	List<Category> findByAncestorIdAndLevel(@Param("ancestorId") int ancestorId, @Param("level") int level);

	List<Category> findByDescendantId(int descendantId);

	/**
	 * 主キー(カテゴリID)検索.
	 * 
	 * @param id カテゴリID
	 * @return 該当カテゴリ情報
	 */
	Category load(int id);

	/**
	 * カテゴリ名の重複チェックを行う.
	 * 
	 * @param name  重複をチェックするカテゴリ名(ブラウザにて入力された値)
	 * @param level チェックを行う階層
	 * @return 重複があれば[false]、重複がなければ[true]
	 * 
	 */
	Boolean checkCategoryNameDuplication(@Param("name") String name, @Param("ancestorId") Integer ancestorId,
			@Param("level") int level);

	boolean existsDescendantCategory(int id);

//	List<Category>

	/**
	 * 該当カテゴリを親に持つ一つ下の階層のカテゴリリストを取得.
	 * 
	 * @param id カテゴリID
	 * @return 該当カテゴリリスト
	 */
	List<Category> findSubordinateCategoryList(int id);

	int pickUpLevelById(int id);

	int pickUpLatestCategoryId();

	void insert(@Param("category") Category category);

	/**
	 * 該当カテゴリ件数を取得.
	 * 
	 * @param filter 検索フィルタ
	 * @return 該当カテゴリの件数
	 */
	int countQuantiryByFilter(@Param("filter") FilterOfCategory filter);

	int findMaxLevel();

	/**
	 * 該当カテゴリとそのカテゴリの子孫カテゴリのリストを取得.
	 * 
	 * @param categoryId カテゴリID
	 * @return 該当カテゴリとそのカテゴリの子孫カテゴリのリスト
	 */
	List<Category> findCategoryWithDescendants(int categoryId);

	/**
	 * カテゴリの論理削除.
	 * 
	 * @param categoryId カテゴリID
	 */
	void logicalDelete(int categoryId);

	/**
	 * カテゴリ情報の更新.
	 * 
	 * @param category 更新情報の入ったカテゴリ
	 */
	void update(@Param("form") CategoryEditForm form);

}
