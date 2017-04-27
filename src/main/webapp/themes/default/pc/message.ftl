<#include "common.ftl"/>

<#macro titleContent>  
<title>${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro content>
<section class="section board-list board-list-collapse">
    <p class="b-all-switch normal">处理成功！</p>
    <div class="msgwin mgl_61" style="text-align:center;">
        <div class="blockcontent">
            <div style="padding:10px"><@s.actionmessage /><br /><a href="javascript:window.close()">关闭本窗口</a><br /><br /></div>
        </div>
    </div>
</section>
</#macro>