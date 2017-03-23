<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8'%>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<c:set var='context' value='${pageContext.request.contextPath}'/>
<spring:message code='admSubModules' text='Adm Sub Modules'/> | <a href='${context}/admSubModule/create'><spring:message code='create.link.label'/>&NonBreakingSpace;<spring:message code='AdmSubModule' text='Adm Sub Module'/></a>

<c:if test='${not empty admModule.admSubModules}'>
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
                <th><spring:message code='displayIconClass' text='Display Icon Class'/></th>
                <th><spring:message code='crudType' text='Crud Type'/></th>
 
            </thead>
            <tbody>
                <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/> 
                <c:forEach items='${admModule.admSubModules}' var='admSubModules'  varStatus='loopStatus'>
                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><a href='${context}/admSubModule/show/<c:out value='${admSubModules.id}'/>'><spring:message code='show.link.label'/></a></td>
                        <td class='right'><c:out value='${admSubModules.version}'/></td>
                        <td><c:out value='${admSubModules.code}'/></td>
                        <td><c:out value='${admSubModules.fullName}'/></td>
                        <td><c:out value='${admSubModules.fullNameNative}'/></td>
                        <td class='center'><c:if test='${admSubModules.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admSubModules.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                        <td class='right'><c:out value='${admSubModules.slNo}'/></td>
                        <td><c:out value='${admSubModules.description}'/></td>
                        <td><c:out value='${admSubModules.displayIconClass}'/></td>
                        <td><c:out value='${admSubModules.crudType}'/></td>

                        <td><a href='${context}/admSubModule/edit/<c:out value='${admSubModules.id}'/>'><spring:message code='edit.link.label'/></a></td>
                        <td><a href='${context}/admSubModule/delete/<c:out value='${admSubModules.id}'/>' onclick="return confirm('${confirmToDelete}');"><spring:message code='delete.link.label'/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>