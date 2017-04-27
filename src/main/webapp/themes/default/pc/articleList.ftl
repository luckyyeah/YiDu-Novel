<#include "common.ftl"/>

<#macro titleContent>  
<#if category?? && category!=0 >
<title>${categorymap[category?c]}小说|${getText("label.system.title")}</title>
<meta name="keywords" content="${categorymap[category?c]},${categorymap[category?c]}小说,${getText("label.system.siteKeywords")}" />
<#elseif author?? >
<title>${author?html}的小说|${getText("label.system.title")}</title>
<meta name="keywords" content="${author?html}的小说,${getText("label.system.siteKeywords")}" />
<meta name="description" content="${getText("label.system.siteDescription")}" />
<#elseif tag?? >
<title>标签：${tag?html}的小说|${getText("label.system.title")}</title>
<meta name="keywords" content="标签：${tag?html}的小说,${getText("label.system.siteKeywords")}" />
<meta name="description" content="${getText("label.system.siteDescription")}" />
<#else>
<title>完本小说|${getText("label.system.title")}</title>
<meta name="keywords" content="完本小说,${getText("label.system.siteKeywords")}" />
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#if>
</#macro>


<#macro content>
<div id="list_ad_01"></div>
<div class="mainnav" id="navList">
<div class="main-index">位置：  &nbsp; > &nbsp; <a href="#"><#if fullflag ?? && fullflag>全本小说<#elseif category ?? && category!=0>${categorymap[category?c]}<#elseif tag ??>标签：${tag?html}的小说<#elseif author??>${author?html}的小说<#else>小说列表页</#if></a></div>
    <section class="section board-list board-list-collapse">
    <ul class="seeWell cf">
        <#list articleList as article>
        <li>
           <a href="${article.url}"  title="${article.articlename}" class="l mr10">
                <img src="${article.imgUrl}" style="width: 120px; height: 150px"/></a>
           <#if article.fullflag ><img src="${contextPath}/themes/${themeName}/pc/images/only.png"  alt="完本图标" class="topss png_bg"><#else><img src="${contextPath}/themes/${themeName}/pc/images/only2.png"  alt="连载中图标" class="topss png_bg"></#if>
           <span class="l">
              <a href="${article.url}"  title="${article.articlename}" class="clearfix stitle">${article.articlename}</a>
              作者：<a href="${encodeURL("/articleList?author=${article.author}")}">${article.author}</a>
              <em class="c999 clearfix">${article.introForHtml}</em>
              更新：<a href="${article.lastChapterUrl}"  title="${article.lastchapter}">${article.lastchapterOmit}</a>
              <a href="<#if !enableChapterIndexPage >${article.url}<#else>${article.chapterListUrl}</#if>" class="readTo"  title="${article.articlename}">马上阅读</a>
           </span>
        </li>
        </#list>
    </ul>

    <div id="list_ad_02"></div>
          <div class="pages">
              <div class="pagelink" id="pagelink">
                <#if fullflag?? && fullflag>
                    <#assign listurl = "/articleList?fullflag=true&page=" >
                    <#assign listurlforjs = "${contextPath}/wanben/" >
                <#elseif category?? && category !=0 >
                    <#assign listurl = "/articleList?category=${category}&page=">
                    <#assign listurlforjs = "${contextPath}/list/${category}/" >
                <#elseif author??>
                    <#assign listurl = "/articleList?author=${author?html}&page=">
                    <#assign listurlforjs = "${contextPath}/list/${author?html}/" >
                <#elseif tag??>
                    <#assign listurl = "/articleList?tag=${tag?html}&page=">
                    <#assign listurlforjs = "${contextPath}/list/${tag?html}/" >
                <#else>
                    <#assign listurl = "/articleList?page=">
                    <#assign listurlforjs = "${contextPath}/list/" >
                </#if>
                <em id="pagestats">${pagination.pageNumber?c}/${pagination.totalPages?c}</em>
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
    </section>
  </div>
  <div id="list_ad_03"></div>
  </div>
</#macro>
