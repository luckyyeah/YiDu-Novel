<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0"/>
<noscript>您已禁用JavaScript,网页不能正常显示.请在当前浏览器的设置或系统设置中,开启使用JavaScript</noscript>
<#assign categorymap = categoryData?eval>
<#if titleContent?exists>  
      <@titleContent/>
</#if>
<meta name="author" content="www.51yd.org"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/themes/${themeName}/wap/css/mobilecss.css" rel="stylesheet" />
<script src="${contextPath}/themes/${themeName}/wap/js/lib/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/themes/${themeName}/wap/js/JX.js"></script>
<script src="${contextPath}/themes/${themeName}/wap/js/Site.js"></script>
</head>
<body>

<#if top?exists>  
    <@top/>
</#if>

<#if customizetop?exists>  
    <@customizetop/>
</#if>

<#if (pageType == 1 || pageType == 7 || pageType == 2 || pageType == 26 || pageType == 5 ) > 
    <#if search?exists>  
        <@search/>
    </#if>
</#if>

<#if (pageType == 1 || pageType == 7 || pageType == 2 || pageType == 26 ) > 
    <#if menu?exists>  
        <@menu/>
    </#if>
</#if>

<#if content?exists>  
    <@content/>
</#if>

<#if footer?exists>  
    <@footer/>
</#if>
  
<#if customizefooter?exists>  
    <@customizefooter/>
</#if>

<#macro search>
    <#if pageType != 5>
    <form action="${contextPath}/search" method="get" id="seach">
    </#if>
    <div class="search">
        <#if pageType != 5>
        <ul>
            <li><input id="txtKeyword" name="key" type="text" data-text="请输入书名/作者名" value="请输入书名/作者名" /></li>
            <li><a id="lnkSearch" class="sbtn" href="javascript:$('#seach').submit();">搜索</a></li>
        </ul>
        <#else>
        <ul>
            <#if key??>
                <li><input id="txtKeyword" type="text" data-text="请输入书名/作者名" value="${key}" /></li>
                
            <#else>
                <li><input id="txtKeyword" type="text" data-text="请输入书名/作者名" value="请输入书名/作者名" /></li>
            </#if>
            <li><a id="lnkSearch" class="sbtn" href="javascript:;">搜索</a></li>
        </ul>
        </#if>
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
    <#if pageType != 5>
    </form>
    </#if>
</#macro>


<#macro top>
<#if pageType == 1 >
    <div class="top">
        <ul>
            <li><a href="${encodeURL("/user/center")}"></a></li>
            <li><a></a></li>
            <li><a href="${encodeURL("/search")}"></a></li>
        </ul>
    </div>
<#elseif pageType != 4>
    <div class="top2">
        <ul>
            <li><a href="javascript:Util.goBack();"></a></li>
            <#if pageType == 7> 
            <li>小说排行榜</li>
            <#elseif pageType == 2>
            <li><#if category?? && category!=0 >${categorymap[category?c]}<#elseif author?? >${author}的小说</title><#else>完本小说</#if></li>
            <#elseif pageType == 26>
            <li>我的书架</li>
            <#elseif pageType == 3>
            <li>作品详情</li>
            <#elseif pageType == 5>
            <li>小说搜索</li>
            <#elseif pageType == 11>
            <li>用户登录</li>
            <#elseif pageType == 12>
            <li>用户注册</li>
            </#if>
            <li><a href="${contextPath}/"></a></li>
            <li><a href="${encodeURL("/user/center")}"></a></li>
            <li><a href="${encodeURL("/search")}"></a></li>
        </ul>
    </div>
</#if>
</#macro>

<#macro menu>
   <div class="nav m10">
        <ul>
            <li <#if pageType == 1 > class="curr"</#if>><a href="${contextPath}/">首页</a></li>
            <li <#if pageType == 7 > class="curr"</#if>><a href="${encodeURL("/top")}">排行</a></li>
            <li <#if pageType == 2 > class="curr"</#if>><a href="${encodeURL("/articleList?category=1")}">分类</a></li>
            <li <#if pageType == 26 > class="curr"</#if>><a href="${encodeURL("/bookcase")}">书架</a></li>
        </ul>
    </div>
</#macro>

<#macro footer>
<div class="footer">
    <div class="version">
        ${getText("label.system.beianNo")} ${getText("label.system.analyticscode")}
    </div>
    <div class="copyright">${getText("label.system.copyright")}</div>
</div>
</#macro>

	<script>
	(function(){
	    var bp = document.createElement('script');
	    var curProtocol = window.location.protocol.split(':')[0];
	    if (curProtocol === 'https') {
	        bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';        
	    }
	    else {
	        bp.src = 'http://push.zhanzhang.baidu.com/push.js';
	    }
	    var s = document.getElementsByTagName("script")[0];
	    s.parentNode.insertBefore(bp, s);
	})();
	</script>
</body>  
</html>