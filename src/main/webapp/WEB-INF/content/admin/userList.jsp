<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
    <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
    <br>
    <table class="yidu-table" align="center">
        <colgroup>
            <col width="120px">
            <col width="170px">
            <col width="170px">
            <col width="100px">
            <col width="150px">
            <col width="120px">
            <col width="80px">
        </colgroup>
        <tr>
            <th class="sortable <s:if test="pagination.sortColumn.equals('userno')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'userno');"><s:text name="label.admin.user.list.userno" /></a></th>
            
            <th class="sortable <s:if test="pagination.sortColumn.equals('loginid')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'loginid');"><s:text name="label.admin.user.list.loginid" /></a></th>
            
            <th class="sortable <s:if test="pagination.sortColumn.equals('type')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'type');"><s:text name="label.admin.user.edit.type" /></a></th>
            
            <th class="sortable <s:if test="pagination.sortColumn.equals('sex')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'sex');"><s:text name="label.admin.user.list.sex" /></a></th>
            
            <th class="sortable"><s:text name="label.admin.user.list.regdate" /></th>
            <th class="sortable"><s:text name="label.admin.user.list.lastlogin" /></th>
            <th class="sortable"><s:text name="label.admin.list.operate" /></th>

        </tr>
        <s:iterator value="userList" id="user" status="rowstatus">
        <s:if test="#rowstatus.even == true">
        <tr class="ac_odd">
        </s:if>
        <s:else>
        <tr>
        </s:else>
            <td><s:property  value="#user.userno" /></td>
            <td><s:property  value="#user.loginid" /></td>
            <td><s:property  value="collections['collectionProperties.user.type'][#user.type]" /></td>
            <td><s:property  value="collections['collectionProperties.user.sex'][#user.sex]" /></td>
            <td><s:date name="#user.regdate" format="yyyy-MM-dd HH:mm" /></td>
            <td><s:date name="#user.lastlogin" format="yyyy-MM-dd HH:mm" /></td>
            <td>
                <a href="<s:property value="contextPath" />/admin/userEdit?userno=<s:property value='#user.userno' />"><s:text name="label.admin.list.modify" /></a>
                <a href="javascript:confirmDelete('<s:property value="contextPath" />/admin/userList!delete?userno=<s:property value='#user.userno' />')"><s:text name="label.admin.list.delete" /></a>
            </td>
        </tr>
        </s:iterator>
    </table>
    <table width="910px" align="center">
        <tr>
            <td class="pagination-label" width="100%" nowrap="nowrap">
                <form namespace="/admin" action="userList" method="post" name="paginationForm">
                    <jsp:include page="/WEB-INF/content/admin/commom/pagination.jsp" />
                </form>
            </td>
        </tr>
        <tr>
            <td class="pagination-label" width="100%" nowrap="nowrap">
                <a href="<s:property value="contextPath" />/admin/userEdit"><s:text name="label.admin.list.add" /></a>
            </td>
        </tr>
    </table>
    </div>
    <jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
</body>
</html>