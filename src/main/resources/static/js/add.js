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

  $("#select-brand").on("click", function () {
    let newWindow = window.open(
      "http://localhost:8080/big_data/",
      null,
      "popup"
    );
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

  $("#open").on("click", function (event) {
    event.preventDefault(); //デフォルトのイベント(aタグの画面遷移)を阻止
    //ボタンをクリックしたら
    $(".modal").addClass("open"); // modalクラスにopenクラス付与
    $(".overlay").addClass("open"); // modalクラスにopenクラス付与
  });

  $("#close").on("click", function () {
    //ボタンをクリックしたら
    const input = $('input[name="select-brand"]:checked');
    if (input.attr("id") != undefined) {
      const brandId = input.attr("id");
      const brandName = input.siblings("span").text();
      $("#brand-id").val(brandId);
      $("#brand-name").text(brandName);
      $("#hidden-brand-name").val(brandName);
      if ($("#delete-brand-btn").val() == undefined) {
        const deleteBtnArea = document.getElementById("delete-brand-btn-area");
        const btn = document.createElement("button");
        btn.type = "button";
        btn.className = "btn btn-default";
        btn.id = "delete-brand-btn";
        btn.textContent = "ブランドを削除";
        deleteBtnArea.appendChild(btn);
      }
    }
    $(".modal").removeClass("open"); // modalクラスにopenクラス削除
    $(".overlay").removeClass("open"); // modalクラスにopenクラス削除
    resetBrand();
    $("#input-brand-name").val(null);
  });

  $("#serch-brand-btn").on("click", function () {
    resetBrand();

    const brandName = $("#input-brand-name").val();
    if (brandName == "") {
      const div = document.getElementById("select-brand-message");
      const span = document.createElement("span");
      span.textContent = "検索ワードを入力してください";
      div.appendChild(span);
      return;
    }

    $.ajax({
      url: "http://localhost:8080/big_data/api/pick-up-brand-list",
      type: "GET",
      dataType: "JSON",
      data: {
        brandName: brandName,
      },

      async: true,
    })
      .done(function (data) {
        const brandList = data.brandList;
        //検索結果メッセージを表示
        const selectBrandMessage = document.getElementById(
          "select-brand-message"
        );
        let message = document.createElement("span");
        if (brandList.length == 0) {
          message.textContent = "検索結果 : 0件";
          selectBrandMessage.appendChild(message);
          return;
        } else {
          message.textContent = "検索結果 : " + brandList.length + "件";
          selectBrandMessage.appendChild(message);
        }
        //検索結果を表示
        const selectBrandArea = document.getElementById("select-brand-area");
        const defaultDiv = document.createElement("div");
        defaultDiv.className = "brand-list not-select";
        const defaultLabel = document.createElement("label");
        defaultLabel.htmlFor = "";
        const defaultInput = document.createElement("input");
        defaultInput.type = "radio";
        defaultInput.className = "input-radio-brand";
        defaultInput.name = "select-brand";
        defaultInput.id = "";
        //
        const defaultSpan = document.createElement("span");
        defaultSpan.className = "brand-name";
        defaultSpan.textContent = "選択しない";
        //
        defaultLabel.appendChild(defaultInput);
        defaultLabel.appendChild(defaultSpan);
        defaultDiv.appendChild(defaultLabel);
        selectBrandArea.appendChild(defaultDiv);
        ////////////

        for (let i = 0; i < brandList.length; i++) {
          const div = document.createElement("div");
          div.className = "brand-list";
          const label = document.createElement("label");
          label.htmlFor = brandList[i].id;
          const input = document.createElement("input");
          input.type = "radio";
          input.className = "input-radio-brand";
          input.name = "select-brand";
          input.id = brandList[i].id;
          //
          const span = document.createElement("span");
          span.className = "brand-name";
          span.textContent = brandList[i].name;
          //
          label.appendChild(input);
          label.appendChild(span);
          div.appendChild(label);
          selectBrandArea.appendChild(div);
        }
      })
      .fail(function (XMLHttpRequest, textStatus, errorThrown) {
        // 検索失敗時には、その旨をダイアログ表示

        console.log("XMLHttpRequest : " + XMLHttpRequest.status);
        console.log("textStatus     : " + textStatus);
        console.log("errorThrown    : " + errorThrown.message);
      });
  });

  $(document).on("click", ".brand-list", function () {
    $(".brand-list").removeClass("checked");
    $(this).addClass("checked");
    //
    const bassDiv = document.getElementById("selected-brand-name");
    const selectedBrandId = $(this).find("input").attr("id");
    if (selectedBrandId == false) {
      bassDiv.textContent = "未選択";
      return;
    }
    const selectedBrandName = $(this).find(".brand-name").text();
    bassDiv.textContent = selectedBrandName + "  を選択中";
  });

  $(document).on("click", "#delete-brand-btn", function () {
    $("#brand-name").text("未選択(必須項目ではありません)");
    $("#hidden-brand-name").val(null);
    $("#brand-id").val(null);
    $("#delete-brand-btn-area").empty();
  });

  function resetBrand() {
    $("#select-brand-message").empty();
    $("#select-brand-area").empty();
    $("#selected-brand-name").empty();
  }
});
