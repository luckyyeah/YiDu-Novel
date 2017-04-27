<#include "usercommon.ftl"/>

<#macro titleContent>  
<title>我的书架|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro content>
<div class="layout grid-s160m0e190 channel-netnovel">
    <div class="col-main"><div class="main-wrap channel-netnovel-main">
    <section>
        <header class="netnovel-header">
            <h3>我的书架 &nbsp;&nbsp;- 您的书架可收藏 ${maxBookcaseNum} 本，已收藏${bookcaseNum} 本。</h3>
        </header>
        <form action="" method="post" name="checkform" id="checkform" onSubmit="return check_confirm();">
            <table class="mygrid" width="100%" align="center" style="font-size:12px">
              <tbody>
              <tr align="center">
                    <th width="5%">
                        <input type="checkbox" id="checkall" name="checkall" value="checkall" onclick="javascript: for (var i=0;i<this.form.elements.length;i++){ if (this.form.elements[i].name != 'checkkall') this.form.elements[i].checked = form.checkall.checked; }"></th>
                    <th width="21%">小说名</th>
                    <th width="30%">最新章节</th>
                    <th width="30%">书签</th>
                    <th width="7%">操作</th>
              </tr>
              <#list bookcaseList as bookcase>
              <tr>
                    <td class="odd" align="center">
                    <input type="checkbox" id="checkid[]" name="checkid[]" value="${bookcase.bookcaseno}">&nbsp;<span class="hottext"><#if bookcase.chapterno?? && bookcase.chapterno!=bookcase.lastchapterno>新</#if></span> </td>
                    <td class="even"><a href="${bookcase.infoUrl}" target="_blank">${bookcase.articlename}</a></td>
                    <td class="odd"><a href="${bookcase.lastChapterUrl}" target="_blank">${bookcase.lastchapter}</a>
                    </td>
                    <td class="even"><#if bookcase.chapterno??><a href="${bookcase.bookmarkUrl}" target="_blank">${bookcase.chaptername}</a></#if></td>
                    <td class="even" align="center"><a href="javascript:if(confirm('确实要将本书移出书架么？')) document.location='${encodeURL("/user/bookcase!delete?bookcaseno=${bookcase.bookcaseno?c}")}';">移除</a></td>
             </tr>
             </#list>
            </tbody>
          </table>
        </form>
    </section>
    </div>
</div>
<#if menuContent?exists>  
    <@menuContent/>  
</#if>
</#macro>