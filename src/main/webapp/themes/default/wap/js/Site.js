/// <reference path="jQuery.Min.js" />
/// <reference path="JX.js" />

(function () {

    window.Site = {
        /*接口地址*/
        Host: "/ajaxService",
        /*公共操作数*/
        options: {
            codeFlag: 0,
            delay: 60,
            timer: null,
            checkTimer: null
        },
        /*设置窗体标题*/
        _setBoxTitle: function (title) {
            $("#_winbox .win-header span").html(title);
        },
        /*重置窗体Top位置*/
        _resetBoxTop: function (top) {
            $("#_winbox").css({ top: top + "px" });
        },
        /*重置位置*/
        _resetPos: function () {
            var box = $("#_winbox");
            var top = ($(window).height() - box.height()) * 0.4;
            if (top < 0) top = 0;
            box.css({ top: top + "px" });
        },
        /*显示错误信息*/
        showMsg: function (id, message, flag) {
            var color = flag ? "#00F" : "#F00";
            $(id).html('<span style="color:' + color + '">' + message + '</span>');
            setTimeout(function () { $(id).html(""); }, 1500);
        },
        /*显示浮层*/
        showWinbox: function (title, html, cover , goHis) {

            var htm = [];
            htm.push('<div id="_winbox" class="win-box">');
            htm.push('    <div class="win-padding">');
            htm.push('        <div class="win-main">');
            htm.push('            <div class="win-header">');
            htm.push('                <span>' + title + '</span>');
            htm.push('                <button class="win-box-close">×</button>');
            htm.push('            </div>');
            htm.push('              <div class="win-line"></div>');
            htm.push('             <div id="_winBody">' + html + '</div>');
            htm.push('        </div>');
            htm.push('    </div>');
            htm.push('</div>');
            $("#_winbox").remove();
            $(htm.join("")).appendTo("body");

            Site._resetPos();
            $("button[class=win-box-close]").click(function () {
                Site.closeWinbox(goHis);
            });

            if (cover) {
                $("#_winCover").remove();
                $('<div id="_winCover" class="win-cover"></div>').appendTo("body");
                $("#_winCover").height($(document).height());
            }
        },
        /*关闭浮层*/
        closeWinbox: function (goHis) {
            $("#_winbox").remove();
            $("#_winCover").remove();
            if (Site.options.timer) clearTimeout(Site.options.timer);
            if (Site.options.checkTimer) clearInterval(Site.options.checkTimer);
            if (typeof goHis != "undefined" && goHis == 1)
                history.go(-1);
        },
        /*登陆框*/
        showLoginbox: function (callback) {

            var htm = [
                '<ul class="group2 m10">',
                '    <li><input id="_txtLoginName" class="input r3" type="text" data-text="账号" value="账号" /></li>',
                '    <li><input id="_txtPassword" class="input r3" type="text" data-text="密码" value="密码" /></li>',
                '    <li style="text-align:right;"><a id="_lnkFindPassword" href="javascript:;">忘记密码?</a></li>',
                '    <li><a id="_lnkLogin" class="button blue r3" href="javascript:;" onclick="login();">登录</a></li>',
                '    <li><a id="_lnkRegister" class="button color2 r3" href="javascript:;">注册账户</a></li>',
                '</ul>'
            ];
            
            var goHis = location.href.toLocaleLowerCase().indexOf("content.aspx") >= 0 ? 1 : 0;
            this.showWinbox('用户登陆', htm.join(""), true, goHis);

            $(".input").click(
                function () {
                    if ($(this).val() == $(this).attr("data-text")) {
                        $(this).val("");
                        $(this).css({ color: "#555" });
                    }
                }).blur(
                    function () {
                        if ($(this).val() == "") {
                            $(this).val($(this).attr("data-text"));
                            $(this).css({ color: "#999" });
                        }
                    });
            $("#_lnkLogin").click(
                function () {
                    var userName = $("#_txtLoginName").val();
                    var userPass = $("#_txtPassword").val();
                    if (userName == $("#_txtLoginName").attr("data-text")
                        || userName == "") {
                        Util.Alert("请输入账户");
                        return;
                    }
                    if (userPass == $("#_txtPassword").attr("data-text")
                        || userPass == "") {
                        Util.Alert("请输入密码");
                        return;
                    }
                    Site._setBoxTitle("登录中....");
                    $.get(Site.Host, $.param({ action: "login", loginid: userName, password: userPass })
                        , function (res) {

                            Site._setBoxTitle("用户登录");

                            if (res.code == 0) {
                                Util.CookieWrite("USER_FLAG", res.result, 999);
                                Util.Alert("登录成功了");
                                setTimeout(function () {
                                    if (typeof callback == "function") callback(res.uid);
                                    Site.closeWinbox();
                                }, 1500);
                                return;
                            }
                            Util.Alert(res.err);
                        });
                });

            $("#_lnkRegister").click(
                function () {
                    Site.showRegisterbox(callback);
                });
            $("#_lnkFindPassword").click(
                function () {
                    Site.showFindPasswordbox(callback);
                });

        },
        /*注册框*/
        showRegisterbox: function (callback) {

            var htm = [
             '<div id="_registerBox">',
             '   <div id="_panels" style="border-top:2px solid #efefef;">',
             '       <div class="box" style="padding:10px;">',
             '           <ul class="group2 ww">',
             '               <li><input id="_txtNickname" class="input r3" type="text" data-text="请输入昵称" value="请输入昵称" /></li>',
             '               <li><input id="_txtPass2" class="input r3" type="text" data-text="请输入密码" value="请输入密码" /></li>',
             '               <li><a id="_lnkRegister2" href="javascript:register2();" class="button blue r3">确定注册</a></li>',
             '               <li><a  data-action="gologin" href="javascript:;" class="button color1 r3">已有账号前往登陆</a></li>',
             '           </ul>',
             '       </div>',
             '   </div>',
             '</div>'];

            this.showWinbox("账户注册", htm.join(""), true);

            $("#_tabRegister").find("li").click(function () {
                var sel = $(this);
                var index = parseInt(sel.attr("data-index"));
                var v = 33.3;
                sel.parent().siblings("ul").find(".animbg").animate({ marginLeft: (index * v) + "%" }, 200
                    , function () {
                        sel.addClass("curr")
                            .siblings().removeClass("curr");
                    });
                $("#_panels > div").eq(index)
                    .show()
                    .siblings("div").hide();
            });
            // 输入框单击事件
            $("#_registerBox .input").click(
                function () {
                    if ($(this).val() == $(this).attr("data-text")) {
                        $(this).val("");
                    }
                }).blur(
                    function () {
                        if ($(this).val() == "") {
                            $(this).val($(this).attr("data-text"));
                        }
                    });
            
            // 昵称注册
            $("#_lnkRegister2").click(function () {

                var nickname = $("#_txtNickname").val();
                var userPass = $("#_txtPass2").val();
                if (nickname == "" || nickname == $("#_txtNickname").attr("data-text")) {
                    Util.Alert("昵称不能为空");
                    return;
                }
                if (nickname.length < 5 || nickname.length > 32 ) {
                    Util.Alert("昵称需要6到32个字符");
                    return;
                }
                if (userPass.length < 5 || userPass.length > 32 ) {
                    Util.Alert("密码需要6到32个字符");
                    return;
                }
                Site._setBoxTitle("正在注册....");
                $.get("/ajaxService", $.param({
                    action: "register", loginid: nickname, password: userPass})
                    , function (res) {
                        $("#lnkRegister2").html("确定注册");
                        Util.Alert(res.result);
                        if (res.code == 0) {
                        	setTimeout(function () {
                                if (typeof callback == "function") callback(res.uid);
                                Site.closeWinbox();
                            }, 1500);
                        }
                    });
            });
            // 已有账号登陆
            $("a[data-action=gologin]").click(function () {
                    Site.showLoginbox(callback);
                });

        },
        
        /*修改密码*/
        showUpdatePasswordbox: function (userid, callback) {

            var htm = [];

            htm.push('<div>');
            htm.push('   <ul class="group2">');
            htm.push('       <li><input id="_txtOldPassword" class="input" type="text" data-text="输入旧密码" value="输入旧密码" /></li>');
            htm.push('       <li><input id="_txtNewPassword" class="input" type="text" data-text="新密码" value="新密码" /></li>');
            htm.push('       <li><a href="javascript:;" id="_lnkUpdate" class="button blue r3">确定修改</a></li>');
            htm.push('   </ul>');
            htm.push('</div>');

            this.showWinbox('密码修改', htm.join(""), true);

            $(".input").click(
                function () {
                    if ($(this).val() == $(this).attr("data-text")) {
                        $(this).val("");
                        $(this).css({ color: "#555" });
                    }
                }).blur(
                    function () {
                        if ($(this).val() == "") {
                            $(this).val($(this).attr("data-text"));
                            $(this).css({ color: "#999" });
                        }
                    });

            $("#_lnkUpdate").click(
                function () {

                    var oldPassword = $("#_txtOldPassword").val();
                    var newPassword = $("#_txtNewPassword").val();

                    if (newPassword.length < 6) {
                        Util.Alert("密码至少6个字符");
                        return;
                    }

                    Site._setBoxTitle("发送请求中....");

                    $.get(Site.Host, $.param({ action: "user_updatepassword", userid: userid, oldpassword: oldPassword, newpassword: newPassword })
                        , function (data) {
                            Site._setBoxTitle("密码修改");
                            var res = eval("(" + data + ")");
                            if (res.code == 0) {
                                Util.CookieWrite("USER_FLAG", res.result, 999);
                                Util.Alert("成功修改了密码");
                                if (typeof callback == "function") callback();
                                setTimeout(function () {
                                    Site.closeWinbox();
                                }, 1500);
                                return;
                            }
                            var msgs = ["", "用户不存在", "旧密码不正确"];
                            Util.Alert(msgs[res.code]);
                        });

                });
        },
        
        /*显示确认框*/
        showConfirmbox: function (msg, callback) {
            var htm = [
                '<div id="_confirmBox" class="box" style="padding:10px 0;">',
                '    <div class="box" style="padding-bottom:10px; color:#555; line-height:24px; text-indent:28px; ">',
                msg,
                '    </div>',
                '    <div class="line"></div>',
                '    <ul class="group g2 m10">',
                '        <li><a href="javascript:;" data-value="1" class="button blue r3">确定</a></li>',
                '        <li><a href="javascript:;" data-value="0" class="button color2 r3">取消</a></li>',
                '    </ul>',
                '</div>'
            ];
            this.showWinbox('提示', htm.join(""), true);
            $("#_confirmBox .button").click(function () {
                var value = parseInt($(this).attr("data-value"));
                if (typeof callback == "function")
                    callback(value);
                Site.closeWinbox();
            });
        },
        /*回顶部*/
        initGotoTop: function () {
            $("#_goTop").remove();
            $('<a id="_goTop" href="javascript:;" class="gotop"></a>').appendTo("body");
            $("#_goTop").click(function () {
                $("body").animate({ scrollTop: 0 }, 200);
            });
            setInterval(function () {
                var top = $("body").scrollTop();
                top > 10 ? $("#_goTop").show() : $("#_goTop").hide();
            }, 200);
        }
    };

})();

window.onload = function () {
    Site.initGotoTop();
};

//定义方法
var in_array = function(arr) {
	for ( var i = 0, k = arr.length; i < k; i++) {
		if (this == arr[i].articleno) {
			return i;
		}
	}
	return -1;
};
Number.prototype.in_array = in_array;
String.prototype.in_array = in_array;