<#include "base.ftl"/>

<#macro titleContent>  
<title>我的书架,阅读历史,订阅记录</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>

<#macro customizetop>
<script src="${contextPath}/themes/${themeName}/wap/js/lib/jquery.cookie.js"></script>
</#macro>


<#macro content>
    <div class="m10">
        <div class="tab t2">
            <ul><li class="animbg"></li></ul>
            <ul id="types">
                <li data-value="1" data-index="0" class="curr">我的书架</li>
                <li data-value="2" data-index="1" >阅读历史</li>
            </ul>
        </div>
        <div id="sortBar" class="bar02">
            <a data-sort="1" class="curr">添加时间</a>
            <a data-sort="2">更新时间</a>
            <a data-sort="3">书名</a>
            <a id="lnkEdit">编辑</a>
        </div>
        <div id="shelfbox" class="shelf">
            <ul id="shelfs"></ul>
        </div>
        <div id="nodata" class="box m2" style="display:none;">
            <div class="loading2">暂无记录</div>
        </div>
    </div>
</#macro>


<#macro customizefooter>
    <script type="text/javascript">
    
        function imageError(element,articleno) {
            element.onerror='';
            element.src='/cover/nocover.jpg';
        }
    
        <#if loginUser??>
        var _userid = parseInt("${loginUser.userno}");
        <#else>
        var _userid = parseInt(0);
        </#if>
        var _showHis = parseInt(0);
        var _bookCases = [];
        var _booksToDelete = [];
        var _isFlag = 0;
        var _his = [];
        var _hisToDelete = [];
        var _eType = 1;
        var _sort = 1;

        // 获取书架信息
        var getCases = function () {

            if (_userid == 0) {
                Site.showLoginbox(function (uid) {
                    _userid = uid;
                    getCases();
                });
                return;
            }

            _eType = 1;

            if (_bookCases.length > 0) {
                show(_bookCases);
                $("#spnCount").html(_bookCases.length);
                return;
            }

            Util.Loading();
            $.get("ajaxService", $.param({ action: "bookcase", sort:_sort })
                , function (res) {
                    if (res.code == 0) {
                        _bookCases = res.items;
                        show(_bookCases);
                    }
                    Util.LoadingClear();
                });
        }
        // 显示
        var show = function (items) {

            if (items == null)
                items = [];

            if (items.length == 0) {
                $("#shelfbox").hide();
                $("#nodata").show();
                return;
            }

            $("#shelfbox").show();
            $("#nodata").hide();
            var htm = [];

            for (var i = 0; i < items.length; i++) {

                var item = items[i];
                var de = _isFlag == 0 ? "none" : "block";
                htm.push('<li>');
                htm.push('  <a href="/info/'+Math.floor(item.articleno/1000)+'/'+item.articleno+'.html">');
                htm.push('      <div class="con">');
                htm.push('          <h2>' + item.articlename + '</h2>');
                htm.push('          <img src="'+item.imgUrl+'"  onerror="imageError(this,'+item.articleno+')" />');
                htm.push('          <em class="del" style="display:' + de + '" onclick="setDeleteId(' + item.bookcaseno + ');return false;"></em>');
                if (item.fullflag)
                    htm.push('<em class="wan"></em>');
                htm.push('      </div>');
                htm.push('  </a>');
                htm.push('</li>');

            }
            $("#shelfs").html(htm.join(""));

        }
        // 设置删除ID
        var setDeleteId = function (id) {

            Site.showConfirmbox("确定删除吗？", function (res) {
                if (res == 1) {
                    if (_eType == 1) {
                        _booksToDelete.push(id);
                        for (var i = 0; i < _bookCases.length; i++) {
                            if (_bookCases[i].bookcaseno == id) {
                                _bookCases = _bookCases.remove(i);
                                show(_bookCases);
                                break;
                            }
                        }
                        return;
                    }
                    _hisToDelete.push(id);
                    for (var i = 0; i < _his.length; i++) {
                        if (_his[i].id == id) {
                            _his = _his.remove(i);
                            show(sortHistory(_his));
                            break;
                        }
                    }
                }
            });
        }
        // 获取历史信息
        var getHistory = function () {

            _eType = 2;

            if (_his.length > 0) {
                show(sortHistory(_his));
                return;
            }
            Util.Loading();
            
             var readhistory = $.cookie("mobilereadhistory");
               if(! readhistory ){
                    readhistory = new Array();
               }else{
                    readhistory = JSON.parse(readhistory);
               }
              show(readhistory);
              Util.LoadingClear();
        }
       
        // 删除信息
        var del = function () {
            // 删除书架信息
            if (_eType == 1) {
                if (_booksToDelete.length != 0) {
                    $.get("ajaxService"
                        , $.param({ action: "deletebookcase", bookcasenos: _booksToDelete.join(",") })
                        , function (data) {
                            _booksToDelete = [];
                        });
                }
                return;
            }
        }

        var setTab = function (o) {
            var sel = $(o);
            var idx = parseInt(sel.attr("data-index"));
            _eType = parseInt(sel.attr("data-value"));
            _eType == 1 ? $("#sortBar").show() : $("#sortBar").hide();

            sel.closest("ul").siblings().find(".animbg").animate({ marginLeft: (50 * idx) + "%" }, 200, function () {
                sel.addClass("curr").siblings().removeClass("curr");
            });
            _bookCases = [];
            _eType == 1 ? getCases() : getHistory();
        }

        // 初始化
        $(function () {

            $("#lnkEdit").click(function () {
                var htm = $(this).html();
                if (htm == "编辑") {
                    _isFlag = 1;
                    $(".del").show();
                    $(this).html("完成");
                } else {
                    _isFlag = 0;
                    $(".del").hide();
                    $(this).html("编辑");
                    del();
                }
            });

            $("#selType").change(function () {
                _eType = parseInt($(this).val());
                _eType == 1 ? getCases() : getHistory();
            });

            $("a[data-sort]").click(function () {
                _sort = parseInt($(this).attr("data-sort"));
                $(this).addClass("curr").siblings("a[data-sort]").removeClass("curr");
                _bookCases = [];
                getCases();
            });

            $("#types").find("li").click(function () {
                var sel = this;
                if (_userid == 0) {
                    Site.showLoginbox(function (uid) {
                        _userid = uid;
                        setTab(sel);
                    });
                    return;
                }
                setTab(this);
            });

            if (_userid == 0) {
                $("#types li[data-value]").removeClass("curr");
                $("#types li[data-value=2]").addClass("curr")
                    .parent().siblings("ul").find(".animbg").css({ marginLeft: "50%" });
                $("#sortBar").hide();
                getHistory();
            } else {
                getCases();
                $("#sortBar").show();
            }
        });
        
    </script>

</#macro>
