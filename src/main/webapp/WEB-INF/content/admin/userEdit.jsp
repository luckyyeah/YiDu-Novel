<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
    <s:form namespace="/admin" action="userEdit" method="post" validate="true">
        <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
        <s:hidden name="userno" />
        <br>
        <table class="yidu-table" align="center">
            <colgroup>
                <col width="120px">
                <col width="442px">
            </colgroup>
            <tbody>
                <tr>
                    <th colspan="2"><s:text name="label.admin.user.edit.title" /></th>
                </tr>
               <s:if test="userno!=0">
                <tr>
                    <td><s:text name="label.admin.user.edit.userno" /></td>
                    <td>
                       <s:property value="userno" />
                    </td>
                </tr>
                </s:if>
                <tr>
                    <td><s:text name="label.admin.user.edit.loginid" /></td>
                    <td>
                        <s:textfield id="loginid" name="loginid" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.user.edit.password" /></td>
                    <td>
                        <s:password name="password" value="password" fieldValue="true"/>
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.user.edit.email" /></td>
                    <td>
                        <s:textfield id="email" name="email" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.user.edit.sex" /></td>
                    <td>
                         <s:radio
                            id="sex"
                            name="sex"
                            list="collections['collectionProperties.user.sex']"
                            />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.user.edit.type" /></td>
                    <td>
                         <s:select
                            id="type"
                            name="type"
                            list="collections['collectionProperties.user.type']"
                            />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.user.edit.qq" /></td>
                    <td>
                        <s:textfield id="qq" name="qq" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.user.edit.lineno" /></td>
                    <td>
                        <s:textfield id="lineno" name="lineno" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                 <tr>
                  <td><s:text name="label.admin.user.edit.realname" /></td>
                  <td>
                      <s:textfield name="realname" id = "realname" cssClass="tb-rounded" maxlength="10"/>
                  </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.user.edit.username" /></td>
                    <td>
                        <s:textfield id="username" name="username" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                  <td><s:text name="label.admin.user.edit.id" /></td>
                  <td>
                    <s:textfield name="id" id = "id" cssClass="tb-rounded" maxlength="18"/>
                  </td>
                </tr>
                <tr>
                  <td><s:text name="label.admin.user.edit.mobileno" /></td>
                  <td>
                    <s:textfield name="mobileno" id = "mobileno" cssClass="tb-rounded" maxlength="11"/>
                  </td>
                </tr>
                <tr>
                  <td><s:text name="label.admin.user.edit.branch" /></td>
                  <td>
                      <s:textfield name="branch" id = "branch" cssClass="tb-rounded" maxlength="50"/>
                  </td>
                </tr>
                <tr>
                  <td><s:text name="label.admin.user.edit.bankno" /></td>
                  <td>
                      <s:textfield name="bankno" id = "bankno" cssClass="tb-rounded" maxlength="20"/>
                  </td>
                </tr>
                <tr>
                  <td><s:text name="label.admin.user.edit.alipayacount" /></td>
                  <td>
                    <s:textfield name="alipayacount" id = "alipayacount" cssClass="tb-rounded" maxlength="50"/>
                  </td>
                </tr>
                <s:if test="userno!=0">
                <tr>
                    <td><s:text name="label.admin.user.edit.regdate" /></td>
                    <td>
                        <s:date name="regdate" format="yyyy-MM-dd HH:mm" />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.user.edit.lastlogin" /></td>
                    <td>
                        <s:date name="lastlogin" format="yyyy-MM-dd HH:mm" />
                    </td>
                </tr>
                </s:if>
                <tr>
                    <td colspan="2" style="text-align: center; padding: 2px">
                        <s:submit name="submit" value="%{getText('label.admin.edit.back')}" method="back" cssClass="submitButton" theme = "simple" onclick="goback()"/>
                        <s:if test="userno==0">
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
    </div>
    <jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
</body>
</html>
