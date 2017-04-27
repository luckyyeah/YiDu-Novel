<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
<form namespace="/admin" action="reviewList" method="post" name="paginationForm">
    <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
    <table align="center">
        <tr>
            <td>
            评论搜索：<s:select list="collections['collectionProperties.review.searchkey']"  name="option" listKey="key" listValue="value" theme="simple"/>
            </td>
            <td>&nbsp;&nbsp;</td>
            <td>
                <s:textfield name = "key" id= "key" maxLength="30" size = "30"/>
            </td>
            <td>&nbsp;&nbsp;</td>
            <td>
                <input type="submit" id="searchbuttom" value="搜索">
            </td>
        </tr>
    </table>
    <table class="yidu-table" align="center">
        <colgroup>
            <col width="80px">
            <col width="150px">
            <col width="170px">
            <col width="170px">
            <col width="140px">
            <col width="120px">
            <col width="80px">
        </colgroup>
        <tr>
            <th class="sortable <s:if test="pagination.sortColumn.equals('reviewno')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'reviewno');"><s:text name="label.admin.review.list.reviewno" /></a></th>
            
            <th class="sortable <s:if test="pagination.sortColumn.equals('loginid')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'loginid');"><s:text name="label.admin.review.list.loginid" /></a></th>
            
            <th class="sortable <s:if test="pagination.sortColumn.equals('articlename')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'articlename');"><s:text name="label.admin.review.list.articlename" /></a></th>
            
            <th class="sortable <s:if test="pagination.sortColumn.equals('chaptername')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'chaptername');"><s:text name="label.admin.review.list.chaptername" /></a></th>
            
            <th class="sortable"><s:text name="label.admin.review.list.review" /></th>
            <th class="sortable"><s:text name="label.admin.review.list.postdate" /></th>
            <th class="sortable"><s:text name="label.admin.list.operate" /></th>

        </tr>
        <s:iterator value="reviewList" id="review" status="rowstatus">
        <s:if test="#rowstatus.even == true">
        <tr class="ac_odd">
        </s:if>
        <s:else>
        <tr>
        </s:else>
            <td><s:property  value="#review.reviewno" /></td>
            <td><s:property  value="#review.loginid" /></td>
            <td><s:property  value="#review.articlename" /></td>
            <td><s:property  value="#review.chaptername" /></td>
            <td><s:property  value="#review.review" /></td>
            <td><s:date name="#review.postdate" format="yyyy-MM-dd HH:mm" /></td>
            <td>
                <a href="javascript:confirmDelete('<s:property value="contextPath" />/admin/reviewList!delete?reviewno=<s:property value='#review.reviewno' />')"><s:text name="label.admin.list.delete" /></a>
            </td>
        </tr>
        </s:iterator>
    </table>
    <table width="910px" align="center">
        <tr>
            <td class="pagination-label" width="100%" nowrap="nowrap">
                <form namespace="/admin" action="reviewList" method="post" name="paginationForm">
                    <jsp:include page="/WEB-INF/content/admin/commom/pagination.jsp" />
                </form>
            </td>
        </tr>
    </table>
    </div>
    </form>
    <jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
</body>
</html>