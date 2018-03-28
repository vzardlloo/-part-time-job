//域地址
var global_ajaxdomain = "http://m.lenovo.com.cn/srv/";

//搜索地址
var global_search = "http://m.lenovo.com.cn/search/";

var global_new_search = "http://m.lenovo.com.cn/nsearch/";

//网站地址
var global_ajaxwap = 'http://m.lenovo.com.cn/';

//WebChat地址
var global_webchat = 'http://m.lenovo.com.cn/chat.html';

var global_think_mobile = 'http://mobile.thinkworldshop.com.cn';


//监控站点ID，需要根据平台以及环境更新
var global_monitorid = 15;


//1 WAP ,2 微信,3 APP,4 PC
var global_platid = 1;
/*单点登录跳转网站地址*/
var sso_global_ajax = 'https://reg.lenovo.com.cn/';
/*单点登录ticket*/
var sso_ticket = 'b044d754-bda2-4f56-9fea-dcf3aecfe782';

var sso_JS = 'http://js.lefile.cn/s/pp/pp-0.2.0_v20150909.min.js';

//根据站点自动配置 global_platid  ----2015-08-11

if (window.sessionStorage && window.sessionStorage.getItem('platid')) {
    global_platid = window.sessionStorage.getItem('platid');
} else {
    if (window.location.href.indexOf('.com.cn/weixin/') != -1) {
        global_platid = 2;
    } else if (window.location.href.indexOf('.com.cn/android/') != -1) {
        global_platid = 3;
    }
}
if (window.sessionStorage) {
    window.sessionStorage.setItem('platid', global_platid);
}

//定义全局变量用户名
var global_loginusername = 'lenovocurrentusername';

//定义全局变量用户积分
var global_loginuserpoint = "lenovocurrentuserpoint";

//全局变量，首页是否显示优惠券
var global_iscouponshow = "lenovocouponisshow";

//lenovoID
var global_loginlenovoid = "lenovocurrentlenovoid";

var global_B2CUN = 'B2CUN';
var global_B2CKEY = 'B2CKEY';
var sid;
var un = getCookie(global_B2CUN);
var key = getCookie(global_B2CKEY);
//if (GetLoginUserName() == null) {
//  $.ajax({
//      url: global_ajaxdomain + 'getsessionid.do',
//      type: "get",
//      async: false,
//      success: function (data) {
//          sid = data;
//      }
//  });
//  if (sid != null && un != null && key != null) {
//      $.ajax({
//          url: global_ajaxdomain + 'autologin.do',
//          type: "get",
//          async: false,
//          dataType: "Json",
//          data: { "JSESSIONID": sid, "B2CUN": un, "B2CKEY": key },
//          success: function (data) {
//              if (data.rc == "0") {
//                  addCookie(global_loginusername, data.username, 0);
//                  addCookie(global_loginlenovoid, data.lenovoid, 0);
//                  addCookie(global_loginuserpoint, data.point, 0);
//                  addCookie(global_iscouponshow, data.isshow, 0);
//              }
//          }
//      });
//  }
//}
function GetLoginUserName() {
//  var globalname = getCookie(global_loginusername);
//  if ($.trim(globalname) == null || $.trim(globalname) == '' || $.trim(globalname) == 'null' || $.trim(globalname) == undefined) {
//      return null;
//  }
//  return globalname;

    var lId = GetLoginLenovoID();
    if (lId == null)
        return null;
    else
        return passport.cookie.loginName;


}

function loadJS(_dom) {
    var headTag = document.getElementsByTagName("head")[0];
    var scpript = document.createElement("script");
    scpript.src = _dom.src;
    scpript.type = _dom.type;
    headTag.appendChild(scpript);
    scpript.onload = scpript.onreadystatechange = function () {


        if (this.readyState = "complete") {
            if (_dom.fn) {
                _dom.fn();
            }
        }

    }
}

