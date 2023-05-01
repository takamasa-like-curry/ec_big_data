"use strict";

$(function () {
  $("#brand-name").on("keyup", function () {
    const brandName = $("#brand-name").val();
    $("#message-area").empty();
    $.ajax({
      url: "http://localhost:8080/big_data/api/input-brand-name-is-exists",
      type: "GET",
      dataType: "JSON",
      data: {
        brandName: brandName,
      },

      async: true,
    })
      .done(function (response) {
        if (response) {
          return;
        }
        const baseDiv = document.getElementById("message-area");
        let span = document.createElement("span");
        span.className = "text-danger";
        span.textContent =
          "このブランド名は使用できません。[既に使用済みのブランド名です]";
        baseDiv.appendChild(span);
      })
      .fail(function (XMLHttpRequest, textStatus, errorThrown) {
        // 検索失敗時には、その旨をダイアログ表示

        console.log("XMLHttpRequest : " + XMLHttpRequest.status);
        console.log("textStatus     : " + textStatus);
        console.log("errorThrown    : " + errorThrown.message);
      });
  });
});
