<#include "../base.ftl"/>

<#macro titleContent>
    <title>用户中心</title>
    <meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
    <meta name="description" content="${getText("label.system.siteDescription")}" />
    <script src="${contextPath}/themes/${themeName}/wap/js/Site.js"></script>
</#macro>

<#macro content>
    <div class="userinfo">
        <img class="r3" src="${contextPath}/themes/${themeName}/wap/images/pic.jpg" />
        <p>${user.loginid} <a class="logout r3" href="${encodeURL("/logout")}">退出</a></p>
        <p>余额：0</p>
        <p>月票：0张 、 评价票：0张</p>
    </div>

    <ul class="group-btns">
        <li><div class="lv2" ><p>会员等级</p></div></li>
        <li><a href="#" class="icon01"><p>我要充值</p></a></li>
        <li><a href="#" class="icon02"><p>充值记录</p></a></li>
        <li><a href="${encodeURL("/bookcase")}" class="icon09"><p>我的书架</p></a></li>
        <li><a href="${encodeURL("/user/message")}" class="icon06"><p>消息管理</p></a></li>
        <li><a href="javascript:Site.showUpdatePasswordbox(7737887);" class="icon08"><p>密码修改</p></a></li>
    </ul>

</#macro>