<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<title>${chapter.articlename}-${chapter.chaptername}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="keywords" content="${chapter.chaptername},${chapter.articlename}最新章节,${chapter.articlename}TXT下载,${chapter.articlename}无广告,${getText("label.system.name")}" />
    <meta name="description" content="《${article.articlename}》是由${article.author}所写的一部情节跌宕起伏、扣人心弦的${article.categoryStr}小说，本站免费提供最新的清爽干净的文字章节在线阅读!" />
    <link href="${contextPath}/themes/${themeName}/single/css/StyleSheet.css" rel="stylesheet" type="text/css" /></head>
</head>
<body>
<div class="head">
    <div class="padding">
        <div class="header">
            <ul class="menu">
                <li class="on"><a href="${contextPath}/" title="${article.articlename}">${article.articlename}</a></li>
                <li style='color:#FFF;padding-left:20px;'>《${article.articlename}》是由${article.author}所写的一部情节跌宕起伏、扣人心弦的${article.categoryStr}经典小说！</li>
            </ul>
        </div>
    </div>
</div>
<div class="src_top">
    <div class="srcbox">
        <a href='${contextPath}/'></a> > <a href='${contextPath}/'>${chapter.articlename}</a> > ${chapter.chaptername}
    </div>
    <div style="float:right;">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <script type="text/javascript">document.write('<td><select name=novelbgcolor id=novelbgcolor onchange="javascript:document.getElementById(\'jsnc_l\').style.backgroundColor=this.options[this.selectedIndex].value;" class="novel_set"><option value="#CCE399">阅读底色</option><option value="#E9FAFF" style="background-color: #E9FAFF">淡蓝海洋</option><option value="#FFFFED" style="background-color: #FFFFED">明黄清俊</option><option value="#eefaee" style="background-color: #eefaee">绿意淡雅</option><option value="#FCEFFF" style="background-color: #FCEFFF">红粉世家</option><option value="#ffffff" style="background-color: #ffffff">白雪天地</option><option value="#F8F8F8" style="background-color: #F8F8F8">灰色世界</option></select></td><td><select name=txtcolor id=txtcolor onchange="javascript:document.getElementById(\'htmlContent\').style.color=this.options[this.selectedIndex].value;" class="novel_set"><option value="#000000">字体颜色</option><option value="#ff0000">红色</option><option value="#006600">绿色</option><option value="#0000ff">蓝色</option><option value="#660000">棕色</option><option value="#000000">黑色</option></select></td><td><select name=fonttype id=fonttype onchange="javascript:document.getElementById(\'htmlContent\').style.fontSize=this.options[this.selectedIndex].value;" class="novel_set"><option value="16pt">字体大小</option><option value="9pt">小</option><option value="10.5pt">中</option><option value="12pt">中大</option><option value="14pt">大</option><option value="16pt">很大</option><option value="18pt">更大</option> </select></td><td><select name=scrollspeed id=scrollspeed onchange="javascript:setSpeed();" class="novel_set"><option value="5">滚屏速度</option><option value="1">慢1</option><option value="2">慢2</option><option value="3">慢3</option><option value="4">中4</option><option value="5">中5</option><option value="6">中6</option><option value="7">快7</option><option value="8">快8</option><option value="9">快9</option></select></td><td><input name=saveset id=saveset onclick="javascript:saveSet();" type=button value="保存"></td>');</script>
            </tr>
        </table>
    </div>
</div>
<div class="ncon" id="articleContent">
    <div class="nc_l" id="jsnc_l">
        <div class="wrapper_main">
            <div class="h1title">
                <h1>${chapter.chaptername}</h1>
            </div>
        <div id="htmlContent" class="contentbox">
            <div class="chapter_Turnpage">
                <a id='pager_prev' class='pre' href='<#if chapter.preChapterno ==0>${contextPath}/<#else>${chapter.preChapterThumbnailUrl}</#if>'>上一篇</a> 
                <a id="pager_current" href="${contextPath}/" target="_top" title="${article.articlename}" class="back">返回目录</a>
                <a id='pager_next' class='pre' href='<#if chapter.nextChapterno ==0>${contextPath}/<#else>${chapter.nextChapterThumbnailUrl}</#if>'>下一篇</a> 
                <p>小提示：按 回车[Enter]键 返回书目，按 ←键 返回上一页， 按 →键 进入下一页。</p>
        </div>
        <#if chapter.content??>${chapter.content}</#if>
    </div>
    
    <div class="chapter_Turnpage">
        <a id='pager_prev' class='pre' href='<#if chapter.preChapterno ==0>${contextPath}/<#else>${chapter.preChapterThumbnailUrl}</#if>'>上一篇</a> 
        <a id="pager_current" href="${contextPath}/" target="_top" title="${article.articlename}" class="back">返回目录</a> 
        <a id='pager_next' class='pre' href='<#if chapter.nextChapterno ==0>${contextPath}/<#else>${chapter.nextChapterThumbnailUrl}</#if>'>下一篇</a> 
        <p>小提示：按 回车[Enter]键 返回书目，按 ←键 返回上一页， 按 →键 进入下一页。</p>
    </div>

    <SCRIPT LANGUAGE="JavaScript">
    <!--
    var pager_prev=document.getElementById("pager_prev").href;
    var pager_next=document.getElementById("pager_next").href;
    var pager_current =document.getElementById("pager_current").href;
    function jumpPage() {
    var event = document.all ? window.event : arguments[0];
    if (event.keyCode == 37) document.location = pager_prev;
    if (event.keyCode == 39) document.location = pager_next;
    if (event.keyCode == 13) document.location = pager_current;
    }
    document.onkeydown=jumpPage;
    //-->
    </SCRIPT>
    </div>
</div>
</div>
</div>
<script src='${contextPath}/themes/${themeName}/single/js/read.js' language='javascript'></script>
<div class="head2">
<div class="padding">
<div class="header">
<ul class="menu">
<li class="on"><a href="${contextPath}/" title="${article.articlename}">${article.articlename}</a></li>
<li style='color:#FFF;padding-left:20px;'>喜欢《${article.articlename}》的书迷们，请记住本站http://${singleBookServerName}丫，这里绝对是你阅读的首选！</li>
</ul>
</div>
</div>
</div>
<div class="foot">
    <p>本站提供的《${article.articlename}》，是由${article.author}所写的一部情节跌宕起伏、扣人心弦的${article.categoryStr}小说。《${article.articlename}》版权属于${article.author}，从网络收集只为更多的书迷们欣赏!</p>
    <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"1","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"3","bdPos":"right","bdTop":"100"},"image":{"viewList":["tieba","qzone","sqq","tsina","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["tieba","qzone","sqq","tsina","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</div>
<div>
</div>

    <div id="full2" style="width:37px; height:22px; position:fixed; left:50%; top:425px; margin-left:493px;  z-index:100; text-align:center; cursor:pointer;">
        <a class="get_top" alt="返回顶部"></a>
    </div>
    <div id="full" style="width:37px; height:22px; position:fixed; left:50%; top:562px; margin-left:493px;  z-index:100; text-align:center; cursor:pointer;">
        <a class="get_bottom" alt="跳至页尾"></a>
    </div>
    <script src="${contextPath}/themes/${themeName}/single/js/news_top.js" type="text/javascript"></script>
</body>
</html>