function GetLoginLenovoID() {
    var globallenovoid = getCookie(global_loginlenovoid);
    var globalname = getCookie(global_loginusername)
//      if (globallenovoid) {
//          return globallenovoid;
//      }
//     // alert('passport'+passport);
//      return null;
//  }
    // alert('here');
    if (passport.isLogin()) {
        //alert('login');
        if (passport.cookie.lenovoId != globallenovoid || !globalname) {
            deleteCookie(global_loginusername, getCookie(global_loginusername));
            deleteCookie(global_loginlenovoid, getCookie(global_loginlenovoid));
            deleteCookie('usertype', getCookie('usertype'));
//          addCookie(global_loginlenovoid, passport.cookie.lenovoId, 30);
//           addCookie(global_loginusername, passport.cookie.loginName);
            //getUserInfo();
        }
        //alert('passport.cookie.lenovoId'+passport.cookie.lenovoId);
        return passport.cookie.lenovoId;
    }

    return null;
}


function getUserInfo() {
    $.ajax({
        type: "get",
        url: global_ajaxdomain + 'mergeShoppingCart.do',
        data: {plat: global_platid},
        dataType: "json",
        async: false,
        success: function (data) {
            if (data.rc == "0") {
                // window.location.reload();
            }
            else {
                return false;
            }
        },
        error: function () {
            //showMessage(1000, data.msg);
        }
    });

    $.ajax({
        url: global_ajaxdomain + 'getusername.do',
        type: "get",
        async: false,
        cache: false,
        dataType: "Json",
        error: function (data) {
            console.log('登录失败，错误信息：' + data.statusText + "|" + data.responseText);
        },
        success: function (data) {

            if (data != null && data.username != undefined && data.rc == "0") {
                addCookie(global_loginusername, data.username, 30);
                addCookie(global_loginlenovoid, passport.cookie.lenovoId, 30);
                addCookie('usertype', data.usertype, 30);
            }
        }
    });
}

function GetLoginUserPoint() {
    var globalpoint = getCookie(global_loginuserpoint);
    if (globalpoint == null || globalpoint == "" || globalpoint == 'null') {
        globalpoint = 0;
    }
    return globalpoint;
}

function GetCouponIsShow() {
    var globalcoupon = getCookie(global_iscouponshow);
    if (globalcoupon == null || globalcoupon == "" || globalcoupon == 'null') {
        globalcoupon = '0';
    }
    return globalcoupon;
}

//登录方法，需要传入登录成功后跳转页面
function LoginLenovo(URL) {
//  //跳转到中转页面
//  URL = global_ajaxwap + 'loginhub.html?targetpath=' + URL;
//
//  //访问登录方法
//  var LoginURL = global_ajaxdomain + 'tologin.do?URL=' + URL + '&plat=' + global_platid;
//
//  //跳转页面
//  window.location.href = LoginURL;

    SSOLoginLenovo(URL);

}


function SSOLoginLenovo(URL) {
    //跳转单点登录中转页面
    var LoginURL = sso_global_ajax + 'auth/v1/login?ticket=' + sso_ticket + '&iswap=0&ru=' + URL;
    //跳转页面
    window.location.href = LoginURL;
}

//APP使用的自动登录方法
function LoginLenovoAuto() {
    $.ajax({
        url: global_ajaxdomain + 'autologin.do',
        type: "get",
        async: false,
        dataType: "Json",
        success: function (data) {
            //不需要重新登录，直接获取用户名
            if (data != null && data.rc == '0') {
                $.ajax({
                    url: global_ajaxdomain + 'getusername.do',
                    type: "get",
                    async: false,
                    dataType: "Json",
                    error: function (data) {
                        console.log('获取用户信息异常，错误信息：' + data.statusText + "，" + data.responseText);
                    },
                    success: function (data) {
                        if (data.rc != "0") {
                            deleteCookie(global_loginusername, getCookie(global_loginusername));
                            deleteCookie(global_loginuserpoint, getCookie(global_loginuserpoint));
                            deleteCookie(global_iscouponshow, getCookie(global_iscouponshow));
                        }
                        else {
                            addCookie(global_loginusername, data.username, 0);
                            addCookie(global_loginuserpoint, data.point, 0);
                            addCookie(global_iscouponshow, data.isshow, 0);
                        }
                    }
                })
            }
        }
    });
}

