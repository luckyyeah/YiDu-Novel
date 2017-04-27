<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>${article.articlename}|${article.articlename}最新章节|${article.articlename}TXT下载|${article.articlename}单本阅读</title>
	<meta name="keywords" content="${article.articlename}，${article.articlename}单本，${article.articlename}最新章节，${article.articlename}TXT下载，${article.author}的小说" />
	<meta name="description" content="《${article.articlename}》是由${article.author}所写的一部情节跌宕起伏、扣人心弦的${article.categoryStr}小说，本站免费提供最新的清爽干净的文字章节在线阅读!" />
	<meta name="copyright" content="${article.articlename}版权属于作者：${article.author}" />
	<link href="${contextPath}/themes/${themeName}/single/css/StyleSheet.css" rel="stylesheet" type="text/css" /></head>
	<!--360结构化-->
	<meta property="og:type" content="novel"/>
	<meta property="og:title" content="${article.articlename?html}"/>
	<meta property="og:description" content="<#if (article.intro ?length != 0)>${article.intro?html}<#else>暂无简介</#if>"/>
	<meta property="og:image" content="${article.imgUrl}"/>
	<meta property="og:novel:category" content=${article.categoryStr}"/>
	<meta property="og:novel:author" content="${article.author?html}"/>
	<meta property="og:novel:book_name" content="${article.articlename?html}"/>
	<meta property="og:novel:read_url" content="${article.url}"/>
	<!--选填-->
	<meta property="og:novel:status" content="<#if article.fullflag>完结<#else>连载中</#if>"/>
	<meta property="og:novel:update_time" content="${article.lastupdate?string("yyyy-MM-dd HH:mm")}"/>
	<meta property="og:novel:click_cnt" content="${article.allvisit?c}"/>
	<meta property="og:novel:latest_chapter_name" content="${article.lastchapter}"/>
	<meta property="og:novel:latest_chapter_url" content="${article.lastChapterUrl}"/>
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
<div class="lbox">
	<div class="section">
		<div class="book_info">
			<div class="pic">
				<a href="/"><img alt="${article.articlename}" src="${article.imgUrl}" style="width: 120px;height: 150px;" /></a>
			</div>
			<h1 style="width:200px;"><a href="/">${article.articlename}</a></h1>
			<span style="float:left;">
                <!-- 将此标记放在您希望显示like按钮的位置 -->
                <div class="bdlikebutton"></div>
                <!-- 将此代码放在适当的位置，建议在body结束前 -->
                <script id="bdlike_shell"></script>
                <script>
                var bdShare_config = {
                    "type":"large",
                    "color":"red",
                    "uid":"681543",
                    "likeText":"支持 ${article.articlename}",
                    "likedText":"您已经顶过谢谢"
                };
                document.getElementById("bdlike_shell").src="http://bdimg.share.baidu.com/static/js/like_shell.js?t=" + Math.ceil(new Date()/3600000);
                </script>
            </span>
            <div class="infos">
                <span><h3> 
                </h3></span>
            </div>
            <div class="upd">
                    最新章节： <a href="${article.thumbnailLastChapterUrl}" title="${article.lastchapter}">${article.lastchapter}</a>&nbsp;&nbsp;&nbsp;&nbsp;${article.lastupdate?string("yyyy-MM-dd HH:mm")}
            </div>
            <p><a href="${article.thumbnailLastChapterUrl}" title="${article.lastchapter}">《${article.articlename}》是由${article.author}所写的一部情节跌宕起伏、扣人心弦的${article.categoryStr}小说， ${article.lastchapter} 是小说《${article.articlename}》的最新章节。</a></p>
        </div>
    </div>
    <div class="lbox" style="background:#FFFFFF;">
        <p style="text-align:center;padding:10px;">
            <span style="font-size:26px;color:green;padding:10px;">${article.articlename}-${article.author}</span></p>
        <p style="text-align:left;color:green;padding:10px;">
            ${article.intro?html}</p> 
        <p style="text-align:left;color:green;padding:10px;">
            &nbsp;&nbsp;为方便您下次继续访问，请使用<span style="color:red;">Ctrl+D</span>快捷键收藏本站！ ^_^</p>
    </div>
    <div class="book_list">
        <h2>${article.articlename}最新章节</h2>
            <ul>
            <#list chapterList as chapter>
                <li><a href="${chapter.thumbnailUrl}" title="${chapter.chaptername}">${chapter.chaptername}</a></li>
            </#list>
            </ul>
    </div>
</div>
<div class="head2">
<div class="padding">
<div class="header">
<ul class="menu">
<li class="on"><a href="/" title="${article.articlename}">${article.articlename}</a></li>
<li style="color:#FFF;padding-left:20px;">喜欢《${article.articlename}》的书迷们，请记住本站http://${singleBookServerName}丫，这里绝对是你阅读的首选！</li>
</ul>
</div>
</div>
</div>
<div class="links">
    <h5>友情链接</h5>
    <ul>
        <li><a href='http://www.51yd.org' target='_blank' title="易读小说系统">易读小说系统</a> </li>
    </ul>
</div>
<div class="foot">
    <p>本站提供的《${article.articlename}》，是由${article.author}所写的一部情节跌宕起伏、扣人心弦的${article.categoryStr}小说。《${article.articlename}》版权属于${article.author}，从网络收集只为更多的书迷们欣赏!<br>${getText("label.system.support")}</p>
    <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"1","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"3","bdPos":"right","bdTop":"100"},"image":{"viewList":["tieba","qzone","sqq","tsina","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["tieba","qzone","sqq","tsina","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</div>
</body>
</html>