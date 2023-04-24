/**
 *
 */
"use strict";

$(function () {
  // 親カテゴリが変更されたら実行
  $("#parent-id").on("change", function () {
    const parentId = $("#parent-id").val();

    if (parentId == -1) {
      $("#child-id").hide();
      $("#grand-child-id").hide();
      $("#inpuut-parent-category").hide();
      $("#input-child-category").hide();
      return;
    } else if (parentId == -2) {
      $("#child-id").hide();
      $("#grand-child-id").hide();
      $("#inpuut-parent-category").show();
      $("#input-child-category").show();
      return;
    } else {
      $("#input-child-category").hide();
    }

    $.ajax({
      url: "http://localhost:8080/big_data/pick-up-category-list/child-category",
      type: "GET",
      dataType: "JSON",
      data: {
        parentId: parentId,
      },

      async: true,
    })
      .done(function (data) {
        //成功
        const childCategoryList = data.childCategoryList;

        if (childCategoryList.length == 0) {
          $("#child-id").hide();
          $("#grand-child-id").hide();
          return;
        }
        $("#inpuut-parent-category").hide();

        //元データを削除
        $("#child-categories").children().remove();
        //子カテゴリを挿入
        for (let i = 0; i < childCategoryList.length; i++) {
          $("#child-id").append(
            $("<option>")
              .val(childCategoryList[i].id)
              .text(childCategoryList[i].name)
          );
        }
        //子カテゴリを表示
        $("#child-id").show();
        //孫カテゴリを非表示
        $("#grand-child-id").hide();
      })
      .fail(function (XMLHttpRequest, textStatus, errorThrown) {
        // 検索失敗時には、その旨をダイアログ表示

        console.log("XMLHttpRequest : " + XMLHttpRequest.status);
        console.log("textStatus     : " + textStatus);
        console.log("errorThrown    : " + errorThrown.message);
      });
  });

  // 子カテゴリが変更されたら実行
  $("#child-id").on("change", function () {
    const childId = $("#child-id").val();

    if (childId == -1) {
      $("#grand-child-id").hide();
      $("#input-child-category").hide();
      return;
    }
    if (childId == -2) {
      $("#input-child-category").show();
      return;
    }

    $.ajax({
      url: "http://localhost:8080/big_data/pick-up-category-list/grand-child-category",
      type: "GET",
      dataType: "JSON",
      data: {
        childId: childId,
      },

      async: true,
    })
      .done(function (data) {
        $("#input-child-category").hide();
        //成功
        const grandChildCategoryList = data.grandChildCategoryList;
        //削除
        $("#grand-child-categories").children().remove();
        //孫カテゴリを挿入
        for (let i = 0; i < grandChildCategoryList.length; i++) {
          $("#grand-child-id").append(
            $("<option>")
              .val(grandChildCategoryList[i].id)
              .text(grandChildCategoryList[i].name)
          );
        }
        $("#grand-child-id").show();
      })
      .fail(function (XMLHttpRequest, textStatus, errorThrown) {
        // 検索失敗時には、その旨をダイアログ表示

        console.log("XMLHttpRequest : " + XMLHttpRequest.status);
        console.log("textStatus     : " + textStatus);
        console.log("errorThrown    : " + errorThrown.message);
      });
  });
});
