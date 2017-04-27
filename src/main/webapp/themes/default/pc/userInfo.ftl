<#include "user/usercommon.ftl"/>

<#macro titleContent>  
<title>用户基本信息|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro content>
<div class="layout grid-s160m0e190 channel-netnovel">
<#assign categorymap = categoryData?eval>
    <div class="col-main"><div class="main-wrap channel-netnovel-main">
    <section>
    <table class="grid" align="center" width="500">
      <tbody>
      <caption>用户基本信息</caption>
        <tr>
          <td class="odd" width="25%">用户ID</td>
          <td class="even">
              ${user.loginid}
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">用户类别</td>
          <td class="even">
              <#if user.type??> <#if user.type==20>作家<#elseif user.type==10>普通用户<#else>管理员</#if><#else>未公开</#if>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">性别</td>
          <td class="even">
              <#if user.sex??> <#if user.sex==1>男<#else>女</#if><#else>未公开</#if>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">邮箱</td>
          <td class="even">
             <#if user.email??>${user.email}<#else>未公开</#if>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">QQ</td>
          <td class="even">
              <#if user.qq??>${user.qq}<#else>未公开</#if>
          </td>
        </tr>
        <tr>
          <td class="odd" width="25%">手&nbsp;机&nbsp;号</td>
          <td class="even">
            <#if user.mobileno??>${user.mobileno}<#else>未公开</#if>
          </td>
        </tr>
    </tbody>
    </table>
    </section>
    </div>
</div>
<#if menuContent?exists>  
    <@menuContent/>  
</#if>
</#macro>