<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
    <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
    <br>
    <table class="yidu-table" align="center">
        <colgroup>
            <col width="130px">
            <col width="100px">
            <col width="250px">
            <col width="250px">
            <col width="100px">
        </colgroup>
        <tr>
            <th class="sortable <s:if test="pagination.sortColumn.equals('blockno')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'blockno');"><s:text name="label.admin.block.list.blockno" /></a></th>
            
            <th class="sortable <s:if test="pagination.sortColumn.equals('blockid')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'blockid');"><s:text name="label.admin.block.list.blockid" /></a></th>
            
            <th class="sortable <s:if test="pagination.sortColumn.equals('blockname')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'blockname');"><s:text name="label.admin.block.list.blockname" /></a></th>
            
            <th class="sortable <s:if test="pagination.sortColumn.equals('type')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'type');"><s:text name="label.admin.block.list.type" /></a></th>
            <th class="sortable"><s:text name="label.admin.list.operate" /></th>
        </tr>
        <s:iterator value="blockList" id="block" status="rowstatus">
        <s:if test="#rowstatus.even == true">
        <tr class="ac_odd">
        </s:if>
        <s:else>
        <tr>
        </s:else>
            <td><s:property  value="#block.blockno" /></td>
            <td><s:property  value="#block.blockid" /></td>
            <td><s:property  value="#block.blockname" /></td>
            <td><s:property  value="collections['collectionProperties.block.type'][#block.type]" /></td>
            <td>
                <a href="<s:property value="contextPath" />/admin/blockEdit?blockno=<s:property value='#block.blockno' />"><s:text name="label.admin.list.modify" /></a>
                <a href="javascript:confirmDelete('<s:property value="contextPath" />/admin/blockList!delete?blockno=<s:property value='#block.blockno' />')"><s:text name="label.admin.list.delete" /></a>
            </td>
        </tr>
        </s:iterator>
    </table>
    <table width="898px" align="center">
        <tr>
            <td class="pagination-label" width="100%" nowrap="nowrap">
                <form namespace="/admin" action="blockList" method="post" name="paginationForm">
                <jsp:include page="/WEB-INF/content/admin/commom/pagination.jsp" />
                </form>
            </td>
        </tr>
        <tr>
            <td class="pagination-label" width="100%" nowrap="nowrap">
                <a href="<s:property value="contextPath" />/admin/blockEdit"><s:text name="label.admin.list.add" /></a>
            </td>
        </tr>
     </table>
     </div>
    <jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
</body>
</html>