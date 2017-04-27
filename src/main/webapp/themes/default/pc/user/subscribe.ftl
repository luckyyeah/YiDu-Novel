<#include "usercommon.ftl"/>

<#macro titleContent>  
<title>我的订阅|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro content>
<div class="layout grid-s160m0e190 channel-netnovel">
    <div class="col-main"><div class="main-wrap channel-netnovel-main">
    <section>
        <header class="netnovel-headersubscribe ">
            <h3>我的订阅 &nbsp;&nbsp;- 您最大可以订阅  ${maxSubscribeNum} 本小说，现已订阅 ${subscribeNum} 本。<br>
            <span style="color:red">您订阅的小说如果更新了，我们会通过邮件的方式通知您，切记设置好邮件信息，否则无法使用该功能！</span></h3>
        </header>
        <form action="" method="post" name="checkform" id="checkform" onSubmit="return check_confirm();">
            <table class="mygrid" width="100%" align="center" style="font-size:12px">
              <tbody>
              <tr align="center">
                    <th width="10%">小说编号</th>
                    <th width="30%">小说名</th>
                    <th width="40%">最新章节</th>
                    <th width="20%">操作</th>
              </tr>
              <#list subscribeList as subscribe>
              <tr>
                    <td class="even">${subscribe.articleno?c}</td>
                    <td class="odd"><a href="${subscribe.url}" target="_blank">${subscribe.articlename}</a></td>
                    <td class="even"><a href="${subscribe.lastChapterUrl}" target="_blank">${subscribe.lastchapter}</a></td>
                    <td class="even" align="center"><a href="javascript:if(confirm('确实要取消订阅本书？')) document.location='${encodeURL("/user/subscribe!delete?subscribeno=${subscribe.subscribeno?c}")}';">移除</a></td>
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