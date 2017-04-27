<#include "base.ftl"/>

<#macro titleContent>  
<#if category?? && category!=0 >
<title>${categorymap[category?c]}小说|${getText("label.system.title")}</title>
<meta name="keywords" content="${categorymap[category?c]},${categorymap[category?c]}小说,${getText("label.system.siteKeywords")}" />
<#elseif author?? >
<title>${author}的小说|${getText("label.system.title")}</title>
<meta name="keywords" content="${author}的小说,${getText("label.system.siteKeywords")}" />
<meta name="description" content="${getText("label.system.siteDescription")}" />
<#else>
<title>完本小说|${getText("label.system.title")}</title>
<meta name="keywords" content="完本小说,${getText("label.system.siteKeywords")}" />
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#if>
</#macro>
<#macro content>
    <div class="m10">
        <ul class="cate">
            <li id="categories">
                    <#list categorymap?keys as categorykey>
                    <a href="${encodeURL("/articleList?category=${categorykey}")}" <#if category?? && categorykey==category?c>class="curr"</#if>>${categorymap[categorykey]?substring(0,2)}</a>
                    </#list>
                    <a href="${encodeURL("/articleList?fullflag=true")}" <#if fullflag?? && fullflag>class="curr"</#if>>完本</a>
            </li>
            <li id="books">
            <#list articleList as article>
                <div class="info i3">
                 <a href="${article.url}">
                     <img src="${article.imgUrl}" />
                     <h3>${article.articlename}</h3>
                     <p>作者：${article.author}</p>
                     <p>字数：${article.size?c} (有${article.allvisit?c}人阅读)</p>
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
