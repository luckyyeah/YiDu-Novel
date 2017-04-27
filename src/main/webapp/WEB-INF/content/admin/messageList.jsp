<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
  <form namespace="/admin" action="messageList" method="post" name="paginationForm">
    <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />

    <table class="yidu-table" align="center">
        <colgroup>
            <col width="100px">
            <col width="170px">
            <col width="400px">
            <col width="130px">
            <col width="50px">
            <col width="100px">
        </colgroup>
        <tr>
            <th class="sortable <s:if test="pagination.sortColumn.equals('loginid')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'articleno');"><s:text name="label.admin.messagae.list.sender" /></a></th>
            
            <th class="sortable <s:if test="pagination.sortColumn.equals('title')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'articlename');"><s:text name="label.admin.messagae.list.title" /></a></th>
            
            <th style="word-break:break-all; word-wrap:break-word;"><s:text name="label.admin.messagae.list.content" /></th>
            
            <th class="sortable <s:if test="pagination.sortColumn.equals('postdate')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'category');"><s:text name="label.admin.messagae.list.date" /></a></th>
            
            <th class="sortable <s:if test="pagination.sortColumn.equals('isread')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'author');"><s:text name="label.admin.messagae.list.status" /></a></th>
            
            <th class="sortable"><s:text name="label.admin.list.operate" /></th>
        </tr>
        <s:iterator value="messageList" id="message" status="rowstatus">
        <s:if test="#rowstatus.even == true">
        <tr class="ac_odd">
        </s:if>
        <s:else>
        <tr>
        </s:else>
            <td><s:property  value="#message.loginid" /></td>
            <td><s:property  value="#message.title" /></td>
            <td><s:property  value="#message.content" /></td>
            <td><s:date name="#message.postdate" format="yyyy/MM/dd HH:mm" /></td>
            <td><s:property  value="collections['collectionProperties.message.isread'][#message.isread]" /></td>
            <td>
                <a href="<s:property value="contextPath" />/admin/messageReply?messageno=<s:property value='#message.messageno' />"><s:text name="label.admin.list.reply" /></a>
                <a href="javascript:confirmDelete('<s:property value="contextPath" />/admin/messageList!delete?messageno=<s:property value='#message.messageno' />')"><s:text name="label.admin.list.delete" /></a>
            </td>
        </tr>
        </s:iterator>
    </table>
    <table width="950px" align="center">
        <tr>
            <td class="pagination-label" width="100%" nowrap="nowrap">
                    <jsp:include page="/WEB-INF/content/admin/commom/pagination.jsp" />
            </td>
        </tr>
    </table>
    </div>
     </form>
    <jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
</body>
</html>