<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
    <jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
    <div id="container">
        <section class="section board-list board-list-collapse">
            <p class="b-all-switch normal">出现错误！</p>
            <div class="msgwin mgl_61" style="text-align:center;">
                <div class="blockcontent">
                    <div style="padding:10px"><br /><s:actionerror theme="simple"/><br />请 <a href="javascript:history.back(1)">返 回</a> 并修正或 <a href="/admin/index">返回首页</a><br /><br /></div>
                </div>
                
            </div>
          <jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
        </section>
      </div>
    </div>
</body>
</html>


