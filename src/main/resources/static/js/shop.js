//// JavaScript Document
//$(function() {

//    /* 产品分类-滑动菜单 */
//    $(".list_left").find("li").bind("click", function() {
//        var index = $(this).index();
//        $(".list_right").find("ul").eq(index).show().siblings().hide();
//        $(".list_right").css({ "left": "35%" });
//        $(".list_left").css({ "left": "-90px" });
//        $(this).find(".now").css({ "top": "50%" });
//        $(this).siblings().find(".now").css({ "top": "-50%" });
//    });
//    //
//    $(".closelist").click(function() {
//        $(".list_right").css({ "left": "120%" });
//        $(".list_left").css({ "left": "0px" });
//        $(".list_left").find(".now").css({ "top": "-50%" });
//    });
//    $(".list2_tt").click(function() {
//        $(this).parent(".list2_box").find("dd").toggleClass("open");
//    });
//    $(".closetoggle").click(function() {
//        $(".closeall").toggleClass("openall");
//        if ($(".closeall").hasClass("openall")) {
//            $(this).parents("ul").find("dd").addClass("open");
//            $(this).text("全部展开");
//        } else {
//            $(this).parents("ul").find("dd").removeClass("open");
//            $(this).text("全部收起");
//        }
//    });

//    /* 产品系列-排序 */
//    //$(".pro_sort a").click(function () {
//    //    var sort_name = $(this).attr("name");
//    //    var pro_box = $(".pro_box").toArray().sort(function (a, b) {
//    //        if (sort_name == "price") {
//    //            return parseInt($(a).attr(sort_name)) - parseInt($(b).attr(sort_name));
//    //        } else {
//    //            return parseInt($(b).attr(sort_name)) - parseInt($(a).attr(sort_name));
//    //        }
//    //    })
//    //    $(".srot_body").append($(pro_box));
//    //})


///* 产品详情轮播图 */
//    $(".pro_prev").click(function() {
//        var $dom = $(".pro_ban");
//        var $length = $(".pro_ban").find("li").length - 1;
//        goLeft($dom, $length);
//    });
//    $(".pro_next").click(function() {
//        var $dom = $(".pro_ban");
//        goRight($dom);
//    });
//    /* 产品数量 */
//    //$(".pro_red").click(function () {
//    //    var _this = $(this).parent("span").find(".pro_no");
//    //    var no = parseInt(_this.val()) - 1;
//    //    if (no > 0)
//    //        _this.val(no);
//    //})
//    //$(".pro_add").click(function () {
//    //    var _this = $(this).parent("span").find(".pro_no");
//    //    var no = parseInt(_this.val()) + 1;
//    //    _this.val(no);
//    //})
//    /* 产品颜色 */
//    $(".pro_color a").click(function() {
//        $(this).addClass("now").siblings().removeClass("now");
//    });
//    /* 浏览过的 */
//    //$("#bb").touchwipe({
//    //    wipeLeft: function () { },
//    //    wipeRight: function () { }
//    //})

//});

//function goLeft($dom, $length) {
//    $dom.find("li").eq($length).prependTo($dom);
//}
//function goRight($dom) {
//    $dom.find("li").eq(0).appendTo($dom);
//}

////document.removeEventListener("touchmove",aa(e),false)
