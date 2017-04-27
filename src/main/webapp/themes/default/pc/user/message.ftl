<#include "usercommon.ftl"/>

<#macro titleContent>  
<title>我的消息|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro content>
<div class="layout grid-s160m0e190 channel-netnovel">
    <div class="col-main"><div class="main-wrap channel-netnovel-main">
    <section>
        <header class="netnovel-header">
            <h3>我的消息 &nbsp;&nbsp;- 您共有 ${messageList.size()}条消息。</h3>
        </header>
        <form action="" method="post" name="checkform" id="checkform" onSubmit="return check_confirm();">
            <table class="mygrid" width="100%" align="center" style="font-size:12px">
              <tbody>
              <tr align="center">
                    <th width="5%">
                        <input type="checkbox" id="checkall" name="checkall" value="checkall" onclick="javascript: for (var i=0;i<this.form.elements.length;i++){ if (this.form.elements[i].name != 'checkkall') this.form.elements[i].checked = form.checkall.checked; }"></th>
                    <th width="21%">来自用户</th>
                    <th width="30%">标题</th>
                    <th width="30%">内容概要</th>
                    <th width="7%">操作</th>
              </tr>
              <#list messageList as message>
              <tr>
                    <td class="odd" align="center">
                    <input type="checkbox" id="checkid[]" name="checkid[]" value="${message.messageno?c}">&nbsp;<span class="hottext"><#if message.isread?? && message.isread>未读</#if></span> </td>
                    <td class="even">${message.loginid}</td>
                    <td class="odd">${message.title}</td>
                    <td class="even">${message.content}</td>
                    <td class="even" align="center"><a href="javascript:if(confirm('确实要删除这条消息么？')) document.location='${encodeURL("/user/message!delete?messageno=${message.messageno?c}")}';">移除</a></td>
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