//验证是否已经登录，如果没有登录跳转到登录页面，如果已经登录跳转到指定的路径
function CheckIfLogin(path) {
    if (GetLoginUserName() == null) {
        $.ajax({
            url: global_ajaxdomain + 'getusername.do',
            type: "get",
            async: false,
            dataType: "Json",
            success: function (data) {
                if (data.rc != "0") {
                    //跳转登录
                    LoginLenovo(window.location.href);
                }
                else {
                    //登录成功之后跳转指定路径
                    addCookie(global_loginusername, data.username, 0);
                    addCookie(global_loginuserpoint, data.point, 0);
                    addCookie(global_iscouponshow, data.isshow, 0);
                    window.location.href = path;
                }
            }
        })
    }
    else {
        window.location.href = path;
    }
}

//重置用户名密码功能
function ResetLoginPassword() {
    var LoginURL = global_ajaxdomain + 'resetpassword.do?plat=' + global_platid + '&URL=' + global_ajaxwap + 'my/myindex.html';
    window.location.href = LoginURL;
}


//登出操作
function LogOutshop() {
    passport.init({
        ticket: sso_ticket,
        logout: function () {
            deleteCookie(global_loginusername, getCookie(global_loginusername));
            deleteCookie(global_loginuserpoint, getCookie(global_loginuserpoint));
            deleteCookie(global_iscouponshow, getCookie(global_iscouponshow));
            deleteCookie(global_B2CUN, getCookie(global_iscouponshow));
            deleteCookie(global_B2CKEY, getCookie(global_B2CKEY));
            window.location.href = global_ajaxwap;
            /*var LoginURL = global_ajaxdomain + 'logout.do?URL=' + global_ajaxwap + 'index.html';
            window.location.href = LoginURL;*/
        }
    });
    passport.doLogout();
}


//没有数据显示HTML
function NoDateDisplay(displaytext, imgurl) {
    if (!imgurl) {
        imgurl = "../img/smile.png";
    }
    var html = '';
    html += '<section style="padding: 0 7%;">';
    html += '    <p style="text-align: center; margin: 20px 0; "><img src="' + imgurl + '" /></p>';
    html += '    <p style="text-align: center;margin: 20px 0;">' + displaytext + '</p>';
    html += '</section>';
    return html;
}

//跳转到客服
function LoginWebChat(p, o) {
    var weburl = global_webchat + '?s=1';//客服专用平台编号：0:pc,1:wap，2：weixin，3：app
    var u = GetLoginUserName();
    if (u != null && u != '' && u != "null") {
        weburl += '&u=' + encodeURIComponent(u);
    }
    weburl += '&p=' + p + '&o=' + o;
    window.location.href = weburl;
}

function IsLgoin() {
    if (GetLoginUserName() != null) {
        return true;
    } else {
        return false;
    }
}

//添加Cookie
function addCookie(name, value, expiresdays) {
    var cookieString = name + "=" + escape(value) + ';path=/';

    if (expiresdays != 0 && expiresdays != '' && expiresdays != null) {
        var date = new Date();
        date.setTime(date.getTime() + expiresdays * 24 * 3600 * 1000);
        cookieString = cookieString + "; expires=" + date.toGMTString();
    }

    document.cookie = cookieString;
}

//获取Cookie
function getCookie(name) {
    var strCookie = document.cookie;
    var arrCookie = strCookie.split("; ");
    for (var i = 0; i < arrCookie.length; i++) {
        var arr = arrCookie[i].split("=");
        if (arr[0] == name) return unescape(arr[1]);
    }
    return null;
}

//删除Cookie
function deleteCookie(name, value) {
    var cookieString = name + "=" + escape(value);
    var cookiedate = new Date(0);
    cookieString = cookieString + "; expires=" + cookiedate.toGMTString() + ";path=/";
    document.cookie = cookieString;
}

/*客服在线状态*/
function serviceOnLine() {
    var time = new Date();
    var starttime = new Date(time.getFullYear(), time.getMonth(), time.getDate(), 9, 0, 0);
    var endtime = new Date(time.getFullYear(), time.getMonth(), time.getDate(), 22, 0, 0);

    if (time < starttime || time > endtime) {
        return false;
    }
    else {
        return true;
    }
}

