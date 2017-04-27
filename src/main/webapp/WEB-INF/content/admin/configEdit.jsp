<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
<s:form namespace="/admin" action="configEdit" method="post" validate="true">
        <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
<table class="grid" align="center" cellspacing="1" width="800">
<caption><s:text name="label.admin.config.edit.caption1" /></caption>
<tbody>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.uri" /></td>
       <td class="even"><s:textfield name ="uri" size="40" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.mobileUri" /></td>
       <td class="even"><s:textfield name ="mobileUri" size="40" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.filePath" /></td>
       <td class="even"><s:textfield name ="filePath" size="40" maxlength="100" cssClass="text" theme="simple"/><span class="hottext">填写绝对路径</span></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.relativeIamgePath" /></td>
       <td class="even"><s:textfield name ="relativeIamgePath" size="25" maxlength="100"  cssClass="text" theme="simple"/><span class="hottext">请填写相对路径</span></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.themeName" /></td>
       <td class="even"><s:textfield name ="themeName" size="25" maxlength="30"  cssClass="text" theme="simple"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.countPerPage" /></td>
       <td class="even"><s:textfield name ="countPerPage" size="25" maxlength="20"  cssClass="text"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.maxBookcase" /></td>
       <td class="even"><s:textfield name ="maxBookcase" size="25" maxlength="20"  cssClass="text"/></td>
    </tr>
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.txtEncoding" /></td>
       <td class="even"><s:textfield name ="txtEncoding" size="25" maxlength="20"  cssClass="text"/></td>
    </tr>
    
</tbody>
</table>
<br />
<table class="grid" align="center" cellspacing="1" width="800">
<caption><s:text name="label.admin.config.edit.caption2" /></caption>
<tbody>
    <tr align="left" valign="middle">
        <td class="odd" width="30%"><s:text name="label.admin.config.edit.dburl" /></td>
       <td class="even"><s:textfield name ="dburl" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>
    <tr align="left" valign="middle">
        <td class="odd" width="30%"><s:text name="label.admin.config.edit.username" /></td>
        <td class="even">
            <s:textfield name ="username" size="25" maxlength="100" cssClass="text" theme="simple"/>
        </td>
    </tr>
    <tr align="left" valign="middle">
        <td class="odd" width="30%"><s:text name="label.admin.config.edit.password" /></td>
        <td class="even">
            <s:textfield name ="password" size="25" maxlength="100" cssClass="text" theme="simple"/><span class="hottext">留空表示不修改密码</span>
        </td>
    </tr>
