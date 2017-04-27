<#include "base.ftl"/>

<#macro titleContent>  
<title>用户登录|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>

<#macro top>
    <div class="top2">
        <ul>
            <li><a href="javascript:Util.goBack();"></a></li>
            <li>用户登录</li>
            <li><a href="${contextPath}/"></a></li>
            <li><a href="${contextPath}/user/center"></a></li>
            <li><a href="search.aspx"></a></li>
        </ul>
    </div>
</#macro>

<#macro content>
    <div class="m10">
        <ul class="group2">
            <li><input id="txtLoginName" class="input r3" type="text" data-text="账号" value="账号" /></li>
            <li><input id="txtPassword" class="input r3" type="text" data-text="密码" value="密码" /></li>
            <li style="text-align:right;"><a id="lnkFindPassword" href="javascript:;">忘记密码?</a></li>
            <li><a id="lnkLogin" class="button blue r3" href="javascript:;" onclick="login();">登录</a></li>
            <li><a id="lnkRegister" class="button white r3" href="javascript:;">注册账户</a></li>
        </ul>
    </div>

    <script type="text/javascript">
        // 来路URL
        var _referer = "${encodeURL("/user/center")}";
        // 登录
        var login = function () {

            var userName = $("#txtLoginName").val();
            var userPass = $("#txtPassword").val();

            if (userName == $("#txtLoginName").attr("data-text")
                || userName == "") {
                Util.Alert("请输入账户");
                return;
            }
            if (userPass == $("#txtPassword").attr("data-text")
                || userPass == "") {
                Util.Alert("请输入密码");
                return;
            }

            $("#lnkLogin").html("正在登录......");

            $.get("${encodeURL("/ajaxService")}", $.param({action:"login", loginid: userName, password: userPass })
                , function (res) {
                    $("#lnkLogin").html("登录");
                    if (res.code == 0) {
                        Util.CookieWrite("USER_FLAG", res.result, 999);
                        if (_referer != "") {
                            location.href = _referer;
                        }
                        return;
                    }
                    Util.Alert(res.err);
                });
        }
        // 初始化
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

            $("#lnkRegister").click(function () {
                location.href = "${contextPath}/register?referer=" + _referer;
            });

        });

    </script>
</#macro>