//定义方法
(function ($) {
    $.getUrlParam = function (commonname) {
        var reg = new RegExp("(^|&)" + commonname + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURIComponent(r[2]);
        return null;
    }
})(jQuery);

//记载菜单方法
$(function () {
    /*URL登录判断*/
    IsMemberinfo();
    /* 搜索弹出 */
    //$(".search_bt").toggle(function () {
    //    $(".search_input input").val("");
    //    $(".search_input").css({ "width": "100%" })
    //}, function () {
    //    $(".search_input").css({ "width": "0" })
    //    var key = $(".search_input input").val();
    //    if (key != undefined && key != "") {
    //        key = encodeURIComponent(key, "utf-8")
    //        window.location.href = global_ajaxwap + "pm/productlist.html?s=" + key;
    //    }
    //});

    /* 头部菜单 */
    $("#list_bt img").click(function () {
        $(".menu").toggleClass("down");
    })

    /*新闻公告*/
    if ($("#J-site-info-ul").length == 1) {
        $.ajax({
            type: "get",
            url: global_ajaxwap + "/notice.html",
            cache: false,
            async: false,
            success: function (data) {
                if (data != "") {
                    $("#J-site-info-ul").html(data);
                    $("#site-info").show();
                    setNotice();
                }
                else {
                    $("#site-info").hide();
                }
            }
        });
    }

    if ($("#service").length == 1) {
        $("#service").attr("title", "工作时间：9:00-22:00");
    }

})

function setNotice() {
    var win_width = parseInt($(window).width());
    var info_length = $("#J-site-info-ul").find("li").length;
    var time_i = 0;
    var move_width = $("#J-site-info-ul").find("li").eq(0).find("a").width();

    $("#J-site-info-ul").find("li").eq(0).addClass("move");
    $("#J-site-info-ul").find("li").eq(0).css("left", -move_width);

    var time = setInterval(function () {
        textGoleft()
    }, 15100)

    function textGoleft() {
        $("#J-site-info-ul").find("li").eq(time_i).removeClass("move");
        $("#J-site-info-ul").find("li").eq(time_i).css("left", "100%");
        time_i++
        $("#J-site-info-ul").find("li").eq(time_i).addClass("move");
        var move = $("#J-site-info-ul").find(".move");
        move_width = move.find("a").width();
        move.css("left", -move_width);
        console.log(time_i + "==========" + info_length + "==========" + move.width())
        if (time_i >= info_length) {
            clearInterval(time)
            move_width = $("#J-site-info-ul").find("li").eq(0).find("a").width();
            $("#J-site-info-ul").find("li").eq(0).addClass("move");
            $("#J-site-info-ul").find("li").eq(0).css("left", -move_width);
            time_i = 0;
            time = setInterval(function () {
                textGoleft()
            }, 15100)
        }
    }
}


/*******************piwik_new_begin*********************/
//监控站点URL
var global_monitorurl = "//shop.click.lenovo.com.cn/collect";
var global_monitorjs = "//shop.click.lenovo.com.cn/analytics.js";

//监控基本站点ID
var global_monitorid = 19;

//根据站点自动配置 monitorid  ----2015-08-11
if (window.sessionStorage && window.sessionStorage.getItem('monitorid')) {
    global_monitorid = window.sessionStorage.getItem('monitorid');
} else {
    if (window.location.href.indexOf('.com.cn/weixin/') != -1) {
        global_monitorid = 21;
    } else if (window.location.href.indexOf('.com.cn/android/') != -1) {
        global_monitorid = 20;
    }
}
if (window.sessionStorage) {
    window.sessionStorage.setItem('monitorid', global_monitorid);
}

//监控摇钱树站点ID
var global_monitorid_tree = 51;

var _paq = _paq || [];

function piwik_loadScript(url, callback) {
    var script = document.createElement('script');
    script.type = 'text/javascript';
    if (callback)
        script.onload = script.onreadystatechange = function () {
            if (script.readyState && script.readyState != 'loaded' && script.readyState != 'complete')
                return;
            script.onreadystatechange = script.onload = null;
            if (window.location.href.indexOf("product") == -1)
                callback();
        };
    script.src = url;
    document.getElementsByTagName('head')[0].appendChild(script);
}

function piwik_pc_track_event(category, action, json_data) {
    try {
        var obj = Piwik.getAsyncTracker();
        obj.setCustomData(json_data);
        obj.setSiteId(global_monitorid);
        obj.trackEvent(category, action, 'data', 1);
    } catch (err) {
        if (console) {
            console.log(err);
        }
    }
}

function piwik_pc_track_event1(category, action, json_data) {
    if (f) {
        alert(f);
        try {
            var obj = Piwik.getAsyncTracker();
            obj.setCustomData(json_data);
            obj.setSiteId(global_monitorid);
            obj.trackEvent(category, action, 'data', 1);
        } catch (err) {
            if (console) {
                console.log(err);
            }
        }
        f = 0;
    } else {
        alert(f);
    }

}

function piwik_pc_track_event_tree(category, action, json_data) {
    try {
        var obj = Piwik.getAsyncTracker();
        obj.setCustomData(json_data);
        obj.setSiteId(global_monitorid_tree);
        obj.trackEvent(category, action, 'data', 1);
    } catch (err) {
        if (console) {
            console.log(err);
        }
    }
}

function piwik_pc_track(json_data) {
    try {
        var obj = Piwik.getAsyncTracker();
        obj.setSiteId(global_monitorid);
        obj.setTrackerUrl(global_monitorurl);
        obj.enableLinkTracking();
        obj.setCustomData(json_data);
        obj.trackPageView();
    } catch (err) {
        if (console) {
            console.log(err);
        }
    }
}


function piwik_pc_track_tree(json_data) {
    try {
        var obj = Piwik.getAsyncTracker();
        obj.setSiteId(global_monitorid_tree);
        obj.setTrackerUrl(global_monitorurl);
        obj.enableLinkTracking();
        obj.setCustomData(json_data);
        obj.trackPageView();
    } catch (err) {
        if (console) {
            console.log(err);
        }
    }
}

var lenovoid;
if (getCookie(global_loginlenovoid)) {
    lenovoid = getCookie(global_loginlenovoid);
}
if (typeof lenovoid != "undefined") {
    var piwik_data = {"lenovoid": lenovoid}
}

function piwik_callback() {
    var json_data = {};
    if (typeof piwik_data != "undefined") {
        json_data = piwik_data;
    }
    if (typeof page_id != "undefined") {
        json_data["page_id"] = page_id;
    }
    json_data["page_type"] = "wap";
    piwik_pc_track(json_data);
}

function piwik_callback_tree() {
    var json_data = {};
    if (typeof piwik_data != "undefined") {
        json_data = piwik_data;
    }
    if (typeof page_id != "undefined") {
        json_data["page_id"] = page_id;
    }
    json_data["page_type"] = "wap";
    piwik_pc_track_tree(json_data);
}


piwik_loadScript(global_monitorjs, piwik_callback);

//piwik_loadScript(global_monitorjs, piwik_callback_tree);

/******************piwik_new_end*******************/

function addomniture() {
    var oHead = document.getElementsByTagName('HEAD').item(0);
    var oScript = document.createElement("script");
    oScript.type = "text/javascript";
    oScript.src = "/js/s_code.js";
    oHead.appendChild(oScript);
}

//图片延迟加载  可视区加载
function imgLazyload() {
    var _window = $(window),
        _img = $("img"),
        imgTop,                                     //图片距离顶部高度
        scTop = _window.scrollTop(),                //滚动条高度
        wH;                                         //窗口高度

    wH = _window.height();  //获得可视浏览器高度

    //页面初始化插入可视区图片
    _img.each(function () {
        if ($(this).data("url")) {
            var _parent = $(this).parent(),
                _imgW = $(this).css("width") == 0 ? _parent.width() : $(this).css("width"),
                _imgH = $(this).css("height") == 0 ? _parent.height() : $(this).css("height"),
                _dataSrc = $(this).data("url"),
                _src = $(this).attr("src");

            var httpLength = global_ajaxwap.length;
            var _px = _imgW.substring(0, (_imgW.length - 2)) * 1.5;

            var _resultPath = _dataSrc.substring(0, httpLength + 1) + "/" + Math.floor(_px) + _dataSrc.substring(httpLength + 1, _dataSrc.length);
            var _resultimgH = _imgH.substring(0, _imgH.length - 2);

            imgTop = $(this).offset().top;

            if (imgTop - _resultimgH - wH < 0 && $(this).attr('src') != $(this).data('url')) {
                $(this).attr({src: _resultPath, "data-rc": "success"});
            }
        }
    });

    //页面滚动后插入正确的图片
    _window.scroll(function () {
        scTop = _window.scrollTop();    //获取滚动条到顶部的垂直高度

        _img.each(function () {
            if ($(this).data("url")) {
                var _parent = $(this).parent(),
                    _imgW = $(this).css("width") == 0 ? _parent.width() : $(this).css("width"),
                    _imgH = $(this).css("height") == 0 ? _parent.height() : $(this).css("height"),
                    _dataSrc = $(this).data("url"),
                    _src = $(this).attr("src");

                var httpLength = global_ajaxwap.length;
                var _px = _imgW.substring(0, (_imgW.length - 2)) * 1.5;

                var _resultPath = _dataSrc.substring(0, httpLength + 1) + "/" + Math.floor(_px) + _dataSrc.substring(httpLength + 1, _dataSrc.length);
                var _resultimgH = _imgH.substring(0, _imgH.length - 2);

                imgTop = $(this).offset().top;

                if (imgTop - _resultimgH - wH < scTop && imgTop + _resultimgH - wH > 0 && $(this).attr('src') != $(this).data('url') && $(this).attr("data-rc") != "success") {
                    $(this).attr({
                        src: _resultPath
                    });
                }
            }
        });
    });
};


/********判断url跳转是否登录***********/
function IsMemberinfo() {
    if (GetLoginUserName() != null) return false;
    var _lenovoid = $.getUrlParam("lenovoid");
    var _stamp = $.getUrlParam("stamp");//时间戳
    var _sign = $.getUrlParam("sign");//签名字符串
    var sq = $.getUrlParam("from");
    if (sq == "sq") {
        $.ajax({
            type: "get",
            url: global_ajaxdomain + 'getc2cmemberinfo.do',
            data: {lenovoid: _lenovoid, stamp: _stamp, sign: _sign},
            cache: false,
            async: false,
            dataType: "Json",
            success: function (data) {
                if (data.rc == 0) {
                    addCookie(global_loginusername, data.username, 0);
                    addCookie(global_loginlenovoid, data.lenovoid, 0);
                    addCookie('usertype', data.usertype, 0);
                }
            }
        });
    }
}

/*------------------20150821监控代码------------------*/
var _trackDataType = 'web'; //标记数据来源，参数是web和wap，可以为空，默认是web
var _Schannel_website_id = 'lenovoshop_pc';//分站编号，不存在可不写此变量或者留空
var _Schannel_webshop_id = '';//商铺编号，不存在可不写此变量或者留空
var _trackData = _trackData || [];//必须为全局变量，假如之前并没有声明，请加此行代码；

var laurl = "//m1.lefile.cn/la/la.min.js?v20160602";

function loadScript(url, callback) {
    var script = document.createElement("script")
    script.type = "text/javascript";
    if (script.readyState) { //IE 
        script.onreadystatechange = function () {
            if (script.readyState == "loaded" ||
                script.readyState == "complete") {
                script.onreadystatechange = null;
                callback();
            }
        };
    } else { //Others: Firefox, Safari, Chrome, and Opera 
        script.onload = function () {
            callback();
        };
    }
    script.src = url;
    document.body.appendChild(script);
}

/** omiture 监控检测自动添加****/
$(function () {
    loadScript(laurl, function () {
        if (typeof (la_callback) != 'undefined' && la_callback) {
            la_callback();
        }
    });
    if (!window.s) {
        $.ajax({
            url: '/js/s_code.js',
            dataType: 'script',
            cache: true,
            success: function () {
                monitor_setdefaultprop();
                var s_code = s.t();
                if (s_code) document.write(s_code);
            }
        });
    }
});
