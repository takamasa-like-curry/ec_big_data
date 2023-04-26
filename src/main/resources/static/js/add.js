/**
 *
 */
"use strict";

$(function () {
  // 親カテゴリが変更されたら実行
  $("#parent-category-id").on("change", function () {
    const parentId = $("#parent-category-id").val();

    $("#select-child-category-id").remove();
    $("#select-grand-child-category-id").remove();
    removeMessage();

    if (parentId == false) {
      return;
    }

    $.ajax({
      url: "http://localhost:8080/big_data/api/pick-up-subordinate-category-list",
      type: "GET",
      dataType: "JSON",
      data: {
        categoryId: parentId,
      },

      async: true,
    })
      .done(function (data) {
        //成功
        const childCategoryList = data.categoryList;

        //子カテゴリを挿入
        const baseDiv = document.getElementById("child-category-area");
        let div1 = document.createElement("div");
        div1.className = "form-group";
        div1.id = "select-child-category-id";
        //label
        let label = document.createElement("label");
        label.htmlFor = "child-category-id";
        label.className = "col-sm-2 control-label";
        //
        let div2 = document.createElement("div");
        div2.className = "col-sm-8";
        //
        let select = document.createElement("select");
        select.className = "form-control";
        select.id = "child-category-id";
        select.name = "childCategoryId";
        //
        let option1 = document.createElement("option");
        option1.value = "";
        option1.textContent = "-- childCategory --";
        select.appendChild(option1);
        for (let i = 0; i < childCategoryList.length; i++) {
          let option = document.createElement("option");
          option.value = childCategoryList[i].id;
          option.textContent = childCategoryList[i].name;
          select.appendChild(option);
        }
        //
        let div3 = document.createElement("div");
        div3.id = "child-category-message-area";
        //
        div2.appendChild(select);
        div2.appendChild(div3);
        div1.append(label);
        div1.appendChild(div2);
        baseDiv.appendChild(div1);
      })
      .fail(function (XMLHttpRequest, textStatus, errorThrown) {
        // 検索失敗時には、その旨をダイアログ表示

        console.log("XMLHttpRequest : " + XMLHttpRequest.status);
        console.log("textStatus     : " + textStatus);
        console.log("errorThrown    : " + errorThrown.message);
      });
  });

  // 子カテゴリが変更されたら実行
  $(document).on("change", "#child-category-id", function () {
    const childId = $("#child-category-id").val();

    $("#select-grand-child-category-id").remove();
    removeMessage();

    if (childId == false) {
      return;
    }

    $.ajax({
      url: "http://localhost:8080/big_data/api/pick-up-subordinate-category-list",
      type: "GET",
      dataType: "JSON",
      data: {
        categoryId: childId,
      },

      async: true,
    })
      .done(function (data) {
        //成功
        const grandChildCategoryList = data.categoryList;

        //子カテゴリを挿入
        const baseDiv = document.getElementById("grand-child-category-area");
        let div1 = document.createElement("div");
        div1.className = "form-group";
        div1.id = "select-grand-child-category-id";
        //label
        let label = document.createElement("label");
        label.htmlFor = "grand-child-category-id";
        label.className = "col-sm-2 control-label";
        //
        let div2 = document.createElement("div");
        div2.className = "col-sm-8";
        //
        let select = document.createElement("select");
        select.className = "form-control";
        select.id = "grand-child-category-id";
        select.name = "grandChildCategoryId";
        //
        let option1 = document.createElement("option");
        option1.value = "";
        option1.textContent = "-- grandChildCategory --";
        select.appendChild(option1);
        for (let i = 0; i < grandChildCategoryList.length; i++) {
          let option = document.createElement("option");
          option.value = grandChildCategoryList[i].id;
          option.textContent = grandChildCategoryList[i].name;
          select.appendChild(option);
        }
        //
        let div3 = document.createElement("div");
        div3.id = "grand-child-category-message-area";
        //
        div2.appendChild(select);
        div2.appendChild(div3);
        div1.append(label);
        div1.appendChild(div2);
        baseDiv.appendChild(div1);
      })
      .fail(function (XMLHttpRequest, textStatus, errorThrown) {
        // 検索失敗時には、その旨をダイアログ表示

        console.log("XMLHttpRequest : " + XMLHttpRequest.status);
        console.log("textStatus     : " + textStatus);
        console.log("errorThrown    : " + errorThrown.message);
      });
  });

  function removeMessage() {
    if (document.getElementById("parent-category-message") != null) {
      document.getElementById("parent-category-message").remove();
    }
    if (document.getElementById("child-category-message") != null) {
      document.getElementById("child-category-message").remove();
    }
    if (document.getElementById("grand-child-category-message") != null) {
      document.getElementById("grand-child-category-message").remove();
    }
  }
});
