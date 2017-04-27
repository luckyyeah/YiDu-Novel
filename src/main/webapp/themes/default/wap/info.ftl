<#include "base.ftl"/>

<#macro titleContent>
<title>${article.articlename?html}|${article.articlename?html}最新章节|${article.articlename?html}TXT下载</title>
<meta name="keywords" content="${article.articlename?html},${article.articlename?html}最新章节,${article.articlename?html}TXT下载,${article.articlename?html}无广告,${getText("label.system.name")}" />
<meta name="description" content="《${article.articlename?html}》情节跌宕起伏、扣人心弦，是一本情节与文笔俱佳的<#if article.category!=0>${article.categoryStr}</#if>小说，${getText("label.system.name")}免费提供${article.articlename?html}最新的清爽干净的文字章节在线阅读!" />
</#macro>

<#macro content>
    <div class="box m10" style="padding:10px 0">
        <div class="info i4" style="border-bottom:none;">
            <a href="javascript:;">
                <img src="<#if article.imgUrl ?? >${article.imgUrl}</#if>" />
                <h3>${article.articlename?html}</h3>
                <p>作者：${article.author?html}</p>
                <p>类别：<#if article.category!=0>${article.categoryStr}</#if></p>
                <p>字数：<#if article.size ??>${article.size}<#else>0</#if></p>
                <p>点击：${article.allvisit}</p>
                <em id="isWanjie" class="wj4"></em>
            </a>
        </div>
        <div class="m10">
            <ul class="group g3">
                <li><a class="button blue r3" href="#chapters">开始阅读</a></li>
                <li><a class="button color2 r3" href="javascript:setShelf();" id="lnkShelf" data-value="0">加入书架</a></li>
            </ul>
        </div>
    </div>
    
    <div id="panels" class="m2">
        <div class="box"> 
            <div class="new">
                <a href="${article.lastChapterUrl}">
                    <p class="name">最新章节：<#if article.lastchapter?? >${article.lastchapter?html}</#if></p>
                    <p class="time">更新时间：${article.lastupdate?string("yyyy-MM-dd HH:mm:ss")}</p>
                </a>
            </div>
            <div class="line"></div>
            <div class="intro box">
                ${article.introForHtml}
            </div>
            <div class="sw">
                <a id="showAll" href="javascript:;" data-value="0">显示全部</a>
                <div style="display:none">
                    <script type="text/javascript">
                        $("#showAll").click(function () {
                            var value = $(this).attr("data-value");
                            if (value == 0) {
                                $(this).attr("data-value", "1");
                                $(this).html("隐藏部分");
                                $(".intro").css({ height: "auto" });
                                return;
                            }
                            $(this).attr("data-value", "0");
                            $(this).html("显示全部");
                            $(".intro").css({ height: "132px" });
                        });
                    </script>
                </div>
            </div>
        </div>
    </div>
    <a name="chapters"></a>
    <div class="m10">
            <ul id="tejia" class="group g1">
                <li><span class="button blue r3">章节列表</span></li>
            </ul>
    </div>

    <div class="m2">
        <div class="title">
            共<span id="spnChapters"><#if chapterList?? >${chapterList?size}<#else>0 </#if></span>章节
            <a id="sort" class="more2" data-value="1">↑倒序排列</a>
        </div>
        <div class="m2">
            <ul class="list" id="chapterlist"></ul>
        </div>
        <div class="paging">
            <ul>
                <li data-value="1"></li>
                <li data-value="2"></li>
                <li><select id="selPage"></select></li>
                <li data-value="3"></li>
                <li data-value="4"></li>
            </ul>
        </div>
    </div>
</#macro>

