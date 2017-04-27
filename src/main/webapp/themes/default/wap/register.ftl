<#include "base.ftl"/>

<#macro titleContent>  
<title>用户注册|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>


<#macro content>

    <div class="m10">

        <div id="panels" class="m2">
            <div class="box" style="padding:10px;">
                <ul class="group2 ww">
                    <li><input id="txtNickname" class="input r3" type="text" data-text="请输入昵称" value="请输入昵称" /></li>
                    <li><input id="txtPass2" class="input r3" type="text" data-text="请输入密码" value="请输入密码" /></li>
                    <li><a id="lnkRegister2" href="javascript:register2();" class="button blue r3">确定注册</a></li>
                    <li><a href="javascript:goLogin();" class="button color1 r3">已有账号前往登陆</a></li>
                </ul>
            </div>
        </div>
    </div>
</#macro>
<#macro customizefooter>

    <script type="text/javascript">

        var _referer = "${encodeURL("/user/center")}";
        var _codeFlag = 0;
        var _delay = 60;
        var _channel = 1;
        var _timer;
        // 昵称注册
        var register2 = function () {
            var nickname = $("#txtNickname").val();
            var userPass = $("#txtPass2").val();
            if (nickname == "" || nickname == $("#txtNickname").attr("data-text")) {
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
            
            $("#lnkRegister2").html("正在提交....");
            $.get("${encodeURL("/ajaxService")}", $.param({
                action: "register", loginid: nickname, password: userPass})
                , function (res) {
                    $("#lnkRegister2").html("确定注册");
                    Util.Alert(res.result);
                    if (res.code == 0) {
                        setTimeout(function () {
                            if (_referer != "") {
                                location.href = _referer;
                            }
                        }, 1500);
                    }
                });
        }
        //前往登录
        var goLogin = function () {
            location.href = "${encodeURL("/login")}?referer=" + _referer;
        }
        //初始化
        $(function () {
            $(".input").click(
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
        });

    </script>
</#macro>