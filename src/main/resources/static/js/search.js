$(function () {
    var ini_keyword = getParam("keyword");
    if (ini_keyword !== -1) {
        $(".input-container input").val(ini_keyword);
        var data = "keyword=" + ini_keyword + "&mall=" + "\"京东商城\",\"淘宝商城\",\"苏宁商城\"";
        dataHandler("searchItems?", data)
    }

    $(".button-container div").bind("click", function () {
        search();
    });

    $(".sort-btn").bind("click", function () {
        var sort_type = $(this).attr("id"),
            selected_btn_class = $(this).attr("class");
        if (selected_btn_class.indexOf("sort-btn-selected") !== -1) {
            return;
        }
        if (sort_type === 'normal-sort') {
            $(".sort-btn").removeClass("sort-btn-selected")
            $(this).addClass("sort-btn-selected");
        }
        if (sort_type === 'sell-sort-sort') {
            $(".sort-btn").removeClass("sort-btn-selected")
            $(this).addClass("sort-btn-selected");
        }
        if (sort_type === 'price-up-sort') {
            $(".sort-btn").removeClass("sort-btn-selected")
            $(this).addClass("sort-btn-selected");
        }
        if (sort_type === 'price-down-sort') {
            $(".sort-btn").removeClass("sort-btn-selected")
            $(this).addClass("sort-btn-selected");
        }
    });


    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            search();
        }
    });
})


function getParam(variable) {
    var query = window.location.search.substring(1);
    query = decodeURI(query);

    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return -1;
}

function dataHandler(fun, data) {
    $.ajax({
        type: "GET",
        url: "/shopping/" + fun + data,
        success: function (data) {

            // console.log(data.hits[0]._source);

            $(".search-result-list").html("");
            console.log(data);
            formatHandler(data.hits);

        }
    })
}

function formatHandler(result) {
    for (var i in result) {
        var item_name = result[i]._source.itemname,
            item_id = result[i]._source.itemid,
            item_url = result[i]._source.itemurl,
            item_img = result[i]._source.itemimg,
            item_price = result[i]._source.itemprice,
            item_reduction = result[i]._source.itemreduction,
            item_coupon = result[i]._source.itemcoupon,
            item_comment_num = result[i]._source.itemcommentnum,
            item_self_shop = result[i]._source.itemselfshop,
            item_shop = result[i]._source.itemshop,
            item_origin = result[i]._source.itemorigin;
        createItemElement(item_id, item_name, item_url, item_img, item_price, item_shop, item_comment_num, item_reduction, item_coupon, item_self_shop, item_origin)
    }
}

function createItemElement(id, name, url, img, price, shop, commentNum, reduction, coupon, selfShop, origin) {
    var logo = '#';
    if (origin === '京东商城') {
        logo = 'assets/京东.png';
    }
    if (origin === '苏宁商城') {
        logo = 'assets/苏宁.png';
    }
    if (origin === '淘宝商城') {
        logo = 'assets/淘宝.png';
    }

    var text = '<li sku=' + id + ' class="search-result-item"><div class="search-result-item-con"><div class="item-img">' +
        '<a href=' + url + ' target="_blank">' +
        '<img src=' + img + ' alt="图片"></a></div><div class="item-title"><div class="shop-logo">' +
        '<img src=' + logo + ' alt="logo"><span>' + shop + '(' + selfShop + ')' + '</span>' +
        '</div><div class="title-con"><a href=' + url + ' target="_blank">' + name +
        '</a></div></div><div class="item-price">￥<b>' + price + '</b></div><div class="item-comment">' + commentNum +
        '条评论 </div><div class="discount"><div class="item-reduction">' + reduction + '</div><div class="item-coupon">' + coupon + '</div></div></div></li>'

    $(".search-result-list").append(text);
}

function search() {
    var input_text = $(".input-container input").val();
    if (input_text.length > 0) {
        var data = "keyword=" + input_text,
            selected_sort = $(".sort-btn-selected").text();

        data += "&mall=";
        if ($('[name=jd]').is(':checked')) {
            data += "\"京东商城\"";
        }else {
            data += "\"\"";
        }
        if ($('[name=sn]').is(':checked')) {
            data += ",\"苏宁商城\"";
        }else {
            data += ",\"\"";
        }
        if ($('[name=tb]').is(':checked')) {
            data += ",\"淘宝商城\"";
        }else {
            data += ",\"\"";
        }
        if (data.indexOf("商城") === -1) {
            alert("请选中至少一个商城！");
            return;
        }

        if (selected_sort.indexOf("默认") !== -1) {
            dataHandler("searchItems?", data)
        } else if (selected_sort.indexOf("销量") !== -1) {
            dataHandler("searchItemsOrderBySells?", data)
        } else if (selected_sort.indexOf("⬆") !== -1) {
            dataHandler("searchItemsOrderByUpPrice?", data)
        } else if (selected_sort.indexOf("⬇") !== -1) {
            dataHandler("searchItemsOrderByDownPrice?", data)
        } else {
            dataHandler("searchItems?", data)
        }


    } else {
        alert("请输入搜索关键字")
    }
}