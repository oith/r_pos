<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8'%>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<c:set var='context' value='${pageContext.request.contextPath}'/>
<spring:message code='zxEmpTrainingDtls' text='Zx Emp Training Dtls'/> | <a href='${context}/zxEmpTrainingDtl/create'><spring:message code='create.link.label'/>&NonBreakingSpace;<spring:message code='ZxEmpTrainingDtl' text='Zx Emp Training Dtl'/></a>

<c:if test='${not empty zxEmp.zxEmpTrainingDtls}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
                <th></th>
                <th><spring:message code='version' text='Version'/></th>
                <th><spring:message code='zxLookupTraining' text='Zx Lookup Training'/></th>
                <th><spring:message code='trainingOrg' text='Training Org'/></th>
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
                <c:forEach items='${zxEmp.zxEmpTrainingDtls}' var='zxEmpTrainingDtls'  varStatus='loopStatus'>
                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><a href='${context}/zxEmpTrainingDtl/show/<c:out value='${zxEmpTrainingDtls.id}'/>'><spring:message code='show.link.label'/></a></td>
                        <td class='right'><c:out value='${zxEmpTrainingDtls.version}'/></td>
                        <td><c:out value='${zxEmpTrainingDtls.zxLookupTraining}'/></td>
                        <td><c:out value='${zxEmpTrainingDtls.trainingOrg}'/></td>
                        <td class='center'><fmt:formatDate value='${zxEmpTrainingDtls.fromDate}' type='date' pattern='dd/MM/yyyy'/></td>
                        <td class='center'><fmt:formatDate value='${zxEmpTrainingDtls.toDate}' type='date' pattern='dd/MM/yyyy'/></td>
                        <td><c:out value='${zxEmpTrainingDtls.remarks}'/></td>
                        <td class='right'><c:out value='${zxEmpTrainingDtls.slNo}'/></td>
                        <td><c:url var='certificate' value='/zxEmpTrainingDtls/getFile/${zxEmpTrainingDtls.certificate}'/>
<a target='_blank' href='${certificate}'>${zxEmpTrainingDtls.certificate}</a></td>
                        <td><c:url var='pic' value='/zxEmpTrainingDtls/getPhotoSmall/${zxEmpTrainingDtls.pic}'/>
<img alt='${zxEmpTrainingDtls.pic}' src='${pic}'/></td>
                        <td><c:out value='${zxEmpTrainingDtls.zxEmpWhoCheckedBy!=null ? zxEmpTrainingDtls.zxEmpWhoCheckedBy :"N/A"}'/></td>
                        <td><c:out value='${zxEmpTrainingDtls.crudType}'/></td>

                        <td><a href='${context}/zxEmpTrainingDtl/edit/<c:out value='${zxEmpTrainingDtls.id}'/>'><spring:message code='edit.link.label'/></a></td>
                        <td><a href='${context}/zxEmpTrainingDtl/delete/<c:out value='${zxEmpTrainingDtls.id}'/>' onclick="return confirm('${confirmToDelete}');"><spring:message code='delete.link.label'/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>