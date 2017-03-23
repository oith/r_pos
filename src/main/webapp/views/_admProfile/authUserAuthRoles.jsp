<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<spring:message code='authUserAuthRoles' text='Auth User Auth Roles'/> | <a
        href='${pageContext.request.contextPath}/authUserAuthRole/create'><spring:message
        code='default.create.link.label'/>&NonBreakingSpace;<spring:message code='AuthUserAuthRole'
                                                                            text='Auth User Auth Role'/></a>

<c:if test='${not empty authUser.authUserAuthRoles}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
            <tr>
                <td></td>
                <td><spring:message code='id' text='Id'/></td>
                <td><spring:message code='emIncludeExclude' text='Em Include Exclude'/></td>

            </tr>
            </thead>
            <tbody>
            <c:forEach items='${authUser.authUserAuthRoles}' var='authUserAuthRoles' varStatus='loopStatus'>

                <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                    <td>
                        <a href='${pageContext.request.contextPath}/authUserAuthRole/show/<c:out value='${authUserAuthRoles.id}'/>'><spring:message
                                code='show.link.label'/></a></td>
                    <td><c:out value='${authUserAuthRoles.id}'/></td>
                    <td><c:out value='${authUserAuthRoles.emIncludeExclude}'/></td>

                    <td>
                        <a href='${pageContext.request.contextPath}/authUserAuthRole/edit/<c:out value='${authUserAuthRoles.id}'/>'><spring:message
                                code='default.button.edit.label'/></a></td>
                    <td>
                        <a href='${pageContext.request.contextPath}/authUserAuthRole/delete/<c:out value='${authUserAuthRoles.id}'/>'
                           onclick='return confirm(' Are you sure to delete?');' ><spring:message
                            code='default.button.delete.label'/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>