</tbody>
</table>
<br />
<table class="grid" align="center" cellspacing="1" width="800">
<caption><s:text name="label.admin.config.edit.caption3" /></caption>
<tbody>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.cleanUrl" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="cleanUrl" ></s:radio>
       </td>
    </tr>
    
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.enableCleanImageUrl"/></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="enableCleanImageUrl" ></s:radio>
       </td>
    </tr>
    
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.enablePinyinUrl"/></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="enablePinyinUrl" ></s:radio>
       </td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.adEffective" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="adEffective" ></s:radio>
       </td>
    </tr>
    
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.compressEffective" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="compressEffective" ></s:radio>
       </td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.addReviewWithoutLogin" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="addReviewWithoutLogin" ></s:radio>
       </td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.enableMobileSite" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="enableMobileSite" ></s:radio>
       </td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.judgmobilesitebydomian" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="judgmobilesitebydomian"  theme = "simple"></s:radio>
           &nbsp;&nbsp;<span class="hottext">否的话通过手机来判断</span>
       </td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.mobilesitedomian" /></td>
       <td class="even"><s:textfield name ="mobilesitedomian" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.enablePseudo" /></td>
       <td class="even"><s:radio list="collections['collectionProperties.boolean']" name="enablePseudo" ></s:radio></td>
    </tr>
    
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.enableQQLogin" /></td>
       <td class="even"><s:radio list="collections['collectionProperties.boolean']" name="enableQQLogin" ></s:radio></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.appId" /></td>
       <td class="even"><s:textfield name ="appId" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.appKey" /></td>
       <td class="even"><s:textfield name ="appKey" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.enableChapterIndexPage" /></td>
       <td class="even"><s:radio list="collections['collectionProperties.boolean']" name="enableChapterIndexPage" ></s:radio></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.enableGenerateHtmlFile" /></td>
       <td class="even"><s:radio list="collections['collectionProperties.boolean']" name="enableGenerateHtmlFile" ></s:radio></td>
    </tr>
    
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.enableMailAuth" /></td>
       <td class="even"><s:radio list="collections['collectionProperties.boolean']" name="enableMailAuth" ></s:radio></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.mailSmtpAuth" /></td>
       <td class="even"><s:radio list="collections['collectionProperties.boolean']" name="mailSmtpAuth" ></s:radio></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.mailSmtpStarttlsEnable" /></td>
       <td class="even"><s:radio list="collections['collectionProperties.boolean']" name="mailSmtpStarttlsEnable" ></s:radio></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.mailSmtpHost" /></td>
       <td class="even"><s:textfield name ="mailSmtpHost" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.mailSmtpPort" /></td>
       <td class="even"><s:textfield name ="mailSmtpPort" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.mailSmtpUsername" /></td>
       <td class="even"><s:textfield name ="mailSmtpUsername" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>
    
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.mailSmtpPassword" /></td>
       <td class="even"><s:textfield name ="mailSmtpPassword" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.mailSmtpFrom" /></td>
       <td class="even"><s:textfield name ="mailSmtpFrom" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.maxSubscribe" /></td>
       <td class="even"><s:textfield name ="maxSubscribe" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>    
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.sendSubscribeInteval" /></td>
       <td class="even"><s:textfield name ="sendSubscribeInteval" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.enableSingleBook" /></td>
       <td class="even"><s:radio list="collections['collectionProperties.boolean']" name="enableSingleBook" ></s:radio></td>
    </tr>
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.rootDomain" /></td>
       <td class="even"><s:textfield name ="rootDomain" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.excludeSubDomain" /></td>
       <td class="even"><s:textfield name ="excludeSubDomain" size="25" cssClass="text" theme="simple"/></td>
    </tr>    
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.reloadSingleBookInterval" /></td>
       <td class="even"><s:textfield name ="reloadSingleBookInterval" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.createSiteMap" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="createSiteMap" ></s:radio>
       </td>
    </tr>
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.siteMapType" /></td>
       <td class="even"><s:textfield name ="siteMapType" size="25" cssClass="text" theme="simple"/><span class="hottext">支持html和xml格式</span></td>
    </tr>
        <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.xmlSiteMapListWithNoPageURL" /></td>
       <td class="even"><s:textfield name ="xmlSiteMapListWithNoPageURL" size="25" cssClass="text" theme="simple"/></td>
    </tr>
        <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.xmlSiteMapListURL" /></td>
       <td class="even"><s:textfield name ="xmlSiteMapListURL" size="25" cssClass="text" theme="simple"/></td>
    </tr>    
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.xmlSiteMapInfoURL" /></td>
       <td class="even"><s:textfield name ="xmlSiteMapInfoURL" size="25" cssClass="text" theme="simple"/></td>
    </tr>
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.xmlSiteMapChaterListURL" /></td>
       <td class="even"><s:textfield name ="xmlSiteMapChaterListURL" size="25" cssClass="text" theme="simple"/></td>
    </tr>    
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.xmlSiteMapReaderURL" /></td>
       <td class="even"><s:textfield name ="xmlSiteMapReaderURL" size="25" cssClass="text" theme="simple"/></td>
    </tr>        
    
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.filterKeyWord" /></td>
       <td class="even"><s:textfield name ="filterKeyWord" size="25" cssClass="text" theme="simple"/></td>
    </tr>
</tbody>
</table>
<br />
<table class="grid" align="center" cellspacing="1" width="800">
<caption><s:text name="label.admin.config.edit.caption4" /></caption>
<tbody>
    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.cacheEffective" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="cacheEffective" ></s:radio>
       </td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.gzipEffective" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="gzipEffective" theme = "simple"></s:radio> 
           &nbsp;&nbsp;<span class="hottext">压缩输出有利于提高浏览速度</span>
       </td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.createIndexPage" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="createIndexPage" ></s:radio>
       </td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="30%"><s:text name="label.admin.config.edit.enableCacheArticleCount" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="enableCacheArticleCount" ></s:radio>
       </td>
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
