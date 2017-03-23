<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8'%>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<c:set var='context' value='${pageContext.request.contextPath}'/>
<spring:message code='posPurchaseDetails' text='Pos Purchase Details'/> | <a href='${context}/posPurchaseDetail/create'><spring:message code='create.link.label'/>&NonBreakingSpace;<spring:message code='PosPurchaseDetail' text='Pos Purchase Detail'/></a>

<c:if test='${not empty posPurchaseMaster.posPurchaseDetails}'>
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
                <c:forEach items='${posPurchaseMaster.posPurchaseDetails}' var='posPurchaseDetails'  varStatus='loopStatus'>
                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><a href='${context}/posPurchaseDetail/show/<c:out value='${posPurchaseDetails.id}'/>'><spring:message code='show.link.label'/></a></td>
                        <td class='right'><c:out value='${posPurchaseDetails.version}'/></td>
                        <td><c:out value='${posPurchaseDetails.posProduct!=null ? posPurchaseDetails.posProduct :"N/A"}'/></td>
                        <td class='right'><c:out value='${posPurchaseDetails.quantity}'/></td>
                        <td class='right'><c:out value='${posPurchaseDetails.unitPrice}'/></td>
                        <td class='right'><c:out value='${posPurchaseDetails.lineTotal}'/></td>
                        <td><c:out value='${posPurchaseDetails.crudType}'/></td>

                        <td><a href='${context}/posPurchaseDetail/edit/<c:out value='${posPurchaseDetails.id}'/>'><spring:message code='edit.link.label'/></a></td>
                        <td><a href='${context}/posPurchaseDetail/delete/<c:out value='${posPurchaseDetails.id}'/>' onclick="return confirm('${confirmToDelete}');"><spring:message code='delete.link.label'/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>