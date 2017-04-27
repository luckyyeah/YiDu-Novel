<#include "common.ftl"/>

<#macro titleContent>
<title><#if key??>${key?html}的搜索结果<#else>小说搜索</#if>|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro content>
<div class="mainnav" id="navList">
    <div id="search_ad_01"></div>
    <table class="grid" width="100%" align="center">
    <caption>搜索结果</caption>
      <tr align="center">
        <th width="20%">文章名称</th>
        <th width="40%">最新章节</th>
        <th width="15%">作者</th>
        <th width="9%">字数</th>
        <th width="10%">更新</th>
        <th width="6%">状态</th>
      </tr>
      <#list articleList as article>
      <tr>
        <td class="even"><a href="${article.url}">${article.articlename}</a></td>
        <td class="even"><a href="${article.lastChapterUrl}" target="_blank"> ${article.lastchapter}</a></td>
        <td class="odd"><a href="${encodeURL("/articleList?author=${article.author}")}">${article.author}</a></td>
        <td class="odd">${article.size}</td>
        <td class="odd" align="center">${article.lastupdate?string("MM-dd HH:mm")}</td>
        <td class="even" align="center"><#if article.fullflag?? && article.fullflag >完结<#else>连载中</#if></td>
      </tr>
      </#list>
    </table>
    <div id="search_ad_02"></div>
    <#if key??>
    <div class="pages">
        <div class="pagelink" id="pagelink">
            <#assign listurl = "/search?key=${key?html}&page=">
            <#assign listurlforjs = "${contextPath}/search/${key?html}/" >
            <em id="pagestats">${pagination.pageNumber}/${pagination.totalPages}</em>
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
            <#if mxpagenum?? && ( mxpagenum < pagination.totalPages)>
                 <a href="${encodeURL(listurl + (mxpagenum+1)?c)}" class="next">&gt;</a>
            </#if>
            <a href="${encodeURL(listurl + pagination.totalPages?c)}">尾页</a>
            <kbd>
                <input name="page" type="text" size="4" maxlength="6" onkeydown="if(event.keyCode==13){window.location='${listurlforjs}'+this.value+'.html'; return false;}" /></kbd>
        </div>
    </div>
    </#if>
</div>
</#macro>
