尊敬的用户 : ${user.loginid}
您在${sitename}订阅的的小说已经更新啦，抓紧去看看吧  :)
<#assign articleno = 0>
<#list chapterList as chapter>
<#if articleno != chapter.articleno>

小说名： 
        ${chapter.articlename} 
<#assign articleno =  chapter.articleno>
更新章节：
</#if>
        ${chapter.chaptername}  ${siteuri}/reader/${chapter.articleno?c}/${chapter.chapterno?c}.html
</#list>

推荐小说：
<#if randomRecommendArticleList ?? > 
     <#list randomRecommendArticleList as article>
        ${article.articlename}  ${siteuri}/book/${article.articleno?c}.html
     </#list>
</#if>

如果要取消订阅邮件，请登录后在订阅管理中删除订阅信息！

${sitename}
电脑版:${siteuri}
手机版:http://${mobileuri}