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

  <div id="container">
        <div class="msgwin mgl_61" style="text-align:center;">
            <div class="blockcontent">
                <div style="padding:10px"><br /><s:actionmessage  theme="simple"/></div>
            </div>
        </div>
  </div>
<jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
</body>
</html>
