<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<c:set var='context' value='${pageContext.request.contextPath}'/>
<spring:message code='admProcesss' text='Adm Processs'/> | <a href='${context}/admProcess/create'><spring:message
        code='default.create.link.label'/>&NonBreakingSpace;<spring:message code='AdmProcess' text='Adm Process'/></a>

<c:if test='${not empty admSubModule.admProcesss}'>
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
            <th><spring:message code='cmd' text='Cmd'/></th>
            <th><spring:message code='query' text='Query'/></th>
            <th><spring:message code='queryAlias' text='Query Alias'/></th>
            <th><spring:message code='processBtns' text='Process Btns'/></th>

            </thead>
            <tbody>
            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?'
                            var='confirmToDelete'/>
            <c:forEach items='${admSubModule.admProcesss}' var='admProcesss' varStatus='loopStatus'>
                <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                    <td><a href='${context}/admProcess/show/<c:out value='${admProcesss.id}'/>'><spring:message
                            code='show.link.label'/></a></td>
                    <td class='right'><c:out value='${admProcesss.version}'/></td>
                    <td><c:out value='${admProcesss.code}'/></td>
                    <td><c:out value='${admProcesss.fullName}'/></td>
                    <td><c:out value='${admProcesss.fullNameNative}'/></td>
                    <td class='center'><c:if test='${admProcesss.isActive}'><spring:message code='default.boolean.true'
                                                                                            text='YES'/></c:if><c:if
                            test='${!admProcesss.isActive}'><spring:message code='default.boolean.false'
                                                                            text='NO'/></c:if></td>
                    <td class='right'><c:out value='${admProcesss.slNo}'/></td>
                    <td><c:out value='${admProcesss.description}'/></td>
                    <td><c:out value='${admProcesss.cmd}'/></td>
                    <td><c:out value='${admProcesss.query}'/></td>
                    <td><c:out value='${admProcesss.queryAlias}'/></td>
                    <td><c:out value='${admProcesss.processBtns}'/></td>

                    <td><a href='${context}/admProcess/edit/<c:out value='${admProcesss.id}'/>'><spring:message
                            code='default.button.edit.label'/></a></td>
                    <td><a href='${context}/admProcess/delete/<c:out value='${admProcesss.id}'/>'
                           onclick="return confirm('${confirmToDelete}');"><spring:message
                            code='default.button.delete.label'/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>