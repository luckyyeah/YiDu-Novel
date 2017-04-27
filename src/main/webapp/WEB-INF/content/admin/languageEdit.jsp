<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
<s:form namespace="/admin" action="languageEdit" method="post" validate="true">
        <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
<table class="grid" align="center" cellspacing="1" width="800">
<caption><s:text name="label.admin.language.edit.caption" /></caption>
<tbody>
    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.language.edit.url" /></td>
       <td class="even"><s:textfield name ="url" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.language.edit.name" /></td>
       <td class="even"><s:textfield name ="name" size="25" maxlength="100"  cssClass="text" theme="simple"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.language.edit.title" /></td>
       <td class="even"><s:textfield name ="title" size="25" maxlength="100"  cssClass="text"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.language.edit.siteKeywords" /></td>
       <td class="even"><s:textarea name="siteKeywords" cssClass="tb-rounded"  cols="30" rows="2"/> </td>
    </tr>
    
    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.language.edit.siteDescription" /></td>
       <td class="even"><s:textarea name="siteDescription" cssClass="tb-rounded"  cols="30" rows="5"/></td>
    </tr>
    
    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.language.edit.copyright" /></td>
       <td class="even"><s:textfield name ="copyright" size="25" maxlength="100"  cssClass="text"/></td>
    </tr>
    
    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.language.edit.beianNo" /></td>
       <td class="even"><s:textfield name ="beianNo" size="25" maxlength="100"  cssClass="text"/></td>
    </tr>
    
    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.language.edit.analyticscode" /></td>
       <td class="even"><s:textarea name="analyticscode" cssClass="tb-rounded"  cols="30" rows="5"/></td>
    </tr>
    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.language.edit.category" /></td>
       <td class="even"><s:textfield name="category" size="25"  cssClass="text"/></td>
    </tr>
    <tr align="left" valign="middle">
       <td class="odd" colspan="2">
            <s:submit name="submit" value="%{getText('label.admin.edit.add')}" method="save" cssClass="submitButton" theme = "simple"/>
       </td>
    </tr>
</tbody>
</table>

</div>
</s:form>
<jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
</body>
</html>
