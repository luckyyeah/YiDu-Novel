<#include "common.ftl"/>

<#macro titleContent>  
<title>邮箱验证|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro content>
<@s.form action="emailValidate" method="post" validate="true">
    <center>
        <span id="ErrorList"><@s.fielderror /> <@s.actionerror /></span>
    </center>
    <table class="grid" align="center" width="400">
      <caption>
        ${getText("label.system.name")}邮箱验证，请输入用户名和登录密码
      </caption>
      <tbody>
        <tr>
          <td sytle="text-align: right; padding-left: 20px;" class="odd userN" width="25%">用&nbsp;&nbsp;户&nbsp;&nbsp;名:</td>
          <td class="even">
                <@s.textfield name="loginid" id = "loginid" cssStyle="width:150px; height:20px;" maxlength="32"/>
          </td>
        </tr>
        <tr>
          <td class="odd psd" width="25%">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
          <td class="even">
            <@s.password name="password" id="password" cssStyle="width:150px; height:20px;" maxlength="32"/>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">&nbsp;
          </td>
          <td class="even">
              <@s.submit method="emailValidate" cssClass="submit" name="submit" theme="simple" cssStyle="cursor:pointer;" value=" 验 证 "/>
          </td>
        </tr>
      </tbody>
    </table>
    <@s.hidden name="mailtoken"/>
</@s.form>
</#macro>