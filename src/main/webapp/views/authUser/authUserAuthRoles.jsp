<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<c:set var='context' value='${pageContext.request.contextPath}'/>
<spring:message code='authRoles' text='Auth User Auth Roles'/> | <a href='${context}/authRole/create'>
    <spring:message code='default.create.link.label'/>&NonBreakingSpace;<spring:message code='AuthRole'
                                                                                        text='Auth User Auth Role'/></a>

<c:if test='${not empty authUser.authRoles}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
            <th></th>
            <th><spring:message code='id' text='Id'/></th>
            <th><spring:message code='emIncludeExclude' text='Em Include Exclude'/></th>

            </thead>
            <tbody>
            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?'
                            var='confirmToDelete'/>
            <c:forEach items='${authUser.authRoles}' var='authRoles' varStatus='loopStatus'>
                <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                    <td>
                        <a href='${context}/authRole/show/<c:out value='${authRoles.id}'/>'><spring:message
                                code='show.link.label'/></a></td>
                    <td><c:out value='${authRoles.id}'/></td>
                    <td class='center'><c:out value='${authRoles.emIncludeExclude}'/></td>

                    <td>
                        <a href='${context}/authRole/edit/<c:out value='${authRoles.id}'/>'><spring:message
                                code='default.button.edit.label'/></a></td>
                    <td><a href='${context}/authRole/delete/<c:out value='${authRoles.id}'/>'
                           onclick="return confirm('${confirmToDelete}');"><spring:message
                            code='default.button.delete.label'/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>