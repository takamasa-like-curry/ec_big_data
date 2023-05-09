"use strict";

$(function () {
  $("#delete").on("click", function (e) {
    if (!confirm("削除しますか?")) {
      e.preventDefault();
    }
  });
});
