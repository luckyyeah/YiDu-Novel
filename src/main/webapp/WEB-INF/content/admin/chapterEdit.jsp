<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
    <s:form namespace="/admin" action="chapterEdit" method="post" validate="true">
        <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
        <br>
        <table class="yidu-table" align="center">
            <colgroup>
                <col width="120px">
                <col width="580px">
            </colgroup>
            <tbody>
                <tr>
                    <th colspan="2"><s:text name="label.admin.chapter.edit.title" /></th>
                </tr>
                <tr>
                    <td><s:text name="label.admin.chapter.edit.articlename" /></td>
                    <td>
                        <s:property value="articlename" />
                    </td>
                </tr>
                <s:if test="chapterno!=0">
                <tr>
                    <td><s:text name="label.admin.chapter.edit.chapterno" /></td>
                    <td>
                        <s:property value="chapterno" />
                    </td>
                </tr>
                </s:if>
                <tr>
                    <td><s:text name="label.admin.chapter.edit.chaptername" /></td>
                    <td>
                        <s:textfield name="chaptername" id="chaptername" maxlength="32" cssClass="tb-rounded" />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.chapter.edit.isvip" /></td>
                    <td>
                        <s:radio
                            id="vip"
                            name="vip"
                            list="collections['collectionProperties.chapter.isvip']"
                            />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.chapter.edit.content" /></td>
                    <td>
                        <s:textarea id="content" name="content" cssClass="tb-rounded" cols="30" rows="20"/> 
                    </td>
                </tr>
                <s:if test="chapterno!=0">
                <tr>
                    <td><s:text name="label.admin.chapter.edit.postdate" /></td>
                    <td>
                        <s:date name="postdate" format="yyyy-MM-dd HH:mm" />
                    </td>
                </tr>
                </s:if>

                <tr>
                    <td colspan="2" style="text-align: center; padding: 2px">
                        <s:submit name="submit" value="%{getText('label.admin.edit.back')}" method="back" cssClass="submitButton" theme = "simple" onclick="goback()"/>
                        <s:if test="articleno==0">
                            <s:submit name="submit" value="%{getText('label.admin.edit.add')}" method="save" cssClass="submitButton" theme = "simple"/>
                        </s:if>
                        <s:else>
                            <s:submit name="submit" value="%{getText('label.admin.edit.modify')}" method="save" cssClass="submitButton" theme = "simple"/>
                        </s:else>
                    </td>
                </tr>
            </tbody>
        </table>
        <s:hidden  name="articlename" />
        <s:hidden  name="articleno" />
        <s:hidden name="chapterno" />
    </s:form>
    </div>
    <jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
</body>
</html>
