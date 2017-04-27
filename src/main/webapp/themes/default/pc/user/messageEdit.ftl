<#include "usercommon.ftl"/>

<#macro titleContent>  
<title>发送消息|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro content>
<@s.form action="messageEdit" method="post" validate="true">
    <center>
        <span id="ErrorList"><@s.fielderror /> <@s.actionerror /></span>
    </center>
    <table class="grid" align="center" width="400">
      <caption>
        发送消息给管理员
      </caption>
      <tbody>
        <tr>
          <td sytle="text-align: right; padding-left: 20px;" class="odd" width="25%">标&nbsp;&nbsp;题:</td>
          <td class="even">
                <@s.textfield name="title" id = "title" cssStyle="width:150px; height:20px;" maxlength="32"/>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">内&nbsp;&nbsp;容:</td>
          <td class="even">
            <@s.textarea name="content" cssStyle="160px;"  cssClass="text"  cols="74" rows="8"/>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">&nbsp;
          </td>
          <td class="even">
              <@s.submit method="add" cssClass="submit" name="submit" theme="simple" cssStyle="cursor:pointer;" value=" 提 交 "/>
          </td>
        </tr>
      </tbody>
    </table>
</@s.form>
</#macro>