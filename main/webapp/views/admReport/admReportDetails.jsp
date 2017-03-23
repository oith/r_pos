<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<c:set var='context' value='${pageContext.request.contextPath}'/>
<spring:message code='admReportDetails' text='Adm Report Details'/> | <a href='${context}/admReportDetail/create'>
    <spring:message code='default.create.link.label'/>&NonBreakingSpace;<spring:message code='AdmReportDetail'
                                                                                        text='Adm Report Detail'/></a>

<c:if test='${not empty admReport.admReportDetails}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
            <th></th>
            <th><spring:message code='version' text='Version'/></th>
            <th><spring:message code='slNo' text='Sl No'/></th>
            <th><spring:message code='admParam' text='Adm Param'/></th>
            <th><spring:message code='isActive' text='Is Active'/></th>
            <th><spring:message code='isMandatory' text='Is Mandatory'/></th>
            <th><spring:message code='defaultVal' text='Default Val'/></th>
            <th><spring:message code='helpText' text='Help Text'/></th>

            </thead>
            <tbody>
            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?'
                            var='confirmToDelete'/>
            <c:forEach items='${admReport.admReportDetails}' var='admReportDetails' varStatus='loopStatus'>
                <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                    <td>
                        <a href='${context}/admReportDetail/show/<c:out value='${admReportDetails.id}'/>'><spring:message
                                code='show.link.label'/></a></td>
                    <td class='right'><c:out value='${admReportDetails.version}'/></td>
                    <td class='right'><c:out value='${admReportDetails.slNo}'/></td>
                    <td><c:out value='${admReportDetails.admParam}'/></td>
                    <td class='center'><c:if test='${admReportDetails.isActive}'><spring:message
                            code='default.boolean.true' text='YES'/></c:if><c:if
                            test='${!admReportDetails.isActive}'><spring:message code='default.boolean.false'
                                                                                 text='NO'/></c:if></td>
                    <td class='center'><c:if test='${admReportDetails.isMandatory}'><spring:message
                            code='default.boolean.true' text='YES'/></c:if><c:if
                            test='${!admReportDetails.isMandatory}'><spring:message code='default.boolean.false'
                                                                                    text='NO'/></c:if></td>
                    <td><c:out value='${admReportDetails.defaultVal}'/></td>
                    <td><c:out value='${admReportDetails.helpText}'/></td>

                    <td>
                        <a href='${context}/admReportDetail/edit/<c:out value='${admReportDetails.id}'/>'><spring:message
                                code='default.button.edit.label'/></a></td>
                    <td><a href='${context}/admReportDetail/delete/<c:out value='${admReportDetails.id}'/>'
                           onclick="return confirm('${confirmToDelete}');"><spring:message
                            code='default.button.delete.label'/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>