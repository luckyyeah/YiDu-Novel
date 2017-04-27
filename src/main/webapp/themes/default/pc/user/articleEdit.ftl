<#include "usercommon.ftl"/>

<#macro titleContent>  
<title>小说管理|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  


<#macro content>
<div class="layout grid-s160m0e190 channel-netnovel">
    <div class="col-main"><div class="main-wrap channel-netnovel-main">
    <section>
    <@s.form action="articleEdit" validate="true" method="post" enctype="multipart/form-data">
    <script type="text/javascript" src="${contextPath}/themes/${themeName}/pc/js/formcommon.js"></script>
    <center>
        <span id="ErrorList"><@s.actionerror /> <@s.actionmessage /></span>
    </center>
    <@s.hidden name="articleno" />
    <table class="grid" align="center" width="500">
      <tbody>
      <caption>小说信息</caption>
        <#if articleno != 0 >
        <tr>
          <td class="odd" width="25%">小说编号</td>
          <td class="even">
              ${articleno?c}
          </td>
        </tr>
        </#if>
        <tr>
          <td class="odd" width="25%">小说名</td>
          <td class="even">
              <@s.textfield name="articlename" cssStyle="160px;"  cssClass="text"  size="25" maxlength="50"/>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">种别</td>
          <td class="even">
                <@s.select
                    name="category"
                    list="collections['collectionProperties.article.category']"
                    />
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">关键字</td>
          <td class="even">
              <@s.textfield name="keywords"  cssStyle="160px;"  cssClass="text"   size="25" maxlength="100"/>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">状态</td>
          <td class="even">
              <@s.radio list="collections['collectionProperties.article.fullflag']" name="fullflag" ></@s.radio>
          </td>
        </tr>
        <tr>
            <td class="odd" width="25%">授权级别</td>
            <td class="even">
                <@s.radio list="collections['collectionProperties.article.permission']" name="permission" ></@s.radio>
            </td>
        </tr>
        <tr>
            <td class="odd" width="25%">是否首发</td>
            <td class="even">
                <@s.radio list="collections['collectionProperties.article.firstflag']" name="firstflag" ></@s.radio>
            </td>
        </tr>
        <tr>
          <td class="odd" width="25%">小说简介</td>
          <td class="even">
              <@s.textarea name="intro" cssStyle="160px;"  cssClass="text"  cols="74" rows="8"/> 
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">小说封面</td>
          <td class="even">
              <@s.file name="articlespic"></@s.file> 
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">&nbsp;
          <td class="even">
              <@s.submit method="save" cssClass="submit" name="submit" theme="simple" cssStyle="cursor:pointer;" value=" 保 存 "/>
          </td>
        </tr>
    </tbody>
    </table>
    <#if articleno != 0 >
    <table class="grid" align="center" width="500">
      <tbody>
      <caption>统计信息</caption>
        <tr>
          <td class="odd" width="25%">日浏览次数</td>
          <td class="even">
           ${dayvisit}
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">周浏览次数</td>
          <td class="even">
           ${weekvisit}
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">月浏览次数</td>
          <td class="even">
           ${monthvisit}
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">总浏览次数</td>
          <td class="even">
           ${allvisit}
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">日推荐次数</td>
          <td class="even">
           ${dayvote}
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">周推荐次数</td>
          <td class="even">
           ${weekvote}
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">月推荐次数</td>
          <td class="even">
           ${monthvote}
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">总推荐次数</td>
          <td class="even">
           ${allvote}
          </td>
        </tr>
      </tbody>
    </table>
    </#if>
    </@s.form>
    </section>
    </div>
</div>
<#if menuContent?exists>  
    <@menuContent/>  
</#if>
</#macro>