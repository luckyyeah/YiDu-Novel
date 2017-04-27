<#include "usercommon.ftl"/>

<#macro titleContent>  
<title>章节管理|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  


<#macro content>
<div class="layout grid-s160m0e190 channel-netnovel">
    <div class="col-main"><div class="main-wrap channel-netnovel-main">
    <section>
    <@s.form action="chapterEdit" validate="true" method="post">
    <script type="text/javascript" src="${contextPath}/themes/${themeName}/pc/js/formcommon.js"></script>
    <center>
        <span id="ErrorList"><@s.actionerror /> <@s.actionmessage /></span>
    </center>
    <@s.hidden name="articleno" />
    <@s.hidden name="chapterno" />
    <table class="grid" align="center" width="500">
      <tbody>
      <caption>章节信息</caption>
        <tr>
          <td class="odd" width="25%">章节名</td>
          <td class="even">
             <@s.textfield name="chaptername" cssStyle="160px;"  cssClass="text" size="25" maxlength="32" />
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">类型</td>
          <td class="even">
                <@s.radio
                    id="vip"
                    name="vip"
                    list="collections['collectionProperties.chapter.isvip']"
                    />
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">内容</td>
          <td class="even">
              <@s.textarea name="content" cssStyle="160px;" cssClass="text" cols="74" rows="20"/> 
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">定时发布时间</td>
          <td class="even">
              <@s.textfield name="publishtimeStr" cssStyle="160px;"  cssClass="text" size="25" maxlength="32" />
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
    </@s.form>
    </section>
    </div>
</div>
<#if menuContent?exists>  
    <@menuContent/>  
</#if>
</#macro>