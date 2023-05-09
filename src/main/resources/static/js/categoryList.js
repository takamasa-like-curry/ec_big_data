"use strict";

$(function () {
  $(document).on("change", ".select-category", function () {
    const parentCategoryId = $(this).val();

    console.log(parentCategoryId);

    let parentCategoryName = "";
    if (parentCategoryId != false) {
      parentCategoryName = $(this).find("option:selected").text();
    }
    let addCategoryLevel;
    if ($(this).attr("id") == "top-category-id") {
      removeSubCategory1List();
      removeSubCategory2List();
      $("#top-category-name").val(parentCategoryName);
      addCategoryLevel = 1;
    }
    if ($(this).attr("id") == "sub-category-1-id") {
      removeSubCategory2List();
      $("#sub-category-1-name").val(parentCategoryName);
      addCategoryLevel = 2;
    }
    if (parentCategoryId == false) {
      return;
    }
    if ($(this).attr("id") == "sub-category-2-id") {
      return;
    }

    $.ajax({
      url: "http://localhost:8080/big_data/api/get-subordinate-category-list",
      type: "GET",
      dataType: "JSON",
      data: {
        categoryId: parentCategoryId,
      },
      async: true,
    })
      .done(function (data) {
        const childCategoryList = data.categoryList;
        if (childCategoryList.length == 0) {
          return;
        }
        const selectChildCategoryArea = $(
          "#select-sub-category-" + addCategoryLevel + "-area"
        );
        const select = $("<select>")
          .addClass("form-control select-category")
          .attr("id", "sub-category-" + addCategoryLevel + "-id")
          .attr("name", "subCategory" + addCategoryLevel + "Id");

        selectChildCategoryArea.append(select);
        const defaultOption = $("<option>")
          .val("")
          .text("- " + parentCategoryName + "のサブカテゴリ -");
        select.append(defaultOption);

        childCategoryList.forEach((category) => {
          const option = $("<option>").val(category.id).text(category.name);
          select.append(option);
        });
      })
      .fail(function (XMLHttpRequest, textStatus, errorThrown) {
        // 検索失敗時には、その旨をダイアログ表示

        console.log("XMLHttpRequest : " + XMLHttpRequest.status);
        console.log("textStatus     : " + textStatus);
        console.log("errorThrown    : " + errorThrown.message);
      });
  });
});

function removeSubCategory1List() {
  $("#select-sub-category-1-area").empty();
}

function removeSubCategory2List() {
  $("#select-sub-category-2-area").empty();
}
