<#include "base.ftl"/>
<#macro headerContent>
  <div class="shadow" <#if pageType?? && pageType==4> style="height:1px;" </#if>></div>
  <header id="global-head" <#if pageType?? && pageType==4> style="height:45px;" </#if>>
    <div>
      <nav class="site-navigation cf" style="width: 320px;">
      <a class="home" href="${contextPath}/">首页</a>
      <a href="${encodeURL("/siteMap")}">网站地图</a>
      <a href="${encodeURL("/articleList?fullflag=true")}">完本小说</a>
      <a href="${encodeURL("/saveShortcut")}" rel="nofollow">创建桌面快捷</a>
      </nav>
      <div class="hd-follow" style="width:630px;text-align:right;" >
        <div class="myhide"><a href="#" class="hides">浏览记录</a>
          <div class="hideInfo">
            <ul id="readhistory">
            </ul>
            <p>*提示：浏览记录仅放置最近浏览的10本书籍</p>
            <span>浏览记录是空的</span>
          </div>
        </div>
          <span id="checklogin">
            <#if enableQQLogin><a href="/gotoQQLogin" rel="nofollow"><img src="/themes/default/pc/images/qq_login.gif" class="vm" alt="QQ登录"></a></#if>
            <a href="${contextPath}/login" style="color:#F0F0F0" rel="nofollow">访客登录</a>&nbsp;&nbsp;
            <a href="${contextPath}/register" style="color:#F0F0F0" rel="nofollow">免费注册</a>&nbsp;&nbsp;
          </span>
        </div>
    </div>
    <!--TODO haserror check-->
    <#if hasError || ( pageType?? && pageType!=4) >
    <p class="site-logo">
        <a href="${contextPath}/" title="${getText("label.system.title")}">
        <img src="${contextPath}/themes/${themeName}/pc/images/logo.png" alt="${getText("label.system.title")} - logo"></a>
    </p>
    <div class="site-search">
    <form action="${contextPath}/search" method="get" >
       <input name="key" type="text" id="key" onFocus="this.classname='over';if (value =='这是一个神奇的搜索，请输入小说名或作者名'){value =''}" onBlur="this.classname='input'"  value="<#if key??>${key?html}<#else>这是一个神奇的搜索，请输入小说名或作者名</#if>" />
       <input type="button" id="searchbuttom" value="" style="background:url('${contextPath}/themes/${themeName}/pc/images/search.jpg');border:0px solid;cursor:pointer;" />
    </form>
    </div>
    </#if>
  </header>
</#macro>  

<#macro naviContent>
  <div id="channel-header" class="clearfix">
    <div class="channel-header-wrapper">
      <nav class="channel-nav">
        <ul class="channel-nav-list">
            <li <#if pageType ?? && pageType==1>class="current"</#if> ><a href="${contextPath}/" title="${getText("label.system.name")}">${getText("label.system.name")}</a></li>
            <#list categorymap?keys as categorykey>
            <li <#if category??><#if categorykey==category?c>class="current"</#if></#if>><a href="${encodeURL("/articleList?category=${categorykey}")}">${categorymap[categorykey]}</a></li>
            </#list>
            <li <#if pageType ?? && pageType==7>class="current"</#if>><a href="${encodeURL("/top?sortColumn=lastupdate")}">排行榜</a></li>
        </ul>
      </nav>
    </div>
  </div>
</#macro>

<#macro footer>
<div class="clear"></div>
<footer id="global-foot">
    <p>
    本小说站所有小说、评论均为网友更新！仅代表发布者个人行为，与本小说站(${getText("label.system.url")})立场无关！<br/>
    本站所有小说的版权为原作者所有！如无意中侵犯到您的权益，或是含有非法内容，请及时与我们联系，我们将在第一时间做出回应！谢谢！<br/>
        ${getText("label.system.copyright")} ${getText("label.system.beianNo")} ${getText("label.system.analyticscode")}
        <#if pageType?? && pageType==1>
            <br>
            ${getText("label.system.support")}
        </#if>
    </p>
</footer>
<#if customizefooter?exists>  
    <@customizefooter/>  
</#if>
</#macro>