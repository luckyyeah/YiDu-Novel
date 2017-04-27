<#include "common.ftl"/>
<#macro titleContent>  
<title>${chapter.articlename}-${chapter.chaptername}</title>
<meta name="keywords" content="${chapter.chaptername},${chapter.articlename}最新章节,${chapter.articlename}TXT下载,${chapter.articlename}无广告,${getText("label.system.name")}" />
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  
<#macro customizeimport>  
<link rel="stylesheet" type="text/css" href="${contextPath}/themes/${themeName}/pc/css/readtools.css" />
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
    位置：&nbsp; > &nbsp; <a href="${contextPath}/" class="c009900">${getText("label.system.name")}</a> > 
    <a href="${article.url}" class="article_title">${chapter.articlename}</a>  > 
    <#if enableChapterIndexPage><a href="${article.chapterListUrl}" class="article_title">${chapter.articlename}章节目录</a> > </#if>
    ${chapter.chaptername}</div>
    <section class="main b-detail" id="directs">
        <div class="bookInfo">
            <h1>
                <span class="r"></span>
                <em class="l">《${chapter.articlename}》</em>
                <strong class="l jieqi_title">${chapter.chaptername}</strong>
                <a href="javascript:return false;" articleno="${article.articleno?c}" chapterno="${chapter.chapterno?c}"  id="addBookcaseButton" title="加入书签" class="l" rel="nofollow">加入书签</a>
                <a href="javascript:return false;" articleno="${article.articleno?c}"  id="voteButton"  title="推荐本书" class="l" rel="nofollow">推荐本书</a>
                <a href="javascript:return false;" articleno="${article.articleno?c}"  id="addSubscribeButton" title="订阅本书" class="l" rel="nofollow">订阅本书</a>
                <a href="/user/messageEdit?title=${article.articlename}-章节错误&content= 关于《${article.articlename}》举报原因如下，错误章节： ${chapter.chaptername}  "  class="l" target="_blank" rel="nofollow">内容报错</a>
                <a href="/user/messageEdit?title=${article.articlename}-更新太慢啦!&content=${article.articlename} 更新太慢了,请加快速度,别的网站都有最新章节了.(请您最好告诉我们现在有哪个网站更新速度比我们快，以便使我们知道进度落后的状况) 以下网站比${siteName}更新的快:  "  class="l" target="_blank" rel="nofollow">更新慢了</a>
                <div class="clear"></div>
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
                        <option value="14pt">默认</option><option value="10pt">10pt</option>
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
        </div>
        <div id="reader_ad_01"></div>
        <div class="mainContenr"   id="content">
            <#if chapter.content??>${chapter.content}</#if>
        </div>
        <div id="reader_ad_02"></div>
        <div class="backs">
            <a href="${chapter.preChapterUrl}" class="pre">上一章</a>
            <a href="<#if enableChapterIndexPage>${article.chapterListUrl}<#else>${article.url}</#if>" class="backfor">返回目录</a>
            <a href="${chapter.nextChapterUrl}" class="next">下一章</a>
            <p>小提示： 按←键返回上一页，按→键进入上一页,您还可以
                 <a href="${encodeURL("/user/bookcase!add?articleno=${chapter.articleno?c}&chapterno=${chapter.chapterno?c}")}" title="加入书签"  target="_blank">加入书签</a>
            </p></div>
        <div id="reader_ad_03"></div>
        </div>
       </section>
       <div class="attention">
            <em>阅读提示：</em><br/>
            1、本站会员登录后，将免费体会到最顺畅的阅读方式[<em>最少广告</em>]。<br/>
            2、<em>注册本站会员</em>，将《<a href="${article.url}" class="article_title"><em>${chapter.articlename}</em></a>》加入书架，可以通过书架更快的了解更新信息。<br/>
            3、免费小说《<a href="${article.url}" class="article_title"><em>${chapter.articlename}</em></a>》 ${chapter.chaptername}所描述的内容只是作者个人观点，与本站的立场无关，本站只为广大用户提供阅读平台。
        </div>
        
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

<#macro customizeJs>
<script type="text/javascript" src="${contextPath}/themes/${themeName}/pc/js/tools.js"></script>
<script type="text/javascript">
    <!--
        var preview_page = "${chapter.preChapterUrl}";
        var next_page = "${chapter.nextChapterUrl}";
        var index_page = '<#if enableChapterIndexPage>${article.chapterListUrl}<#else>${article.url}</#if>';
        var readHistoryObject = new Object();
        readHistoryObject.chapterno = ${chapter.chapterno?c};
        readHistoryObject.articleno = ${chapter.articleno?c};
        readHistoryObject.chaptername = "${chapter.chaptername}";
        readHistoryObject.articlename = "${chapter.articlename}";
        readHistoryObject.infoUrl="${article.url}";
        readHistoryObject.chapterUrl = "${chapter.url}";
        document.onkeydown=jumpPage;
    -->
</script>
</#macro>
