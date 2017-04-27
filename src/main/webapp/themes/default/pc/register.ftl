<#include "common.ftl"/>

<#macro titleContent>  
<title>用户注册|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro content>

<@s.form action="register" validate="true" method="post" onsubmit="$('#register_submit').css('cursor','wait');">
<script type="text/javascript" src="${contextPath}/themes/${themeName}/pc/js/formcommon.js"></script>
    <center>
        <span id="ErrorList"><@s.fielderror /> <@s.actionerror /></span>
    </center>
    <table class="grid" align="center" width="500">
      <caption>
     新用户注册
      </caption>
      <tbody>
         <tr>
          <td class="odd userN" width="25%">用&nbsp;&nbsp;户&nbsp;&nbsp;名:</td>
          <td class="even">
              <@s.textfield name="loginid" id = "loginid" cssStyle="160px;"  cssClass="text username"  size="25" maxlength="32"/>
          </td>
        </tr>
        <tr>
          <td class="odd psd" width="25%">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
          <td class="even">
              <@s.password name="password" id = "password" cssStyle="160px;"  cssClass="text password"  size="25" maxlength="32"/>
          </td>
        </tr>
        <tr>
          <td class="odd again" width="25%">再次输入：</td>
          <td class="even">
              <@s.password name="repassword" id = "repassword" cssStyle="160px;"  cssClass="text password"  size="25" maxlength="32"/>
          </td>
        </tr>
        <tr>
          <td class="odd emails" width="25%">电子邮箱：</td>
          <td class="even">
              <@s.textfield name="email" id = "email" cssStyle="160px;"  cssClass="text"  size="25" maxlength="60"/>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">QQ：</td>
          <td class="even">
              <@s.textfield name="qq" id = "qq" cssStyle="160px;"  cssClass="text"  size="25" maxlength="15"/>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">&nbsp;
          <td class="even">
              <@s.submit method="register" cssClass="submit" name="submit" theme="simple" cssStyle="cursor:pointer;"  value=" 提 交 "/>
          </td>
        </tr>
      </tbody>
    </table>
    <s:hidden name="backUrl"/>
</@s.form>
</#macro>