<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<c:set var='context' value='${pageContext.request.contextPath}'/>
<spring:message code='admLocations' text='Adm Locations'/> | <a href='${context}/admLocation/create'><spring:message
        code='default.create.link.label'/>&NonBreakingSpace;<spring:message code='AdmLocation' text='Adm Location'/></a>

<c:if test='${not empty admLocation.admLocations}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
            <th></th>
            <th><spring:message code='version' text='Version'/></th>
            <th><spring:message code='code' text='Code'/></th>
            <th><spring:message code='fullName' text='Full Name'/></th>
            <th><spring:message code='fullNameNative' text='Full Name Native'/></th>
            <th><spring:message code='emLocationType' text='Em Location Type'/></th>

            </thead>
            <tbody>
            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?'
                            var='confirmToDelete'/>
            <c:forEach items='${admLocation.admLocations}' var='admLocations' varStatus='loopStatus'>
                <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                    <td><a href='${context}/admLocation/show/<c:out value='${admLocations.id}'/>'><spring:message
                            code='show.link.label'/></a></td>
                    <td class='right'><c:out value='${admLocations.version}'/></td>
                    <td><c:out value='${admLocations.code}'/></td>
                    <td><c:out value='${admLocations.fullName}'/></td>
                    <td><c:out value='${admLocations.fullNameNative}'/></td>
                    <td class='center'><c:out value='${admLocations.emLocationType}'/></td>

                    <td><a href='${context}/admLocation/edit/<c:out value='${admLocations.id}'/>'><spring:message
                            code='default.button.edit.label'/></a></td>
                    <td><a href='${context}/admLocation/delete/<c:out value='${admLocations.id}'/>'
                           onclick="return confirm('${confirmToDelete}');"><spring:message
                            code='default.button.delete.label'/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>