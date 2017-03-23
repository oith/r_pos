<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8'%>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<c:set var='context' value='${pageContext.request.contextPath}'/>
<spring:message code='posSalesDetails' text='Pos Sales Details'/> | <a href='${context}/posSalesDetail/create'><spring:message code='create.link.label'/>&NonBreakingSpace;<spring:message code='PosSalesDetail' text='Pos Sales Detail'/></a>

<c:if test='${not empty posSalesMaster.posSalesDetails}'>
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
                <c:forEach items='${posSalesMaster.posSalesDetails}' var='posSalesDetails'  varStatus='loopStatus'>
                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><a href='${context}/posSalesDetail/show/<c:out value='${posSalesDetails.id}'/>'><spring:message code='show.link.label'/></a></td>
                        <td class='right'><c:out value='${posSalesDetails.version}'/></td>
                        <td><c:out value='${posSalesDetails.posProduct!=null ? posSalesDetails.posProduct :"N/A"}'/></td>
                        <td class='right'><c:out value='${posSalesDetails.quantity}'/></td>
                        <td class='right'><c:out value='${posSalesDetails.unitPrice}'/></td>
                        <td class='right'><c:out value='${posSalesDetails.lineTotal}'/></td>
                        <td><c:out value='${posSalesDetails.crudType}'/></td>

                        <td><a href='${context}/posSalesDetail/edit/<c:out value='${posSalesDetails.id}'/>'><spring:message code='edit.link.label'/></a></td>
                        <td><a href='${context}/posSalesDetail/delete/<c:out value='${posSalesDetails.id}'/>' onclick="return confirm('${confirmToDelete}');"><spring:message code='delete.link.label'/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>