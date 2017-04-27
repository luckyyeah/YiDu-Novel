<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Style-type" content="text/css" />
<title>易读小说管理后台</title>
<meta name="keywords" content="易读小说系统管理后台" />
<meta name="description" content="易读小说系统是开源的JAVA项目，可以帮你快速搭建自己的小说系统!" />
<link rel="stylesheet" type="text/css" href="<s:property value="contextPath" />/css/common.css">
<link rel="stylesheet" type="text/css" href="<s:property value="contextPath" />/css/styles.css">
<script type="text/javascript" src="<s:property value="contextPath" />/js/lib/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<s:property value="contextPath" />/js/yidu.js"></script>
<script type="text/javascript" src="<s:property value="contextPath" />/js/pagination.js"></script>
</head>
<body>
<div id="wrapper">
  <div class="shadow" style="height:1px;" ></div>
  <header id="global-head" style="height:45px;">
    <div>
      <nav class="site-navigation cf">
      <a class="home" href="<s:property value="contextPath" />/"><s:text name="label.admin.navi.home" /></a>
      <a href="<s:property value="contextPath" />/admin/index"><s:text name="label.admin.navi.adminHome" /></a>
      <a href="http://www.51yd.org/" target="_blank"><s:text name="label.admin.navi.forum" /></a>
      </nav>
      <div class="hd-follow" style="width:300px;align:right" >
      <span id="checklogin">
        <s:text name="label.admin.navi.welcomeMeg" />
        <a href="<s:property value="contextPath" />/logout" style="color: rgb(240, 240, 240);" class="out"><s:text name="label.admin.navi.logout" /></a>
      </span>
    </div>
  </header>
    <div id="channel-header" class="clearfix">
      <div class="channel-header-wrapper">
        <nav class="channel-nav">
          <ul class="channel-nav-list">
            <li><a href="<s:property value="contextPath" />/admin/configEdit" title="<s:text name="label.admin.navi.configEdit" />"><s:text name="label.admin.navi.configEdit" /></a></li>
            <li><a href="<s:property value="contextPath" />/admin/languageEdit" title="<s:text name="label.admin.navi.languageEdit" />"><s:text name="label.admin.navi.languageEdit" /></a></li>
            <li><a href="<s:property value="contextPath" />/admin/blockList" title="<s:text name="label.admin.navi.blockList" />"><s:text name="label.admin.navi.blockList" /></a></li>
            <li><a href="<s:property value="contextPath" />/admin/articleList" title="<s:text name="label.admin.navi.articleList" />"><s:text name="label.admin.navi.articleList" /></a></li>
            <li><a href="<s:property value="contextPath" />/admin/userList" title="<s:text name="label.admin.navi.userList" />"><s:text name="label.admin.navi.userList" /></a></li>
            <li><a href="<s:property value="contextPath" />/admin/messageList" title="<s:text name="label.admin.navi.messageList" />"><s:text name="label.admin.navi.messageList" /></a></li>
            <li><a href="<s:property value="contextPath" />/admin/reviewList" title="<s:text name="label.admin.navi.reviewList" />"><s:text name="label.admin.navi.reviewList" /></a></li>
        </ul>
      </nav>
    </div>
  </div>

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
