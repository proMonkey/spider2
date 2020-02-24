$(function () {
    $(".button-container div").bind("click", function () {
        search()
    });
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            search();
        }
    });

})

function search() {
    var input_text = $(".input-container input").val();
    if (input_text.length > 0) {
        window.location.href = "search?keyword=" + input_text + "&mall=" + "\"京东商城\",\"淘宝商城\",\"苏宁商城\"";

    } else {
        alert("请输入搜索关键字");
    }
}