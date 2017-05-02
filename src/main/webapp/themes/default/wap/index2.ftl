<#include "base.ftl"/>

<#macro titleContent>  
<title>${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>

<#macro recommendblock articleList title id style> 
<div class="${style}">
        <div class="title">${title}</div>
        <div id="divChange${id}" class="info m2">
            <#if articleList?? && (articleList?size > 0) >
            <a href="${articleList[0].url}">
                <img src="${articleList[0].imgUrl}" />
                <h3>${articleList[0].articlename?html}</h3>
                <p>作者：${articleList[0].author?html}</p>
                <p>${articleList[0].introForHtml}</p>
            </a>
            </#if>
        </div>
        <ul id="ulChange${id}" class="list">
        <#list articleList as article>
            <#if article_index !=0>    
                <li><a href="${article.url}">${article.categoryStr}：${article.articlename?html}</a></li>
            </#if>
        </#list>
        </ul>
</div>
</#macro>


<#macro content>
    <div class="m8">
        <div class="rec">
            <ul>
            <#list blocks.index_hot_list_mobile as article>
                <li><a href="${article.url}"><img height="117px" width="91px" src="${article.imgUrl}" /></a></li>
            </#list>
            </ul>
        </div>
    </div>

    <#if recommendblock?exists>
          <@recommendblock articleList=blocks.last_update_list_mobile title="最近更新" id="1" style="m5"/>
    </#if>
    
    <#if recommendblock?exists>
          <@recommendblock articleList=blocks.index_yanqing_tuijian title="热门言情" id="2" style="m10"/>
    </#if>
    
     <#if recommendblock?exists>
          <@recommendblock articleList=blocks.index_xuanhuan_tuijian title="热门玄幻" id="3" style="m10"/>
    </#if>
    
     <#if recommendblock?exists>
          <@recommendblock articleList=blocks.index_junshi_tuijian title="热门军事" id="4" style="m10"/>
    </#if>
    
     <#if recommendblock?exists>
          <@recommendblock articleList=blocks.index_kongbu_tuijian title="热门恐怖" id="5" style="m10"/>
    </#if>
    
     <#if recommendblock?exists>
          <@recommendblock articleList=blocks.index_wuxia_tuijian title="热门武侠" id="6" style="m10"/>
    </#if>

</#macro> 