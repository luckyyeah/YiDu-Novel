<#include "common.ftl"/>
<#macro titleContent>  
<title>${chapter.articlename}-${chapter.chaptername}</title>
<meta name="keywords" content="${chapter.chaptername},${chapter.articlename}最新章节,${chapter.articlename}TXT下载,${chapter.articlename}无广告,${getText("label.system.name")}" />
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  
<#macro customizeimport>  
<link rel="stylesheet" type="text/css" href="${contextPath}/themes/${themeName}/pc/css/readtools.css"/>
</#macro>

<#macro content>
<div class="mainnav">
        <div class="main-index" id="direct">
            <span class="r mr10">
                <form action="${contextPath}/search" method="get" >
                    搜小说：<input type="text" name="key" value="" />
                    <button type="submit" >搜</button>
                </form>
            </span>
    位置：<a href="${contextPath}/" class="c009900">${getText("label.system.name")}</a> > 

    <a href="${article.url}" class="article_title">${chapter.articlename}</a>  > 
    <#if enableChapterIndexPage><a href="${article.chapterListUrl}" class="article_title">${chapter.articlename}章节目录</a> > </#if>
    ${chapter.chaptername}</div>
    <section class="main b-detail" id="directs">
        <div class="bookInfo">
            <h1>
                <span class="r"></span>
                <em class="l">《${chapter.articlename}》</em>
                <strong class="l jieqi_title">${chapter.chaptername}</strong>
            </h1>
            <div class="toolbar">
            <ul>
                <li>
                    <span class="fl">背景：</span>
                    <div class="fl">
                        <input id="bg1" onclick="setBG('#dcecf5')" type="button" class="setBG" />
                        <input id="bg2" onclick="setBG('#e1ffe6')" type="button" class="setBG" />
                        <input id="bg3" onclick="setBG('#edf6d0')" type="button" class="setBG" />
                        <input id="bg4" onclick="setBG('#eae8f7')" type="button" class="setBG" />
                        <input id="bg5" onclick="setBG('#f5f1e7')" type="button" class="setBG" />
                        <input id="bg6" onclick="setBG('#ebf4ef')" type="button" class="setBG" />
                        <input id="bg7" onclick="setBG('#FFFFFF')" type="button" class="setBG" />
                    </div>
                </li>
                <li>
                    <span class="fl">字体大小：</span>
                    <div class="fl">
                        <select onchange="setFontSize(this.value);" id="bcolor" name="bcolor">
                        <option value="#E9FAFF">大小</option>
                        <option value="16pt">默认</option><option value="10pt">10pt</option>
                        <option value="12pt">12pt</option><option value="14pt">14pt</option>
                        <option value="16pt">16pt</option><option value="18pt">18pt</option>
                        <option value="20pt">20pt</option><option value="22pt">22pt</option>
                        <option value="25pt">25pt</option><option value="30pt">30pt</option></select>
                    </div>    
                </li>
                <li>
                    <span class="fl">字体颜色：</span>
                    <div class="fl">
                        <select onchange="setFontColor(this.value)" id="txtcolor" name="txtcolor">
                            <option value="#000000">黑色</option>
                            <option value="#ff0000">红色</option>
                            <option value="#006600">绿色</option>
                            <option value="#0000ff">蓝色</option>
                            <option value="#660000">棕色</option>
                        </select>
                    </div>
                </li>
                <li id="sudu">
                    <span class="fl">滚动速度：</span>
                    <a id="sudu50" href="javascript:setSpeed(50);" rel="nofollow">快</a>
                    <a id="sudu100" href="javascript:setSpeed(100);" class="this" rel="nofollow">中</a>
                    <a id="sudu150" href="javascript:setSpeed(150);" rel="nofollow">慢</a>
                </li>
            </ul>
			<div style="clear:both;"></div>
        </div>
        <div id="reader_ad_01"></div>
        <div>
            <ul>
            <#list fullReadChapterList as c >
                <li style="float:left;width:28%;padding:5px 20px;font-size:14px;"><a href="#${c.chapterno?c}">${c.chaptername}</a></li>
            </#list>
            </ul>
        </div>
        <div class="clear"></div>
        <div class="mainContenr"   id="content" style="overflow:hidden">
			<#list fullReadChapterList as c >
                <a name="${c.chapterno?c}">${c.chaptername}</a><br/>
				<#if c.content??>${c.content}<br/></#if>
				<hr/><br/>				
            </#list>
        </div>
        <div id="reader_ad_02"></div>
        </div>
       </section>
       
    <div class="navTab">
        <ul>
            <li onmouseover="replaces(1,2)" id="for1" class="select"><a href="#">站长推荐</a></li>
            <li onmouseover="replaces(2,2)" id="for2" class ><a href="#">猜你喜欢</a></li>
        </ul>
    </div>
    <div class="tabMain">
        <#if blocks.reader_recommand_list ?? > 
        <ul id="content1">
            <#list blocks.reader_recommand_list as article>
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
        <#if blocks.reader_random_recommand_list ?? > 
        <ul id="content2" style="display:none;">
            <#list blocks.reader_random_recommand_list as article>
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
    </div>
</div>
<div id="reader_ad_04"></div>
</#macro>

<#macro customizefooter> 
    <div id="full2" style="width:37px; height:22px; position:fixed; left:50%; top:425px; margin-left:493px;  z-index:100; text-align:center; cursor:pointer;">
    <a class="get_top" alt="返回顶部" rel="nofollow"></a>
    </div>
    
    <div id="full" style="width:37px; height:22px; position:fixed; left:50%; top:562px; margin-left:493px;  z-index:100; text-align:center; cursor:pointer;">
    <a class="get_bottom" alt="跳至页尾" rel="nofollow"></a>
    </div>
</#macro>

<#macro customizeJs>  
<script type="text/javascript" src="${contextPath}/themes/${themeName}/pc/js/tools.js"></script>
<script type="text/javascript" src="${contextPath}/themes/${themeName}/pc/js/lib/jquery.tools.min1.2.5.js"></script>
<script type="text/javascript" src="${contextPath}/themes/${themeName}/pc/js/news_top.js"></script>
<script type="text/javascript">
    <!--
        var readHistoryObject = new Object();
        readHistoryObject.chapterno = ${chapter.chapterno?c};
        readHistoryObject.articleno = ${chapter.articleno?c};
        readHistoryObject.chaptername = "${chapter.chaptername}";
        readHistoryObject.articlename = "${chapter.articlename}";
        readHistoryObject.infoUrl="${article.url}";
        readHistoryObject.chapterUrl = "${chapter.url}";
    -->
</script>
</#macro>
