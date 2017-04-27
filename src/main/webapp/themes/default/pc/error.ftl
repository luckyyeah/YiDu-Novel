<#include "common.ftl"/>

<#macro titleContent>  
<title>${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro content>
<section class="section board-list board-list-collapse">
    <p class="b-all-switch normal">出现错误！</p>
    <div class="msgwin mgl_61" style="text-align:center;">
        <div class="blockcontent">
            <div style="padding:10px"><br />
        <@s.actionerror /><br />请&nbsp;<a href="javascript:history.back(1)">返 回</a>&nbsp;并修正或&nbsp;<a href="${contextPath}/">返回首页</a><br /><br /></div>
        </div>
    </div>
</section>
</#macro>