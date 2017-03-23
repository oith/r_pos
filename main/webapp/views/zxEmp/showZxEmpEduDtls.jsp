<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8'%>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<c:set var='context' value='${pageContext.request.contextPath}'/>
<spring:message code='zxEmpEduDtls' text='Zx Emp Edu Dtls'/> | <a href='${context}/zxEmpEduDtl/create'><spring:message code='create.link.label'/>&NonBreakingSpace;<spring:message code='ZxEmpEduDtl' text='Zx Emp Edu Dtl'/></a>

<c:if test='${not empty zxEmp.zxEmpEduDtls}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
                <th></th>
                <th><spring:message code='version' text='Version'/></th>
                <th><spring:message code='zxLookupEduLvl' text='Zx Lookup Edu Lvl'/></th>
                <th><spring:message code='eduOrg' text='Edu Org'/></th>
                <th><spring:message code='fromDate' text='From Date'/></th>
                <th><spring:message code='toDate' text='To Date'/></th>
                <th><spring:message code='remarks' text='Remarks'/></th>
                <th><spring:message code='slNo' text='Sl No'/></th>
                <th><spring:message code='certificate' text='Certificate'/></th>
                <th><spring:message code='pic' text='Pic'/></th>
                <th><spring:message code='zxEmpWhoCheckedBy' text='Zx Emp Who Checked By'/></th>
                <th><spring:message code='crudType' text='Crud Type'/></th>
 
            </thead>
            <tbody>
                <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/> 
                <c:forEach items='${zxEmp.zxEmpEduDtls}' var='zxEmpEduDtls'  varStatus='loopStatus'>
                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><a href='${context}/zxEmpEduDtl/show/<c:out value='${zxEmpEduDtls.id}'/>'><spring:message code='show.link.label'/></a></td>
                        <td class='right'><c:out value='${zxEmpEduDtls.version}'/></td>
                        <td><c:out value='${zxEmpEduDtls.zxLookupEduLvl}'/></td>
                        <td><c:out value='${zxEmpEduDtls.eduOrg}'/></td>
                        <td class='center'><fmt:formatDate value='${zxEmpEduDtls.fromDate}' type='date' pattern='dd/MM/yyyy'/></td>
                        <td class='center'><fmt:formatDate value='${zxEmpEduDtls.toDate}' type='date' pattern='dd/MM/yyyy'/></td>
                        <td><c:out value='${zxEmpEduDtls.remarks}'/></td>
                        <td class='right'><c:out value='${zxEmpEduDtls.slNo}'/></td>
                        <td><c:url var='certificate' value='/zxEmpEduDtls/getFile/${zxEmpEduDtls.certificate}'/>
<a target='_blank' href='${certificate}'>${zxEmpEduDtls.certificate}</a></td>
                        <td><c:url var='pic' value='/zxEmpEduDtls/getPhotoSmall/${zxEmpEduDtls.pic}'/>
<img alt='${zxEmpEduDtls.pic}' src='${pic}'/></td>
                        <td><c:out value='${zxEmpEduDtls.zxEmpWhoCheckedBy!=null ? zxEmpEduDtls.zxEmpWhoCheckedBy :"N/A"}'/></td>
                        <td><c:out value='${zxEmpEduDtls.crudType}'/></td>

                        <td><a href='${context}/zxEmpEduDtl/edit/<c:out value='${zxEmpEduDtls.id}'/>'><spring:message code='edit.link.label'/></a></td>
                        <td><a href='${context}/zxEmpEduDtl/delete/<c:out value='${zxEmpEduDtls.id}'/>' onclick="return confirm('${confirmToDelete}');"><spring:message code='delete.link.label'/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>