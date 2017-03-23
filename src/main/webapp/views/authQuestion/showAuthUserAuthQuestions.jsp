<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8'%>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<c:set var='context' value='${pageContext.request.contextPath}'/>
<spring:message code='authUserAuthQuestions' text='Auth User Auth Questions'/> | <a href='${context}/authUserAuthQuestion/create'><spring:message code='create.link.label'/>&NonBreakingSpace;<spring:message code='AuthUserAuthQuestion' text='Auth User Auth Question'/></a>

<c:if test='${not empty authQuestion.authUserAuthQuestions}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
                <th></th>
                <th><spring:message code='id' text='Id'/></th>
                <th><spring:message code='answer' text='Answer'/></th>
 
            </thead>
            <tbody>
                <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/> 
                <c:forEach items='${authQuestion.authUserAuthQuestions}' var='authUserAuthQuestions'  varStatus='loopStatus'>
                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><a href='${context}/authUserAuthQuestion/show/<c:out value='${authUserAuthQuestions.id}'/>'><spring:message code='show.link.label'/></a></td>
                        <td><c:out value='${authUserAuthQuestions.id}'/></td>
                        <td><c:out value='${authUserAuthQuestions.answer}'/></td>

                        <td><a href='${context}/authUserAuthQuestion/edit/<c:out value='${authUserAuthQuestions.id}'/>'><spring:message code='edit.link.label'/></a></td>
                        <td><a href='${context}/authUserAuthQuestion/delete/<c:out value='${authUserAuthQuestions.id}'/>' onclick="return confirm('${confirmToDelete}');"><spring:message code='delete.link.label'/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>