<#macro customizefooter> 
    <script type="text/javascript">

        var _articleno = parseInt(${article.articleno?c});
        var _size = parseInt(10);
        var _index = 0;
        var _hisgoryId = 0;
        var _sort = 0;
        var _isFirst = 0;
        var _pages = 0;
        var _userid = 0;
        <#if loginUser?? >
            _userid = parseInt(${loginUser.userno?c});
        </#if>
        // 获取历史浏览记录
        var getHistoryId = function () {

            var value = Util.CookieValue("READ_HISTORY");

            if (value != "") {
                var items = value.split("|");
                for (var i = 0; i < items.length; i++) {
                    var arr = items[i].split(",");
                    if (parseInt(arr[0]) == _articleno)
                        return parseInt(arr[1]);
                }
            }
            return 0;
        }
        
         // 设置书架
        var setShelf = function () {

            if (_userid == 0) {
                Site.showLoginbox(function (uid) {
                    _userid = uid;
                    $.get("${encodeURL("/ajaxService")}", $.param({ action: "bookcaseisexists", articleno: _articleno })
                        , function (res) {
                            if (res.code == 0) {
                                $("#lnkShelf").attr("data-value", res.result);
                                $("#lnkShelf").html(res.result == 1 ? "取消收藏" : "加入书架");
                            }
                        });
                });
                return;
            }

            var temp = $("#lnkShelf");
            var value = parseInt(temp.attr("data-value"));

            if (value == 1) {
                temp.html("删除中...");
                $.get("${encodeURL("/ajaxService")}", $.param({ action: "deletebookcasebyarticle", articleno: _articleno })
                    , function (res) {
                        if (res.code == 0) {
                            $("#lnkShelf").html("加入书架");
                            $("#lnkShelf").attr("data-value", 0);
                            Util.Alert("成功取消收藏");
                            return;
                        }

                        $("#lnkShelf").html("取消收藏");
                        Util.Alert("取消收藏时发生了错误");
                    });
                return;
            }

            temp.html("添加中...");
            $.get("${encodeURL("/ajaxService")}", $.param({ action: "addbookcase", articleno: _articleno })
                , function (res) {
                    if (res.code == 0) {
                        $("#lnkShelf").html("取消收藏");
                        $("#lnkShelf").attr("data-value", 1);
                        Util.Alert("成功加入书架");
                        return;
                    }
                    $("#lnkShelf").html("加入书架");
                    Util.Alert("加入书架时发生了错误");
                });

        }
        

        // 获取章节目录
        var getDirectory = function (index , callback) {
            Util.Loading();
            $.get("${encodeURL("/ajaxService")}"
                , $.param({ action:"chapterlist",articleno: _articleno, index: index, size: _size, sort: _sort })
                , function (result) {
                    Util.LoadingClear();
                    $("#spnChapters").html(result.total);
                    _pages = result.pages;

                    if (result.items.length > 0) {
                        var tmp = [];
                        for (var i = 0; i < result.items.length; i++) {
                            var item = result.items[i];
                            var link = "/reader/"+Math.floor(_articleno/1000)+"/"+_articleno+"/"+item.chapterno+".html";
                            var viplink ="javascript:checkVipReader("+item.chapterno+")"
                            tmp.push('<li>');
                            if(item.isvip){
                             tmp.push('<a href="' + viplink + '" class="vipreader"><em>VIP</em>' + item.chaptername + '</a>');
                            }else{
                             tmp.push('<a href="' + link + '">' + item.chaptername + '</a>');
                            }
                            if (_hisgoryId == item.chapterno) tmp.push('<em class="sq"></em>');
                            
                            tmp.push('</li>');
                        }
                        $("#chapterlist").html(tmp.join(""));
                        _index = index;

                        if (_isFirst == 0) {
                            var sel = $("#selPage");
                            sel.empty();
                            for (var i = 0; i < _pages ; i++)
                                sel.append('<option value="' + i + '">第 ' + (i + 1) + '/' + _pages + ' 页</option>');
                            _isFirst = 1;
                        }

                        if (typeof callback == "function") {
                            callback(index);
                        }

                        return;
                    }

                    Util.Alert("已到达末页");

                });
        }
        // 初始化
        $(function () {
            _hisgoryId = getHistoryId();
            if (_hisgoryId != 0) {
                $.get("service.aspx", $.param({ action: "chapter_getname", chapterid: _hisgoryId }), function (data) {
                    var res = eval("(" + data + ")");
                    if (res.code == 0) {
                        $("#lnkHis").html("阅读进度：" + res.result.Name);
                        $("#lnkHis").attr("href", "content.aspx?articleno=" + _articleno + "&chapterid=" + _hisgoryId + "&his=1");
                        $("#lnkHis").closest("ul").show();
                    }
                });
            }
            // 加载第一页
            getDirectory(0);

            // 分页选择框事件
            $("#selPage").change(function () {
                var index = parseInt($(this).val());
                getDirectory(index);
            });

            // 绑定其他分页按钮事件
            $(".paging").find("li[data-value]").click(function () {

                var value = parseInt($(this).attr("data-value"));
                var index = 0;

                switch (value) {
                    case 2:
                        index = _index - 1;
                        if (index < 0) index = 0;
                        break;
                    case 3:
                        index = _index + 1;
                        if (index > _pages - 1) index = _pages - 1;
                        break;
                    case 4: index = _pages - 1; break;
                }
                getDirectory(index, function (idx) {
                    $("#selPage").val(idx);
                });

            });

            //倒序、正序排列
            $("#sort").click(function () {

                var value = parseInt($(this).attr("data-value"));
                _sort = value;
                $(this).attr("data-value", _sort == 1 ? 0 : 1);
                $(this).html(_sort == 1 ? "↓正序排列" : "↑倒序排列");
                getDirectory(0, function (idx) {
                    $("#selPage").val(idx);
                });

            });
		
        });
			function checkVipReader(_chapterno){
				$.get("${encodeURL("/checkviporder")}", $.param({chapterno: _chapterno })
				            , function (res) {
						var link = "/reader/"+Math.floor(_articleno/1000)+"/"+_articleno+"/"+_chapterno+".html";
            var viplink = "/vip/"+Math.floor(_articleno/1000)+"/"+_articleno+"/"+_chapterno+".html";
				    if (res.code == 0) {
								location.href = link;
				     } else {
				       location.href = viplink;
				     }
				    });
			}
    </script>

</#macro>
