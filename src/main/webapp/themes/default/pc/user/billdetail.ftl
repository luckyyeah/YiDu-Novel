<#include "usercommon.ftl"/>

<#macro titleContent>  
<title>我的账单|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro content>
<div class="layout grid-s160m0e190 channel-netnovel">
    <div class="col-main"><div class="main-wrap channel-netnovel-main">
    <section>
    <@s.form action="useredit" validate="true" method="post">
    <script type="text/javascript" src="${contextPath}/themes/${themeName}/pc/js/formcommon.js"></script>
    <center>
        <span id="ErrorList"><@s.fielderror /> <@s.actionerror /> <@s.actionmessage /></span>
    </center>
    <table class="grid" align="center" width="500">
      <tbody>
      <caption>用户基本信息</caption>
        <tr>
          <td class="odd psd" width="25%">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</td>
          <td class="even">
              <@s.password name="password" id = "password" cssStyle="160px;"  cssClass="text password"  size="25" maxlength="32"/>
          </td>
        </tr>
        <tr>
          <td class="odd again" width="25%">再次输入</td>
          <td class="even">
              <@s.password name="repassword" id = "repassword" cssStyle="160px;"  cssClass="text password"  size="25" maxlength="32"/>
          </td>
        </tr>
        <tr>
          <td class="odd emails" width="25%">电子邮箱</td>
          <td class="even">
              <@s.textfield name="email" id = "email" cssStyle="160px;"  cssClass="text"  size="25" maxlength="60"/>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">QQ</td>
          <td class="even">
              <@s.textfield name="qq" id = "qq" cssStyle="160px;"  cssClass="text"  size="25" maxlength="15"/>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">手&nbsp;机&nbsp;号</td>
          <td class="even">
              <@s.textfield name="mobileno" id = "mobileno" cssStyle="160px;"  cssClass="text"  size="25" maxlength="11"/>
          </td>
        </tr>
    </tbody>
    </table>
    <table class="grid" align="center" width="500">
      <tbody>
      <caption>以下信息申请作者前必须如实填写，一旦填写无法修改</caption>
        <tr>
          <td class="odd" width="25%">真实姓名</td>
          <td class="even">
           <#if realname?? && realname!=''>${realname}<#else>
              <@s.textfield name="realname" id = "realname" cssStyle="160px;"  cssClass="text"  size="25" maxlength="10"/>
           </#if>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">笔&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</td>
          <td class="even">
          <#if username??  && username!=''>${username}<#else>
              <@s.textfield name="username" id = "username" cssStyle="160px;"  cssClass="text"  size="25" maxlength="50"/>
          </#if>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
          <td class="even">
          <#if sex??> <#if sex==1>男<#else>女</#if><#else>
              <@s.radio  name="sex"  list="#@java.util.LinkedHashMap@{1:'男',2:'女'}" />
          </#if>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">身份证号</td>
          <td class="even">
              <#if id??  && id!=''>${id}<#else>
              <@s.textfield name="id" id = "id" cssStyle="160px;"  cssClass="text"  size="25" maxlength="18"/>
              </#if>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">开&nbsp;户&nbsp;行</td>
          <td class="even">
              <#if branch??  && branch!=''>${branch}<#else>
              <@s.textfield name="branch" id = "branch" cssStyle="160px;"  cssClass="text"  size="25" maxlength="50"/>
              </#if>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">银行帐号</td>
          <td class="even">
              <#if bankno?? && bankno!=''>${bankno}<#else>
              <@s.textfield name="bankno" id = "bankno" cssStyle="160px;"  cssClass="text"  size="25" maxlength="20"/>
              </#if>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">支付宝帐号</td>
          <td class="even">
              <#if alipayacount?? && alipayacount!=''>${alipayacount}<#else>
              <@s.textfield name="alipayacount" id = "alipayacount" cssStyle="160px;"  cssClass="text"  size="25" maxlength="50"/>
              </#if>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">&nbsp;
          <td class="even">
              <@s.submit method="save" cssClass="submit" name="submit" theme="simple" cssStyle="cursor:pointer;" value=" 提 交 "/>
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