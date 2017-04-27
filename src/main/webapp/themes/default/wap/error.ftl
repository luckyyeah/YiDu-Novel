<#include "base.ftl"/>

<#macro titleContent>  
<title>${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>

<#macro top>
    <div class="top">
        <ul>
            <li><a href="${contextPath}/user/center"></a></li>
            <li><a></a></li>
            <li><a href="search.aspx"></a></li>
        </ul>
    </div>
    <div class="search">
        <ul>
            <li><input id="txtKeyword" type="text" data-text="请输入书名/作者名/关键字" value="请输入书名/作者名/关键字" /></li>
            <li><a id="lnkSearch" class="sbtn" href="javascript:;">搜索</a></li>
        </ul>
        <div style="display:none">
            <script type="text/javascript">
                $("#txtKeyword").click(function () {
                    if ($(this).val() == $(this).attr("data-text"))
                        $(this).val("");
                }).blur(function () {
                    if ($(this).val() == "")
                        $(this).val($(this).attr("data-text"));
                });
            </script>
        </div>
    </div>
    <div class="nav m10">
        <ul>
            <li class="curr"><a href="${contextPath}/">首页</a></li>
            <li><a href="${encodeURL("/top")}">排行</a></li>
            <li><a href="${encodeURL("/mobileCategory")}">分类</a></li>
            <li><a href="${encodeURL("/user/bookcase")}">书架</a></li>
        </ul>
    </div>
</#macro>


<#macro content>
<section class="section board-list board-list-collapse">
    <p class="b-all-switch normal">出现错误！</p>
    <div class="msgwin mgl_61" style="text-align:center;">
        <div class="blockcontent">
            <div style="padding:10px"><br />
        <@s.actionerror /><br />请&nbsp;<a href="javascript:history.back(1)">返 回</a>&nbsp;并修正或&nbsp;<a href="${contextPath}/">返回首页</a><br /><br /></div>
        </div>
    </div>
</section>
</#macro>