<#include "common.ftl"/>

<#macro titleContent>  
<title><#if article?? >${article.articlename}</#if>的评论|${getText("label.system.title")}</title>
<meta name="keywords" content="<#if article?? >${article.articlename}</#if>的评论,${getText("label.system.siteKeywords")}" />
</#macro>

<#macro content>
<div id="review_ad_01"></div>
<div class="mainnav" id="navList">
    <div class="main-index">位置：  &nbsp; > &nbsp; <a href="${article.url}"><#if article?? >${article.articlename}</#if></a>&nbsp;>&nbsp; <#if article?? >${article.articlename}</#if>的评论</div>
    <div class="comment_left">
        <div class="commenthead">
            <div class="ti">
                <h2>《${article.articlename}》的评论</h2>
                <div class="par">共有评论<a target="blank" href="${encodeURL("/reviewList?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}">${pagination.totalRecords?c}</a>条</div>
                <div class="par2"><a target="_blank" href="${encodeURL("/reviewList?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}">[全部评论]</a></div>
            </div>
        </div>
        <ul class="commentslist">
            <#list reviewList as review>
                <li class="line">
                <div class="has_avatar">
                    <a target="_blank" class="a_avatar50" href="${encodeURL("/userInfo?userno=${review.userno?c}")}"><img width="50" height="50" alt="${review.loginid}" src="${contextPath}/themes/${themeName}/pc/images/90_avatar_middle.jpg"></a>
                </div>
                <div class="replycontent">
                    <div class="t_t">
                        <div>
                            <a target="_blank" title="${review.loginid}" class="commenter" href="${encodeURL("/userInfo?userno=${review.userno?c}")}">${review.loginid}</a>
                            <span class="time">评论于：${review.postdate?string("yyyy-MM-dd HH:mm")}</span>
                        </div>
                    </div>
                    <ul class="Reviewer">
                        <li class="txt">${review.review?html}
                        </li>
                    </ul>
                  </div>
                </li>
            </#list>
        </ul>
        <div class="pages">
              <div class="pagelink" id="pagelink">
                <#assign listurl = "/reviewList?subdir=${article.subdir?c}&articleno=${articleno?c}&page=" >
                <#assign listurlforjs = "${contextPath}/reviewList/${articleno?c}/" >
                <em id="pagestats">${pagination.pageNumber}/${pagination.totalPages?c}</em>
                <a href="${encodeURL(listurl +"1")}" class="first">首页</a>
                <#list pagination.pageNumberList as pagenum >
                    <#if pagenum_index == 0 && (pagenum > 1 )>
                        <a href="${encodeURL(listurl+ (pagenum-1)?c)}" class="prev">&lt;</a>
                    </#if>
                    <#if pagenum == pagination.pageNumber>
                        <strong>${pagenum?c}</strong>
                    <#else>
                        <a href="${encodeURL(listurl + pagenum?c)}"> ${pagenum?c} </a>
                    </#if>
                    <#assign mxpagenum = pagenum >
                </#list>
                <#if mxpagenum?? && (mxpagenum < pagination.totalPages)>
                     <a href="${encodeURL(listurl + (mxpagenum+1)?c)}" class="next">&gt;</a>
                </#if>
                <a href="${encodeURL(listurl + pagination.totalPages?c)}">尾页</a>
                <kbd>
                    <input name="page" type="text" size="4" maxlength="6" onkeydown="if(event.keyCode==13){window.location='${listurlforjs}'+this.value+'.html'; return false;}" /></kbd>
             </div>
        <div class="blank"></div>
         <div id="review_ad_02"></div>
        <!-- 我的回复框 -->
        <div id="commentbox" class="talker_form">
            <#if !loginFlag>
            <div class="logintip">您还未登录，请登录或注册后再发表回复</div>
            </#if>
            <div class="form_t"> 评论内容： </div>
            <div class="form_b">
                <div class="smtextarea">
                    <textarea class="comment_content" onkeyup="stat_text_word(this);" rows="5" cols="60" name="review" id="review">既然来了，就留下几句话吧</textarea>
                    <input type="hidden" value="${articleno?c}" id="articleno" name="articleno">
                    <input type="hidden" value="false" name="isFromForm" id="isFromForm" >
                </div>
                <div class="form_f">
                    <div class="box_l"> 
                        <div>已输入字数：<span id="comment_text_word">0</span>  (评论最少5字最多500字) </div>
                    </div>
                    <input type="submit" class="release_btn submit_comment_btn" value=" " id= "reviewSubmitbtn" name="submitbtn">
                </div>
            </div>
        </div>
    </div>
   <div id="review_ad_03"></div>
</#macro>
