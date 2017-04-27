<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
    <s:form namespace="/admin" action="articleEdit" method="post" validate="true" enctype="multipart/form-data">
        <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
        <s:hidden name="articleno" />
        <br>
        <table class="yidu-table" align="center">
            <colgroup>
                <col width="120px">
                <col width="580px">
            </colgroup>
            <tbody>
                <tr>
                    <th colspan="2"><s:text name="label.admin.article.edit.title" /></th>
                </tr>
                <s:if test="articleno!=0">
                <tr>
                    <td><s:text name="label.admin.article.edit.articleno" /></td>
                    <td>
                        <s:property value="articleno" />
                    </td>
                </tr>
                </s:if>
                <tr>
                    <td><s:text name="label.admin.article.edit.articlename" /></td>
                    <td>
                        <s:textfield name="articlename" id="articlename" maxlength="32" cssClass="tb-rounded" />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.category" /></td>
                    <td>
                        <s:select
                            id="category"
                            name="category"
                            list="collections['collectionProperties.article.category']"
                            />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.author" /></td>
                    <td>
                        <s:textfield id="author" name="author" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.agent" /></td>
                    <td>
                        <s:textfield id="agent" name="agent" cssClass="tb-rounded" /> 
                    </td>
                </tr>

                <tr>
                    <td><s:text name="label.admin.article.edit.intro" /></td>
                    <td>
                        <s:textarea id="intro" name="intro" cssClass="tb-rounded"  cols="30" rows="5"/> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.keywords" /></td>
                    <td>
                        <s:textfield id="keywords" name="keywords" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.articlespic" /></td>
                    <td>
                        <s:file name="articlespic"></s:file> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.fullflag" /></td>
                    <td>
                        <s:radio list="collections['collectionProperties.article.fullflag']" name="fullflag" ></s:radio>
                    </td>
                </tr>
                                <tr>
                    <td><s:text name="label.admin.article.edit.authorflag" /></td>
                    <td>
                        <s:radio list="collections['collectionProperties.article.authorflag']" name="authorflag" ></s:radio>
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.permission" /></td>
                    <td>
                        <s:radio list="collections['collectionProperties.article.permission']" name="permission" ></s:radio>
                    </td>
                </tr>
                 <tr>
                    <td><s:text name="label.admin.article.edit.firstflag" /></td>
                    <td>
                        <s:radio list="collections['collectionProperties.article.firstflag']" name="firstflag" ></s:radio>
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.postdate" /></td>
                    <td> 格式:yyyy/MM/dd hh:mm  例:2014/10/08 21:28
                        <s:textfield id="postdateStr" name="postdateStr" cssClass="tb-rounded" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><s:text name="label.admin.article.edit.seo" /></td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.usecustomizeinfotitle" /></td>
                    <td>
                        <s:radio list="collections['collectionProperties.boolean']" name="usecustomizeinfotitle" ></s:radio>
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.infotitle" /></td>
                    <td>
                        <s:textfield id="infotitle" name="infotitle" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.infokeywords" /></td>
                    <td>
                        <s:textfield id="infokeywords" name="infokeywords" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.infodescription" /></td>
                    <td>
                        <s:textfield id="infodescription" name="infodescription" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.usecustomizelisttitle" /></td>
                    <td>
                        <s:radio list="collections['collectionProperties.boolean']" name="usecustomizelisttitle" ></s:radio>
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.listtitle" /></td>
                    <td>
                        <s:textfield id="listtitle" name="listtitle" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.listkeywords" /></td>
                    <td>
                        <s:textfield id="listkeywords" name="listkeywords" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.listdescription" /></td>
                    <td>
                        <s:textfield id="listdescription" name="listdescription" cssClass="tb-rounded" /> 
                    </td>
                </tr>

                <tr>
                    <td colspan="2"><s:text name="label.admin.article.edit.statcaption" /></td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.dayvisit" /></td>
                    <td>
                        <s:textfield id="dayvisit" name="dayvisit" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.weekvisit" /></td>
                    <td>
                        <s:textfield id="weekvisit" name="weekvisit" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                                <tr>
                    <td><s:text name="label.admin.article.edit.monthvisit" /></td>
                    <td>
                        <s:textfield id="monthvisit" name="monthvisit" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.allvisit" /></td>
                    <td>
                        <s:textfield id="allvisit" name="allvisit" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                                <tr>
                    <td><s:text name="label.admin.article.edit.dayvote" /></td>
                    <td>
                        <s:textfield id="dayvote" name="dayvote" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.weekvote" /></td>
                    <td>
                        <s:textfield id="weekvote" name="weekvote" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                                <tr>
                    <td><s:text name="label.admin.article.edit.monthvote" /></td>
                    <td>
                        <s:textfield id="monthvote" name="monthvote" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.allvote" /></td>
                    <td>
                        <s:textfield id="allvote" name="allvote" cssClass="tb-rounded" /> 
                    </td>
                </tr>
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
    </s:form>
    </div>
    <jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
</body>
</html>
