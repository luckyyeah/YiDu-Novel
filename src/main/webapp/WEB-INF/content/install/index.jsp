<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Style-type" content="text/css" />
<title><s:text name="label.install.page.title" /></title>
<meta name="author" content="www.51yd.org"/> 
<link rel="stylesheet" type="text/css" href="<s:property value="contextPath" />/css/board.css">
<link rel="stylesheet" type="text/css" href="<s:property value="contextPath" />/css/common.css">
<link rel="stylesheet" type="text/css" href="<s:property value="contextPath" />/css/styles.css">
<script type="text/javascript" src="<s:property value="contextPath" />/js/lib/jquery-1.7.1.min.js"></script>
</head>
<body>
<div id="wrapper">
  <div class="shadow" style="height:1px;" ></div>
  <header id="global-head" style="height:45px;">
    <div>
      
      </nav>
      <div style="float: left;height: 33px;line-height: 32px;text-align: center;color: #fff;" >
                    <s:text name="label.install.page.title" />
    </div>
  </header>

<script type="text/javascript">
<!--
    $(document).ready(function() {
        clearErrorMessages = function(form) {
            clearErrorMessagesCSS(form);
            clearServerErrorMessages();
        };
    });

    function clearServerErrorMessages() {
        $("#ErrorList").text("");
    }
//-->
</script>

<s:form namespace="/install" action="index" method="post" validate="true">
        <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
        
<table class="grid" align="center" cellspacing="1" width="800">        
<caption><s:text name="label.install.caption" /></caption>
<tbody>
    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.install.url" /></td>
       <td class="even"><s:textfield name ="url" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.install.name" /></td>
       <td class="even"><s:textfield name ="name" size="25" maxlength="100"  cssClass="text" theme="simple"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.install.title" /></td>
       <td class="even"><s:textfield name ="title" size="25" maxlength="100"  cssClass="text"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.install.siteKeywords" /></td>
       <td class="even"><s:textarea name="siteKeywords" cssClass="tb-rounded"  cols="30" rows="2"/> </td>
    </tr>
    
    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.install.siteDescription" /></td>
       <td class="even"><s:textarea name="siteDescription" cssClass="tb-rounded"  cols="30" rows="5"/></td>
    </tr>
    
    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.install.copyright" /></td>
       <td class="even"><s:textfield name ="copyright" size="25" maxlength="100"  cssClass="text"/></td>
    </tr>
    
    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.install.beianNo" /></td>
       <td class="even"><s:textfield name ="beianNo" size="25" maxlength="100"  cssClass="text"/></td>
    </tr>
    
    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.install.analyticscode" /></td>
       <td class="even"><s:textarea name="analyticscode" cssClass="tb-rounded"  cols="30" rows="5"/></td>
    </tr>
    
</tbody>
</table>
<br />

<table class="grid" align="center" cellspacing="1" width="800">
<caption><s:text name="label.install.caption2" /></caption>
<tbody>
    <tr align="left" valign="middle">
        <td class="odd" width="20%"><s:text name="label.install.dbhost" /></td>
       <td class="even"><s:textfield name ="dbhost" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>
    <tr align="left" valign="middle">
        <td class="odd" width="20%"><s:text name="label.install.dbport" /></td>
       <td class="even"><s:textfield name ="dbport" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>
    <tr align="left" valign="middle">
        <td class="odd" width="20%"><s:text name="label.install.dbname" /></td>
       <td class="even"><s:textfield name ="dbname" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>
    <tr align="left" valign="middle">
        <td class="odd" width="20%"><s:text name="label.install.dbusername" /></td>
        <td class="even">
            <s:textfield name ="dbusername" size="25" maxlength="100" cssClass="text" theme="simple"/>
        </td>
    </tr>
    <tr align="left" valign="middle">
        <td class="odd" width="20%"><s:text name="label.install.dbpassword" /></td>
        <td class="even">
            <s:textfield name ="dbpassword" size="25" maxlength="100" cssClass="text" theme="simple"/>
        </td>
    </tr>
</tbody>
</table>
<br />

<table class="grid" align="center" cellspacing="1" width="800">
<caption><s:text name="label.install.caption3" /></caption>
<tbody>
    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.install.username" /></td>
       <td class="even"><s:textfield name ="username" size="40" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.install.password" /></td>
       <td class="even"><s:textfield name ="password" size="25" maxlength="100"  cssClass="text" theme="simple"/></td>
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
