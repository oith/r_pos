<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<c:set var='context' value='${pageContext.request.contextPath}'/>
<spring:message code='admReports' text='Adm Reports'/> | <a href='${context}/admReport/create'><spring:message
        code='default.create.link.label'/>&NonBreakingSpace;<spring:message code='AdmReport' text='Adm Report'/></a>

<c:if test='${not empty admModule.admReports}'>
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
            <th><spring:message code='fileName' text='File Name'/></th>
            <th><spring:message code='supportFormats' text='Support Formats'/></th>

            </thead>
            <tbody>
            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?'
                            var='confirmToDelete'/>
            <c:forEach items='${admModule.admReports}' var='admReports' varStatus='loopStatus'>
                <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                    <td><a href='${context}/admReport/show/<c:out value='${admReports.id}'/>'><spring:message
                            code='show.link.label'/></a></td>
                    <td class='right'><c:out value='${admReports.version}'/></td>
                    <td><c:out value='${admReports.code}'/></td>
                    <td><c:out value='${admReports.fullName}'/></td>
                    <td><c:out value='${admReports.fullNameNative}'/></td>
                    <td class='center'><c:if test='${admReports.isActive}'><spring:message code='default.boolean.true'
                                                                                           text='YES'/></c:if><c:if
                            test='${!admReports.isActive}'><spring:message code='default.boolean.false'
                                                                           text='NO'/></c:if></td>
                    <td class='right'><c:out value='${admReports.slNo}'/></td>
                    <td><c:out value='${admReports.description}'/></td>
                    <td><c:out value='${admReports.fileName}'/></td>
                    <td class='center'><c:out value='${admReports.supportFormats}'/></td>

                    <td><a href='${context}/admReport/edit/<c:out value='${admReports.id}'/>'><spring:message
                            code='default.button.edit.label'/></a></td>
                    <td><a href='${context}/admReport/delete/<c:out value='${admReports.id}'/>'
                           onclick="return confirm('${confirmToDelete}');"><spring:message
                            code='default.button.delete.label'/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>