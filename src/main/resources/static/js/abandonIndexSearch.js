$(function () {
    $(".button-container div").bind("click", function () {
        var input_text = $(".input-container input").val();
        if (input_text.length > 0) {
            var data = "keyword=" + input_text;



        } else {
            alert("请输入搜索关键字")
        }
    })
})