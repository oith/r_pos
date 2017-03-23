<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8'%>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<c:set var='context' value='${pageContext.request.contextPath}'/>
<spring:message code='admProcessDetails' text='Adm Process Details'/> | <a href='${context}/admProcessDetail/create'><spring:message code='create.link.label'/>&NonBreakingSpace;<spring:message code='AdmProcessDetail' text='Adm Process Detail'/></a>

<c:if test='${not empty admProcess.admProcessDetails}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
                <th></th>
                <th><spring:message code='version' text='Version'/></th>
                <th><spring:message code='admParam' text='Adm Param'/></th>
                <th><spring:message code='zoneType' text='Zone Type'/></th>
                <th><spring:message code='isActive' text='Is Active'/></th>
                <th><spring:message code='isMandatory' text='Is Mandatory'/></th>
                <th><spring:message code='defaultVal' text='Default Val'/></th>
                <th><spring:message code='helpText' text='Help Text'/></th>
                <th><spring:message code='slNo' text='Sl No'/></th>
                <th><spring:message code='crudType' text='Crud Type'/></th>
 
            </thead>
            <tbody>
                <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/> 
                <c:forEach items='${admProcess.admProcessDetails}' var='admProcessDetails'  varStatus='loopStatus'>
                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><a href='${context}/admProcessDetail/show/<c:out value='${admProcessDetails.id}'/>'><spring:message code='show.link.label'/></a></td>
                        <td class='right'><c:out value='${admProcessDetails.version}'/></td>
                        <td><c:out value='${admProcessDetails.admParam}'/></td>
                        <td class='center'><c:out value='${admProcessDetails.zoneType}'/></td>
                        <td class='center'><c:if test='${admProcessDetails.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admProcessDetails.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                        <td class='center'><c:if test='${admProcessDetails.isMandatory}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admProcessDetails.isMandatory}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                        <td><c:out value='${admProcessDetails.defaultVal}'/></td>
                        <td><c:out value='${admProcessDetails.helpText}'/></td>
                        <td class='right'><c:out value='${admProcessDetails.slNo}'/></td>
                        <td><c:out value='${admProcessDetails.crudType}'/></td>

                        <td><a href='${context}/admProcessDetail/edit/<c:out value='${admProcessDetails.id}'/>'><spring:message code='edit.link.label'/></a></td>
                        <td><a href='${context}/admProcessDetail/delete/<c:out value='${admProcessDetails.id}'/>' onclick="return confirm('${confirmToDelete}');"><spring:message code='delete.link.label'/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>