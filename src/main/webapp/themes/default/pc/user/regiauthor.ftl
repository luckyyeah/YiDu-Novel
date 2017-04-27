<#include "usercommon.ftl"/>

<#macro titleContent>  
<title>申请作者|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro content>
<div class="layout grid-s160m0e190 channel-netnovel">
    <div class="col-main"><div class="main-wrap channel-netnovel-main">
    <section>
    <@s.form action="regiauthor" validate="true" method="post" enctype="multipart/form-data">
    <script type="text/javascript" src="${contextPath}/themes/${themeName}/pc/js/formcommon.js"></script>
    <center>
        <span id="ErrorList"><@s.fielderror /> <@s.actionerror /> <@s.actionmessage /></span>
    </center>
    
    <table class="grid" align="center" width="600px">
      <tbody>
      <caption>作品审核条件：</caption>
        <tr>
          <td  width="100%">
          首先提交如下样稿<br>
          样稿文件格式：TXT文件或者word文档<br>
长篇作品：<br>
累计发表3万字以上（累计所有章节字数）<br>
短篇作品：<br>
字数不限，但必须完结。<br>
作品审核时间：<br>
48小时内审核。请您耐心等待，节假日审核时间顺延。<br>
超过48时没有审核，请发站内信申请审核。<br>
            </td>
        </tr>
        <tr>
          <td>
             <@s.file name="sample"></@s.file> 
          </td>
        </tr>
    </tbody>
    </table>
    <table class="grid" align="center" width="600px">
      <tbody>
      <caption>章程，自行修改</caption>
        <tr>
          <td  colspan ="2">章程</td>
        </tr>
         <tr>
          <td class="odd" width="25%">&nbsp;
          <td class="even">
              <@s.submit method="register" cssClass="submit" name="submit" theme="simple" cssStyle="cursor:pointer;" value=" 提 交 "/>
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