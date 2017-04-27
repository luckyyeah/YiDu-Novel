<#include "common.ftl"/>

<#macro titleContent>
<#if article.usecustomizelisttitle ??  && article.usecustomizelisttitle >
<title>${article.listtitle}</title>
<meta name="keywords" content="${article.listkeywords}" />
<meta name="description" content="${article.listdescription}" />
<#else>
<title>${article.articlename}章节列表|${article.articlename}最新章节</title>
<meta name="keywords" content="${article.articlename}章节列表,${article.articlename}最新章节,${article.articlename}TXT下载,${article.articlename}无广告,${getText("label.system.name")}" />
<meta name="description" content="《${article.articlename}》章节列表，,${article.articlename}最新章节，${getText("label.system.name")}免费提供${article.articlename}最新的清爽干净的文字章节在线阅读!" />
</#if>
</#macro>

<#macro content>
    <div id="chapterList_ad_01"></div>
    <div class="mainnav"><div class="main-index"> 位置：  &nbsp; > &nbsp; 
        <a href="${encodeURL("/articleList?category=${article.category}")}" class="c009900">
        ${article.categoryStr}</a> &nbsp; > &nbsp; 
        <a href="${article.url}" class="c009900">${article.articlename}</a>
    </div>
    <div id="chapterList_ad_02"></div>
    <div class="clear"></div>
    <div class="chapterNum">
        <a name="chapters"></a>
          <ul>
            <h1>《${article.articlename}》分卷阅读<#if !loginFlag>([<a href="${encodeURL("/login")}">登陆</a>]后开放)</#if></h1>
            <#list chapterList as chapter>
                <#if chapter.chaptertype == 0>
					<#if loginFlag>
						<#if chapter_index % 30 == 0>
							<li style="width:100%;background-color:#f2f9f1;margin-left:0;text-align:center;">
								<#if (chapter_index + 30 lt chapterList?size)>
									<a href="${encodeURL("/reader?subdir=${article.subdir?c}&articleno=${article.articleno?c}&chapterno=${chapterList[chapter_index].chapterno?c}&toChapterno=${chapterList[chapter_index+29].chapterno?c}")}">分段阅读</a>
								<#else>
									<a href="${encodeURL("/reader?subdir=${article.subdir?c}&articleno=${article.articleno?c}&chapterno=${chapterList[chapter_index].chapterno?c}&toChapterno=${chapterList[chapterList?size-1].chapterno?c}")}">分段阅读</a>
								</#if>
							</li>
						</#if>
					</#if>
                    <li>
                    <a href="${chapter.url}" title="${chapter.chaptername}">${chapter.chaptername}</a>
                    </li>
                </#if>
            </#list>
          </ul>
        </div>
    </div>
    <div id="chapterList_ad_03"></div>
        <div class="navTab">
        <ul>
            <li onmouseover="replaces(1,2)" id="for1" class="select"><a href="#">站长推荐</a></li>
            <li onmouseover="replaces(2,2)" id="for2" class ><a href="#">猜你喜欢</a></li>
        </ul>
    </div>

    <div class="tabMain">
        <#if blocks.chapterList_recommand_list ?? > 
        <ul id="content1">
            <#list blocks.chapterList_recommand_list as article>
            <li><a href="${article.url}" title="${article.articlename}"><img src="${article.imgUrl}" width="111px;" height="146px;" alt="${article.articlename}"></a>
            <#if article.fullflag>
                <img src="${contextPath}/themes/${themeName}/pc/images/only.png" class="topss png_bg" alt="完本图标">
            <#else>
                <img src="${contextPath}/themes/${themeName}/pc/images/only2.png" class="topss png_bg"  alt="连载中图标">
            </#if>
            <a href="${article.url}">${article.articlename}</a></li>
            </#list>
        </ul>
        </#if>
        <#if blocks.chapterList_randomrecommand_list ?? > 
        <ul id="content2" style="display:none;">
            <#list blocks.chapterList_randomrecommand_list as article>
           <li><a href="${article.url}" title="${article.articlename}"><img src="${article.imgUrl}" width="111px;" height="146px;" alt="${article.articlename}"></a>
            <#if article.fullflag>
                <img src="${contextPath}/themes/${themeName}/pc/images/only.png" class="topss png_bg" alt="完本图标">
            <#else>
                <img src="${contextPath}/themes/${themeName}/pc/images/only2.png" class="topss png_bg"  alt="连载中图标">
            </#if>
            <a href="${article.url}">${article.articlename}</a></li>
            </#list>
        </ul>
        </#if>
    </div>
</#macro>

<#macro customizefooter> 
    <div id="full2" style="width:37px; height:22px; position:fixed; left:50%; top:425px; margin-left:493px;  z-index:100; text-align:center; cursor:pointer;">
        <a class="get_top" alt="返回顶部"></a>
    </div>
    <div id="full" style="width:37px; height:22px; position:fixed; left:50%; top:562px; margin-left:493px;  z-index:100; text-align:center; cursor:pointer;">
        <a class="get_bottom" alt="跳至页尾"></a>
    </div>
</#macro>
<#macro customizeJs>  
<script type="text/javascript" src="${contextPath}/themes/${themeName}/pc/js/news_top.js"></script>
</#macro>
