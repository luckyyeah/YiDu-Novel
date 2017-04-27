<#include "usercommon.ftl"/>

<#macro titleContent>  
<title>${article.articlename}章节管理|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro content>
<div class="layout grid-s160m0e190 channel-netnovel">
    <div class="col-main"><div class="main-wrap channel-netnovel-main">
    <section>
        <header class="netnovel-header">
            <h3>${article.articlename} &nbsp;&nbsp;- 共有 ${chapterList.size()}章。  &nbsp;&nbsp;<a href='${encodeURL("/user/chapterEdit?articleno=${article.articleno?c}")}' ><font color=red>添加章节</font></a></h3>
        </header>
        <form action="" method="post" name="checkform" id="checkform" onSubmit="return check_confirm();">
            <table class="mygrid" width="100%" align="center" style="font-size:12px">
              <tbody>
              <tr align="center">
                    <colgroup>
                        <col width="240px">
                        <col width="240px">
                        <col width="240px">
                    </colgroup>
              </tr>
              <#list chapterList as chapter>
              <#if chapter_index % 3 ==0>
              <tr>
              </#if>
                    <td>
                        <a href="${encodeURL("/user/chapterEdit?chapterno=${chapter.chapterno?c}")}">${chapter.chaptername}</a>
                        <a href="javascript:if(confirm('确实要删除这条消息么？')) document.location='${encodeURL("/user/chapterList!delete?chapterno=${chapter.chapterno?c}")}'" style="color:red">删除</a>
                    </td>
              <#if chapter_index % 3 ==2>
              </tr>
              </#if>
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
