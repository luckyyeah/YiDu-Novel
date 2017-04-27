<#include "base.ftl"/>

<#macro titleContent>
<#assign topNameMap = topNameJsonData?eval>
<#assign mobileTopNameMap = mobileTopNameJsonData?eval>
<title>${topNameMap[sortColumn]}|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}" />
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>

<#macro content>
    <div class="m10">
        <ul class="cate">
            <li id="categories">
                    <#list mobileTopNameMap?keys as topkey>
                    <a href="${encodeURL("/top?sortColumn=${topkey}")}" <#if sortColumn==topkey>class="curr"</#if>>${mobileTopNameMap[topkey]?substring(0,3)}</a>
                    </#list>
            </li>
            <li id="books">
            <#list articleList as article>
                <div class="info i3">
                 <a href="${article.url}">
                     <img src="${article.imgUrl}" />
                     <h3>${article.articlename}</h3>
                     <p>作者：${article.author}</p>
                     <p>类别：${article.categoryStr}</p>
                     <#if article.fullflag>
                           <em class="wj3"></em>
                     </#if>
                 </a>
             </div>
             </#list>
            </li>
        </ul>
        <div class="m1">
            <a id="lnkMore" class="loading ww" href="/search">前往搜索页查看更多作品</a>
        </div>
    </div>
</#macro>
