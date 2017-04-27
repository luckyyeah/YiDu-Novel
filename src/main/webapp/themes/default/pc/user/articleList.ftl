<#include "usercommon.ftl"/>

<#macro titleContent>  
<title>我的小说|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro content>
<div class="layout grid-s160m0e190 channel-netnovel">
    <div class="col-main"><div class="main-wrap channel-netnovel-main">
    <section>
        <header class="netnovel-header">
            <h3>我的小说 &nbsp;&nbsp;- 您共有 ${articleList.size()}本小说。  &nbsp;&nbsp;<a href='${encodeURL("/user/articleEdit")}' ><font color=red>添加小说</font></a></h3>
        </header>
        <form action="" method="post" name="checkform" id="checkform" onSubmit="return check_confirm();">
            <table class="mygrid" width="100%" align="center" style="font-size:12px">
              <tbody>
              <tr align="center">
                    <th width="20%">小说名</th>
                    <th width="10%">小说类型</th>
                    <th width="45%">最新章节</th>
                    <th width="25%">操作</th>
              </tr>
              <#list articleList as article>
              <tr>
                    <td class="even">${article.articlename}</td>
                    <td class="odd">${article.categoryStr}</td>
                    <td class="odd"><#if article.lastchapter??>${article.lastchapter}</#if></td>
                    <td class="even" align="center">
                        <a href='${encodeURL("/user/articleEdit?articleno=${article.articleno?c}")}' >编辑</a>
                        <a href='${encodeURL("/user/articleEdit?articleno=${article.articleno?c}")}' >申请上架</a>
                        <a href='#' >评论管理</a>
                        <a href='${encodeURL("/user/chapterList?articleno=${article.articleno?c}")}'>章节管理</a>
                        <a href="javascript:if(confirm('确实要删除这本小说么？')) document.location='${encodeURL("/user/articleList!delete?articleno=${article.articleno?c}")}';">删除</a>
                    </td>
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