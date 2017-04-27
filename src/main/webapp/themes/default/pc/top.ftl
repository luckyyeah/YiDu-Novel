<#include "common.ftl"/>

<#macro assignContent> 
    <#assign topnamemap = topNameJsonData?eval>
</#macro>

<#macro titleContent>  
<title>${topnamemap[sortColumn]}|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}" />
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>

<#macro content>
    <div id="top_ad_01"></div>
    <div class="topLefft">
        <h1>排行榜</h1>
        <ul>
            <#list topnamemap?keys as topkey>
                <li <#if sortColumn??><#if topkey==sortColumn>class="select"</#if></#if>><a href="${encodeURL("/top?sortColumn=${topkey}")}">${topnamemap[topkey]}</a></li>
            </#list>
        </ul>
    </div>
    <div class="topRight">
        <h1 class="allClick"><span>${topnamemap[sortColumn]}</span></h1>
        <ul class="seeWell cf">
        <#list articleList as article>
        <#if article_index lt 3>
            <li><a class="l mr10" title="${article.articlename}" href="${article.url}" target="_blank">
            <img style="width: 120px; height: 150px" src="${article.imgUrl}" alt="${article.articlename}"></a>
            <#if article.fullflag ><img src="${contextPath}/themes/${themeName}/pc/images/only.png"  alt="完本图标" class="topss png_bg"><#else><img src="${contextPath}/themes/${themeName}/pc/images/only2.png"  alt="连载中图标" class="topss png_bg"></#if>
            <span class="l"><a class="clearfix stitle" href="${article.url}">
            <em <#if article_index ==0 >class="first"</#if>>${article_index + 1}</em>${article.articlename}</a>
            类型：<a href="${encodeURL("/articleList?category=${article.category}&page=1")}"><#if article.category!=0>${article.categoryStr}</#if></a><br/> 
            作者：<a href="${encodeURL("/articleList?author=${article.author}")}">${article.author}</a>
            <br>点击数：<label>${article.allvisit}</label>
            <a class="readTo" href="<#if !enableChapterIndexPage >${article.url}<#else>${article.chapterListUrl}</#if>">马上阅读</a></span></li>
        </#if>
        </#list>
        </ul>
        <div class="allList">
        <#list articleList as article>
        <#if article_index gt 2 >
          <dl><dt><em>${article_index + 1}</em>[
          <a href="${encodeURL("/articleList?category=${article.category}&page=1")}"> <#if article.category!=0>${article.categoryStr}</#if></a>]</dt>
          <dd class="title"><a href="${article.url}">${article.articlename}</a></dd>
          <dd class="state">最新章节：<a href="${article.lastChapterUrl}"><#if article.lastchapter?? >${article.lastchapter}</#if>（ </a> [${article.lastupdate?string("MM-dd HH:mm")}]</dd>
          <dd class="authors">作者：<a href="${encodeURL("/articleList?author=${article.author}")}">${article.author}</a></dd></dl>
        </#if>
        </#list>
        <div id="top_ad_02"></div>
        <div class="mainnav" id="navList">
        <div class="pages">
              <div class="pagelink" id="pagelink">
                <#assign listurl = "/top?sortColumn=${sortColumn}&page=">
                <#assign listurlforjs = "${contextPath}/top/${sortColumn}/" >
                <em id="pagestats">${pagination.pageNumber}/${pagination.totalPages?c}</em>
                <a href="${encodeURL(listurl +"1")}" class="first">首页</a>
                <#list pagination.pageNumberList as pagenum >
                    <#if pagenum_index == 0 && (pagenum > 1 )>
                        <a href="${encodeURL(listurl+ (pagenum-1)?c)}" class="prev">&lt;</a>
                    </#if>
                    <#if pagenum == pagination.pageNumber>
                        <strong>${pagenum?c}</strong>
                    <#else>
                        <a href="${encodeURL(listurl + pagenum?c)}"> ${pagenum?c} </a>
                    </#if>
                    <#assign mxpagenum = pagenum >
                </#list>
                <#if mxpagenum?? && (mxpagenum < pagination.totalPages)>
                     <a href="${encodeURL(listurl + (mxpagenum+1)?c)}" class="next">&gt;</a>
                </#if>
                <a href="${encodeURL(listurl + pagination.totalPages?c)}">尾页</a>
                <kbd>
                    <input name="page" type="text" size="4" maxlength="6" onkeydown="if(event.keyCode==13){window.location='${listurlforjs}'+this.value+'.html'; return false;}" /></kbd>
             </div>
        </div>
        </div>
        </div>
    </div>
    <div id="top_ad_03"></div>
</div>
</#macro>
