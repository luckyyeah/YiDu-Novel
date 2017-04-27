<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
<body>
    <s:form namespace="/admin" action="blockEdit" method="post" validate="true">
        <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
        <s:hidden name="blockno" />
        <br>
        <table class="yidu-table" align="center">
            <colgroup>
                <col width="120px">
                <col width="442px">
            </colgroup>
            <tbody>
                <tr>
                    <th colspan="2"><s:text name="label.admin.block.edit.title" /></th>
                </tr>
                <tr>
                    <td><s:text name="label.admin.block.edit.blockid" /></td>
                    <td>
                        <s:textfield name="blockid" id="blockid" maxlength="32" cssClass="tb-rounded" />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.block.edit.blockname" /></td>
                    <td>
                        <s:textfield name="blockname" id="blockname" maxlength="32" cssClass="tb-rounded" />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.block.edit.type" /></td>
                    <td>
	                    <s:select
		                    id="type"
		                    name="type"
		                    list="collections['collectionProperties.block.type']"
		                    />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.block.edit.category" /></td>
                    <td>
                        <s:select
                            id="category"
                            name="category"
                            headerKey = "0"
                            headerValue = ""
                            list="collections['collectionProperties.article.category']"
                            />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.block.edit.sortcol" /></td>
                    <td>
                        <s:select
                            id="sortcol"
                            name="sortcol"
                            headerKey = ""
                            headerValue = ""
                            list="collections['collectionProperties.block.sortCol']"
                            />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.block.edit.isasc" /></td>
                    <td>
                        <s:radio list="collections['collectionProperties.boolean']" name="isasc"></s:radio>
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.block.edit.isfinish" /></td>
                    <td>
                        <s:radio list="collections['collectionProperties.boolean']" name="isfinish"></s:radio>
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.block.edit.limitnum" /></td>
                    <td>
                        <s:textfield id="limitnum" name="limitnum" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.block.edit.content" /></td>
                    <td>
                        <s:textarea id="content" name="content" cssClass="tb-rounded"  cols="30" rows="5"/> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.block.edit.target" /></td>
                    <td>
                         <s:select
                            id="target"
                            name="target"
                            list="collections['collectionProperties.block.target']"
                            />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center; padding: 2px">
                        <s:submit name="submit" value="%{getText('label.admin.edit.back')}" method="back" cssClass="submitButton" theme = "simple" onclick="goback()"/>
                        <s:if test="blockno==0">
                            <s:submit name="submit" value="%{getText('label.admin.edit.add')}" method="save" cssClass="submitButton" theme = "simple"/>
                        </s:if>
                        <s:else>
                            <s:submit name="submit" value="%{getText('label.admin.edit.modify')}" method="save" cssClass="submitButton" theme = "simple"/>
                        </s:else>
                    </td>
                </tr>
            </tbody>
        </table>
    </s:form>
    <jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
</body>
</html>
