<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8'%>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<c:set var='context' value='${pageContext.request.contextPath}'/>
<spring:message code='admMenuCommons' text='Adm Menu Commons'/> | <a href='${context}/admMenuCommon/create'><spring:message code='create.link.label'/>&NonBreakingSpace;<spring:message code='AdmMenuCommon' text='Adm Menu Common'/></a>

<c:if test='${not empty admSubModule.admMenuCommons}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
                <th></th>
                <th><spring:message code='version' text='Version'/></th>
                <th><spring:message code='code' text='Code'/></th>
                <th><spring:message code='fullName' text='Full Name'/></th>
                <th><spring:message code='fullNameNative' text='Full Name Native'/></th>
                <th><spring:message code='isActive' text='Is Active'/></th>
                <th><spring:message code='slNo' text='Sl No'/></th>
                <th><spring:message code='description' text='Description'/></th>
                <th><spring:message code='tooltip' text='Tooltip'/></th>
                <th><spring:message code='displayIconClass' text='Display Icon Class'/></th>
                <th><spring:message code='isExternal' text='Is External'/></th>
                <th><spring:message code='isOpenInNewTab' text='Is Open In New Tab'/></th>
                <th><spring:message code='crudType' text='Crud Type'/></th>
 
            </thead>
            <tbody>
                <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/> 
                <c:forEach items='${admSubModule.admMenuCommons}' var='admMenuCommons'  varStatus='loopStatus'>
                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><a href='${context}/admMenuCommon/show/<c:out value='${admMenuCommons.id}'/>'><spring:message code='show.link.label'/></a></td>
                        <td class='right'><c:out value='${admMenuCommons.version}'/></td>
                        <td><c:out value='${admMenuCommons.code}'/></td>
                        <td><c:out value='${admMenuCommons.fullName}'/></td>
                        <td><c:out value='${admMenuCommons.fullNameNative}'/></td>
                        <td class='center'><c:if test='${admMenuCommons.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admMenuCommons.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                        <td class='right'><c:out value='${admMenuCommons.slNo}'/></td>
                        <td><c:out value='${admMenuCommons.description}'/></td>
                        <td><c:out value='${admMenuCommons.tooltip}'/></td>
                        <td><c:out value='${admMenuCommons.displayIconClass}'/></td>
                        <td class='center'><c:if test='${admMenuCommons.isExternal}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admMenuCommons.isExternal}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                        <td class='center'><c:if test='${admMenuCommons.isOpenInNewTab}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admMenuCommons.isOpenInNewTab}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                        <td><c:out value='${admMenuCommons.crudType}'/></td>

                        <td><a href='${context}/admMenuCommon/edit/<c:out value='${admMenuCommons.id}'/>'><spring:message code='edit.link.label'/></a></td>
                        <td><a href='${context}/admMenuCommon/delete/<c:out value='${admMenuCommons.id}'/>' onclick="return confirm('${confirmToDelete}');"><spring:message code='delete.link.label'/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>