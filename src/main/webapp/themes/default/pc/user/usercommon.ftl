<#include "../common.ftl"/>
<#macro menuContent>
<div class="col-sub">
    <nav class="top-rank">
        <h3>用户面板</h3>
        <div class="top-rank-list">
            <ul>
               <li <#if pageType==21>class="current"</#if>><a href="${encodeURL("/user/bookcase")}">我的书架</a></li>
            </ul>
            <ul>
               <li <#if pageType==22>class="current"</#if>><a href="${encodeURL("/user/message")}">查看短信</a></li>
            </ul>
            <ul>
               <li <#if pageType==20>class="current"</#if>><a href="${encodeURL("/user/subscribe")}">订阅管理</a></li>
            </ul>
            <ul>
               <li <#if pageType==23>class="current"</#if>><a href="${encodeURL("/user/useredit")}">编辑资料</a></li>
            </ul>

            <#if user.type==10>
            <ul>
               <li <#if pageType==24>class="current"</#if>><a href="${encodeURL("/user/regiauthor")}">申请作者</a></li>
            </ul>
            </#if>
            <#if user.type==20 || user.type==40 || user.type==41>
            <ul>
               <li <#if pageType==30||pageType==31||pageType==32||pageType==33>class="current"</#if>><a href="${encodeURL("/user/articleList")}">小说管理</a></li>
            </ul>
            <ul>
               <li <#if pageType==40>class="current"</#if>><a href="${encodeURL("/user/billdetail")}">账单详细</a></li>
            </ul>
            </#if>
            <ul class="last">
               <li><a href="${encodeURL("/logout")}">退出登录</a></li>
            </ul>
        </div>
    </nav>
</div>
</#macro>