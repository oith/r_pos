<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<spring:message code='authUserEnvKeys' text='Auth User Env Keys'/> | <a
        href='${pageContext.request.contextPath}/authUserEnvKey/create'><spring:message
        code='default.create.link.label'/>&NonBreakingSpace;<spring:message code='AuthUserEnvKey'
                                                                            text='Auth User Env Key'/></a>

<c:if test='${not empty authUser.authUserEnvKeys}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
            <tr>
                <td></td>
                <td><spring:message code='version' text='Version'/></td>
                <td><spring:message code='envKey' text='Env Key'/></td>
                <td><spring:message code='envValue' text='Env Value'/></td>

            </tr>
            </thead>
            <tbody>
            <c:forEach items='${authUser.authUserEnvKeys}' var='authUserEnvKeys' varStatus='loopStatus'>

                <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                    <td>
                        <a href='${pageContext.request.contextPath}/authUserEnvKey/show/<c:out value='${authUserEnvKeys.id}'/>'><spring:message
                                code='show.link.label'/></a></td>
                    <td><c:out value='${authUserEnvKeys.version}'/></td>
                    <td><c:out value='${authUserEnvKeys.envKey}'/></td>
                    <td><c:out value='${authUserEnvKeys.envValue}'/></td>

                    <td>
                        <a href='${pageContext.request.contextPath}/authUserEnvKey/edit/<c:out value='${authUserEnvKeys.id}'/>'><spring:message
                                code='default.button.edit.label'/></a></td>
                    <td>
                        <a href='${pageContext.request.contextPath}/authUserEnvKey/delete/<c:out value='${authUserEnvKeys.id}'/>'
                           onclick='return confirm(' Are you sure to delete?');' ><spring:message
                            code='default.button.delete.label'/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>