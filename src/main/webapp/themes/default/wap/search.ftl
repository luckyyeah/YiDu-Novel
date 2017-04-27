<#include "base.ftl"/>

<#macro titleContent>  
<title>小说搜索</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>

<#macro content>
    <div class="title m10">筛选条件<a id="moreTiaojian" class="more" data-value="0">展开</a></div>
    <div id="divTiaojian" class="box m2" style="padding:10px 0; display:none; ">
        <ul class="tiaojian">
            <li>分类：</li>
            <li>
                <a href="javascript:;" data-value="0" data-type="0" class="curr">全部</a>
                <a href="javascript:;" data-value="1" data-type="0">玄幻</a>
                <a href="javascript:;" data-value="2" data-type="0">武侠</a>
                <a href="javascript:;" data-value="3" data-type="0">都市</a>
                <a href="javascript:;" data-value="4" data-type="0">历史</a>
                <a href="javascript:;" data-value="5" data-type="0">推理</a>
                <a href="javascript:;" data-value="6" data-type="0">网游</a>
                <a href="javascript:;" data-value="7" data-type="0">科幻</a>
                <a href="javascript:;" data-value="8" data-type="0">恐怖</a>
                <a href="javascript:;" data-value="9" data-type="0">散文</a>
                <a href="javascript:;" data-value="11" data-type="0">其他</a>
            </li>
        </ul>
        <ul class="tiaojian">
            <li>排序：</li>
            <li>
                <a href="javascript:;" data-value="1" data-type="3" class="curr">更新</a>
                <a href="javascript:;" data-value="2" data-type="3">点击</a>
                <a href="javascript:;" data-value="3" data-type="3">推荐</a>
                <a href="javascript:;" data-value="4" data-type="3">字数</a>
            </li>
        </ul>
    </div>

    <div class="title m10">
        <div id="divCount" style="display:none;">共<span id="spnSearchCount">0</span>条记录</div>
        <div id="divHot">热门推荐</div>
        <div id="divStatus" style=" display:none; position:absolute; top:0; right:5%; font-weight:normal;"><input id="chStatus" type="checkbox" value="1" /><label for="chStatus">显示完结作品</label></div>
    </div>
    <div class="m2">
        <div id="books"><div class="loading2">正在加载.....</div></div>
        <div class="loading2" style="display:none;">暂无任何记录</div>
        <div><a id="lnkMore" class="loading" onclick="getNextPage(this);" style="display:none;">加载更多</a></div>
    </div>
</#macro>

<#macro customizefooter>

 <script type="text/javascript">

        var _sort = 1;
        var _index = 0;
        var _size = 10;
        var _type = 0;
        var _status = 0;
        <#if key??>
        var _keyword = "${key}";
        <#else>
        var _keyword = "";
        </#if>

        var search = function (callback) {

            _keyword = $("#txtKeyword").val();
            if (_keyword == $("#txtKeyword").attr("data-text"))
                _keyword = "";
            if (_index == 0)
                Util.Loading();
            $.get("${encodeURL("/ajaxService")}", $.param({ action: "search", key: _keyword, category: _type, sort: _sort, status: _status, index: _index, size: _size })
                , function (res) {
                    Util.LoadingClear();
                    if (res.total == 0) {
                        $(".loading2").show();
                        $("#books").html("");
                        $(".loading").hide();
                        $("#spnSearchCount").html(0);
                        return;
                    }

                    if (_keyword == "" && _type == 0 ) {
                        $("#divHot").show().siblings().hide();
                        $("#lnkMore").hide();
                    } else {
                        $("#divHot").hide().siblings().show();
                        $("#lnkMore").show();
                    }

                    $(".loading2").hide();
                    $("#spnSearchCount").html(res.total);
                    $("#lnkMore").removeClass("ldg").html(res.hasNext ? "加载更多" : "全部加载完毕");

                    var str = [];
                    for (var i = 0 ; i < res.items.length; i++) {
                        var item = res.items[i];
                        str.push('<div class="info i2">');
                        str.push('    <a href="/info/'+Math.floor(item.articleno/1000)+'/'+item.articleno+'.html">');
                        str.push('        <img src="'+item.imgUrl+'" />');
                        str.push('        <h3>' + item.articlename + '</h3>');
                        str.push('        <p>作者：' + item.author + '</p>');
                        str.push('        <p>分类：' + item.categoryStr + '</p>');
                        str.push('        <p>字数：' + Util.NumberFormat(item.size) + '(有' + Util.NumberFormat(item.allvisit) + '人阅读)</p>');
                        if (item.fullflag)
                            str.push('<em class="wj22"></em>');
                        str.push('    </a>');
                        str.push('</div>');
                    }
                    if (_index == 0)
                        $("#books").html("");
                    $("#books").append(str.join(""));
                    if (typeof callback == "function") callback();
                });
        }

        var init = function () {
            $(".tiaojian a").click(
                function () {
                    $(this).addClass("curr").siblings().removeClass("curr");
                    var type = parseInt($(this).attr("data-type"));
                    switch (type) {
                        case 0:
                            var t2 = parseInt($(this).attr("data-value"));
                            if (_type == t2) {
                                _type = 0;
                                $(this).removeClass("curr");
                            } else {
                                _type = t2;
                            }
                            break;
                        case 3:
                            var s2 = parseInt($(this).attr("data-value"));
                            if (_sort == s2) {
                                _sort = 1;
                                $(this).removeClass("curr");
                            } else {
                                _sort = s2;
                            }
                            break;
                    }
                    _index = 0;
                    $("#books").html("");
                    search();
                });
            $("#chStatus").click(function () {
                _status = parseInt($(this).val());
                _index = 0;
                $("#books").html("");
                search();
                if (_status == 1) $(this).val(0);
                if (_status == 0) $(this).val(1);

            });
        }

        var getNextPage = function (elem) {
            _index++;
            $(elem).addClass("ldg").html("正在加载.....");
            search();
        }

        $(function () {

            $("#moreTiaojian").click(function () {
                var value = $(this).attr("data-value");
                if (value == "0") {
                    $(this).html("收起");
                    $(this).attr("data-value", 1);
                    $("#divTiaojian").stop().show(100);
                } else {
                    $(this).html("展开");
                    $(this).attr("data-value", 0);
                    $("#divTiaojian").stop().hide(100);
                }
            });

            $("#lnkSearch").click(function () {
                $("#books").html("");
                _index = 0;
                search();
            });

            init();
            search();
        });
    </script>

</#macro>

