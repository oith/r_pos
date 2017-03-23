<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8'%>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<c:set var='context' value='${pageContext.request.contextPath}'/>
<spring:message code='posAdjustmentDetails' text='Pos Adjustment Details'/> | <a href='${context}/posAdjustmentDetail/create'><spring:message code='create.link.label'/>&NonBreakingSpace;<spring:message code='PosAdjustmentDetail' text='Pos Adjustment Detail'/></a>

<c:if test='${not empty posAdjustmentPurchaseReturnMaster.posAdjustmentDetails}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
                <th></th>
                <th><spring:message code='version' text='Version'/></th>
                <th><spring:message code='posProduct' text='Pos Product'/></th>
                <th><spring:message code='quantity' text='Quantity'/></th>
                <th><spring:message code='unitPrice' text='Unit Price'/></th>
                <th><spring:message code='lineTotal' text='Line Total'/></th>
                <th><spring:message code='crudType' text='Crud Type'/></th>
 
            </thead>
            <tbody>
                <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/> 
                <c:forEach items='${posAdjustmentPurchaseReturnMaster.posAdjustmentDetails}' var='posAdjustmentDetails'  varStatus='loopStatus'>
                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><a href='${context}/posAdjustmentDetail/show/<c:out value='${posAdjustmentDetails.id}'/>'><spring:message code='show.link.label'/></a></td>
                        <td class='right'><c:out value='${posAdjustmentDetails.version}'/></td>
                        <td><c:out value='${posAdjustmentDetails.posProduct!=null ? posAdjustmentDetails.posProduct :"N/A"}'/></td>
                        <td class='right'><c:out value='${posAdjustmentDetails.quantity}'/></td>
                        <td class='right'><c:out value='${posAdjustmentDetails.unitPrice}'/></td>
                        <td class='right'><c:out value='${posAdjustmentDetails.lineTotal}'/></td>
                        <td><c:out value='${posAdjustmentDetails.crudType}'/></td>

                        <td><a href='${context}/posAdjustmentDetail/edit/<c:out value='${posAdjustmentDetails.id}'/>'><spring:message code='edit.link.label'/></a></td>
                        <td><a href='${context}/posAdjustmentDetail/delete/<c:out value='${posAdjustmentDetails.id}'/>' onclick="return confirm('${confirmToDelete}');"><spring:message code='delete.link.label'/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>