<#include "common.ftl"/>

<#macro titleContent>  
<title>用户登录|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro content>
<@s.form action="login" method="post" validate="true">
    <center>
        <span id="ErrorList"><@s.fielderror /> <@s.actionerror /></span>
    </center>
    <table class="grid" align="center" width="400">
      <caption>
        登陆${getText("label.system.name")}
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
          <td width="25%"></td>
          <td class="even">
            <@s.checkbox name="useCookie" fieldValue="true" theme="simple"  value="true" />下次自动登录
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">&nbsp;
          </td>
          <td class="even">
              <@s.submit method="login" cssClass="submit" name="submit" theme="simple" cssStyle="cursor:pointer;" value=" 提 交 "/>
              <span class="pl10">没有账号？<a href="${contextPath}/register">注册一个吧!</a></span>
          </td>
        </tr>
      </tbody>
    </table>
</@s.form>
</#macro>