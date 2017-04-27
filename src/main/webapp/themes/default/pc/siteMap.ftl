<#include "common.ftl"/>
<#macro titleContent>  
<title>网站地图|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  
<#macro content> 
   <p class="b-all-switch normal">网站地图:</p>
    <div class="mainLink">
        <#list articleList as article><a class="poptext" href="${article.url}" title="${article.articlename}最新章节" class="f14">${article.articlename}</a></#list>
    </div>
</#macro>

