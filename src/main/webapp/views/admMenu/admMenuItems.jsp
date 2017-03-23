<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<c:set var='context' value='${pageContext.request.contextPath}'/>
<spring:message code='admMenuItems' text='Adm Menu Items'/> | <a href='${context}/admMenuItem/create'><spring:message
        code='default.create.link.label'/>&NonBreakingSpace;<spring:message code='AdmMenuItem'
                                                                            text='Adm Menu Item'/></a>

<c:if test='${not empty admMenu.admMenuItems}'>
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
            <th><spring:message code='tooltip' text='Tooltip'/></th>
            <th><spring:message code='isExternal' text='Is External'/></th>
            <th><spring:message code='isOpenInNewTab' text='Is Open In New Tab'/></th>
            <th><spring:message code='urlPath' text='Url Path'/></th>
            <th><spring:message code='displayIconClass' text='Display Icon Class'/></th>

            </thead>
            <tbody>
            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?'
                            var='confirmToDelete'/>
            <c:forEach items='${admMenu.admMenuItems}' var='admMenuItems' varStatus='loopStatus'>
                <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                    <td><a href='${context}/admMenuItem/show/<c:out value='${admMenuItems.id}'/>'><spring:message
                            code='show.link.label'/></a></td>
                    <td class='right'><c:out value='${admMenuItems.version}'/></td>
                    <td><c:out value='${admMenuItems.code}'/></td>
                    <td><c:out value='${admMenuItems.fullName}'/></td>
                    <td><c:out value='${admMenuItems.fullNameNative}'/></td>
                    <td class='center'><c:if test='${admMenuItems.isActive}'><spring:message code='default.boolean.true'
                                                                                             text='YES'/></c:if><c:if
                            test='${!admMenuItems.isActive}'><spring:message code='default.boolean.false'
                                                                             text='NO'/></c:if></td>
                    <td class='right'><c:out value='${admMenuItems.slNo}'/></td>
                    <td><c:out value='${admMenuItems.description}'/></td>
                    <td><c:out value='${admMenuItems.tooltip}'/></td>
                    <td class='center'><c:if test='${admMenuItems.isExternal}'><spring:message
                            code='default.boolean.true' text='YES'/></c:if><c:if
                            test='${!admMenuItems.isExternal}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                    <td class='center'><c:if test='${admMenuItems.isOpenInNewTab}'><spring:message
                            code='default.boolean.true' text='YES'/></c:if><c:if test='${!admMenuItems.isOpenInNewTab}'><spring:message
                            code='default.boolean.false' text='NO'/></c:if></td>
                    <td><c:out value='${admMenuItems.urlPath}'/></td>
                    <td><c:out value='${admMenuItems.displayIconClass}'/></td>

                    <td><a href='${context}/admMenuItem/edit/<c:out value='${admMenuItems.id}'/>'><spring:message
                            code='default.button.edit.label'/></a></td>
                    <td><a href='${context}/admMenuItem/delete/<c:out value='${admMenuItems.id}'/>'
                           onclick="return confirm('${confirmToDelete}');"><spring:message
                            code='default.button.delete.label'/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>