<#include "base.ftl"/>

<#macro titleContent>  
<title>${chapter.articlename}-${chapter.chaptername}</title>
<meta name="keywords" content="${chapter.chaptername},${chapter.articlename}最新章节,${chapter.articlename}TXT下载,${chapter.articlename}无广告,${getText("label.system.name")}" />
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro customizetop>
    <div class="top3 readerheader">
        <ul>
            <li><a  href="/"></a></li>
            <li><a class="button blue r3" href="javascript:setShelf();">加入书架</a></li>
            <li><a class="button blue r3 payfont" href="/user/centerpay">充值</a></li>
        </ul>
    </div>
</#macro>

<#macro content>
    <div class="mainnav" style="font-size: 22px ;padding: 10px 12px;word-wrap: break-word;word-break: break-word;line-height: 1.562;">
       <strong class="l jieqi_title">${chapter.chaptername}</strong>
        <div class="mainContenr"   id="content">
            <#if chapter.content??>${chapter.content}</#if>
        </div>
    </div>
    <script src="${contextPath}/themes/${themeName}/wap/js/lib/jquery.cookie.js"></script>
    <script language="JavaScript" type="text/JavaScript"> 
    	  var _articleno = parseInt(${articleno?c});
    	  var _userid = 0;
        <#if loginUser?? >
            _userid = parseInt(${loginUser.userno?c});
        </#if>
        $(document).ready(function(){
            replaceWord();
            saveHistory();
        })
    
    function saveHistory(){
            var readhistory = $.cookie("mobilereadhistory");
           if(! readhistory ){
                readhistory = new Array();
           }else{
                readhistory = JSON.parse(readhistory);
           }
           var readObject = new Object();
           readObject.chapterno = ${chapter.chapterno?c};
           readObject.articleno = ${chapter.articleno?c};
           readObject.chaptername = "${chapter.chaptername}";
           readObject.articlename = "${chapter.articlename}";
           readObject.imgUrl = "${article.imgUrl}";
           var index = readObject.articleno.in_array(readhistory);
           if(index != -1){
                readhistory.splice(index,1);
           }
           readhistory.splice(0,0,readObject);
           if(readhistory.length > 10 ){
                readhistory.splice(9,readhistory.length - 10);
           }
           $.cookie("mobilereadhistory",JSON.stringify(readhistory),{path:'/' ,expires: 365});
    
    }
    
    function replaceWord(){
        var str = document.getElementById("content").innerHTML;//这里是整个页面代码 ,也可以指定id
        str = str.replace(/\<script[\s\S]+?\<\/script\>/gi, "");
        str = str.replace(/\<styltyp[\s\S]+?\<\/styl\>/gi, "");
        str = str.replace(/\<style[\s\S]+?\<\/style\>/gi, "");
        str = str.replace(/\<a[\s\S].+?\<\/a\>/gi, "");
        str = str.replace(/Www.+?ggyy\.net/gi, "");
        str = str.replace(/Www.+?Com/gi, "");
        str = str.replace(/Www.+?net/gi, "");
        str = str.replace(/Www.+?cc/gi, "");
        str = str.replace(/&lt;br.+?&gt;/gi, "<br />");
        str = str.replace(/&amp;hllp;/gi, "&hellip;").replace(/&amp;ldqo;/gi,
                "&ldquo;").replace(/ldqo/gi, "ldquo").replace(/&amp;rdqo;/gi,
                "&rdquo;").replace(/&amp;dash;/gi, "&mdash;");
        document.getElementById("content").innerHTML = str;
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




            temp.html("添加中...");
            $.get("${encodeURL("/ajaxService")}", $.param({ action: "addbookcase", articleno: _articleno })
                , function (res) {
                    if (res.code == 0) {
                        Util.Alert("成功加入书架");
                        return;
                    }
                    $("#lnkShelf").html("加入书架");
                    Util.Alert("加入书架时发生了错误");
                });

        }    
    </script>


</#macro>
<#macro customizefooter>
    <div class="top3 readerfooter">
        <ul>
            <li><a class="button blue r3" href="<#if chapter.preChapterno ==0>${article.url}<#else>${chapter.preChapterUrl}</#if>">上一章</a></li>
            <li><a class="button blue r3" href="/info/${(articleno/100)?int}/${articleno?c}.html">章节目录</a></li>
            <li><a class="button blue r3" href="<#if chapter.nextChapterno ==0>${article.url}<#else>${chapter.nextChapterUrl}</#if>">下一章</a></li>
        </ul>
    </div>
</#macro>