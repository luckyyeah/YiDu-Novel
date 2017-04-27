<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>错误页面</title>
    <meta name="keywords" content="错误页面" />
    <meta name="description" content="出错了，请重新选择!" />
    <link href="${contextPath}/themes/${themeName}/single/css/StyleSheet.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="lbox">
    <div class="section">
        <p class="b-all-switch normal">出现错误啦！</p>
        <div class="msgwin mgl_61" style="text-align:center;">
            <div class="blockcontent">
                <div style="padding:10px"><br />
            <@s.actionerror /><br />请&nbsp;<a href="javascript:history.back(1)">返 回</a>&nbsp;并修正或&nbsp;<a href="${contextPath}/">返回首页</a><br /><br /></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>