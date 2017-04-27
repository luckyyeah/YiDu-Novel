<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<input type="text" style="display: none;" name="pagination.sortColumn" id="sortColumn" value="<s:property value="pagination.sortColumn"/>"/>
<input type="text" style="display: none;" name="pagination.sortOrder" id="sortOrder" value="<s:property value="pagination.sortOrder"/>"/>
<table width="100%" align="center">
    <tr>
      <td class="pagination-label" width="100%" nowrap="nowrap">
        <s:property value="pagination.pageRecords"/> <s:text name="label.admin.page.items" />
      </td>
      <td>
        <s:if test="pagination.previousFlag">
        <a href="#" onclick="fnPagination(4,<s:property value="pagination.totalPages"/>);">
            <img src="/images/admin/left_end.gif" alt="Go to first page" width="15" height="19"/>
        </a>
       <s:else>
            <img src="/images/admin/left_end_gray.gif" alt="Go to first page" width="15" height="19"/>
        </s:else>
        </s:if>
      </td>
      <td>
        <s:if test="pagination.previousFlag">
        <a href="#" onclick="fnPagination(3,<s:property value="pagination.totalPages"/>);">
            <img src="/images/admin/left.gif" alt="Go to first page" width="15" height="19"/>
        </a>
        <s:else>
            <img src="/images/admin/left_gray.gif" alt="Go to first page" width="15" height="19"/>
        </s:else>
        </s:if>
      </td>
      <td class="pagination-label" nowrap="nowrap"><s:text name="label.admin.page.page" />:</td>
      <td>
        <input name="pagination.pageNumber" id="pageNumber" class="pagination-textbox" <s:if test="!pagination.previousFlag&&!pagination.nextFlag">readonly="readonly"</s:if>
        style="width: 30px;" maxlen="1" value="<s:property value="pagination.pageNumber"/>" type="text" onclick="fnPagination(7,<s:property value="pagination.totalPages"/>);"/></td>
      <td class="pagination-label" nowrap="nowrap">of <s:property value="pagination.totalPages"/></td>
      
      <td>
        <s:if test="pagination.nextFlag">
        <a href="#" onclick="fnPagination(1,<s:property value="pagination.totalPages"/>);">
            <img src="/images/admin/right.gif" alt="Go to next page" border="0" width="15" height="19"/>
        </a>
        <s:else>
            <img src="/images/admin/right_gray.gif" alt="Go to next page" border="0" width="15" height="19"/>
        </s:else>
        </s:if>
      </td>
      <td>
        <s:if test="pagination.nextFlag">
        <a href="#" onclick="fnPagination(2,<s:property value="pagination.totalPages"/>);">
            <img src="/images/admin/right_end.gif" alt="Go to next page" border="0" width="15" height="19"/>
        </a>
        <s:else>
            <img src="/images/admin/right_end_gray.gif" alt="Go to next page" border="0" width="15" height="19"/>
        </s:else>
        </s:if>
      </td>
      <td>&nbsp;</td>
      <td class="pagination-label" nowrap="nowrap"><s:text name="label.admin.page.itemsPerPage" /></td>
      <td class="pagination-linkoff" style="" nowrap="nowrap">
        <s:select onchange="fnPagination(5,0);" list="#{'5':'5','10':'10','50':'50','100':'100','0':'All'}" theme="simple" name="pagination.pageSize" id="pageSize" value="#request.pagination.pageSize"/>
      </td>
    </tr>
</table>
