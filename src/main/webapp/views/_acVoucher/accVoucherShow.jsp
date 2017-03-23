<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<!DOCTYPE html>
<html>
<c:set var='context' value='${pageContext.request.contextPath}'/>
<head>
    <title><spring:message code='default.button.show.label' text='Show'/>&nbsp;<spring:message code='acVoucherMst'
                                                                                               text='Ac Voucher Mst'/></title>
</head>
<body>
<div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

    <section class='content-header'><!-- Content Header (Page header) -->
        <h1><spring:message code='default.button.show.label' text='Show'/>&nbsp;<spring:message code='acVoucherMst'
                                                                                                text='Ac Voucher Mst'/></h1>

        <ul class='top-links'>
            <sec:authorize url='/accVoucher/create'>
                <li>
                    <a href='${context}/accVoucher/create' class='btn btn-block btn-primary btn-xs'><i
                            class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label'
                                                                           text='New'/></a>
                </li>
            </sec:authorize>
            <sec:authorize url='/accVoucher/index'>
                <li>
                    <a href='${context}/accVoucher/index' class='btn btn-block btn-info btn-xs'><i
                            class='fa fa-reorder'></i> <spring:message code='default.button.list.label'
                                                                       text='List'/></a>
                </li>
            </sec:authorize>
        </ul>
    </section>
    <!-- /.content-header -->

    <section class='content-messages'>
        <jsp:include page='/views/layouts/_flashMessage.jsp'/>
    </section>
    <!-- /.flesh-message -->

    <section class='content'><!-- Main content -->
        <div class='box box-primary'>
            <div class='box-body'>
                <fieldset class='show-page'>

                    <ol class='property-list'>
                        <form:hidden path='id'/>
                        <c:if test='${acVoucherMst.osLegalEntityEntity!=null}'>
                            <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='osLegalEntityEntity' text='Legal Entity'/>:
                                        </span>
                                        <span class='property-value' aria-labelledby='osLegalEntityEntity'>
                                            <c:out value='${acVoucherMst.osLegalEntityEntity}'/>
                                        </span>
                            </li>
                        </c:if>

                        <c:if test='${acVoucherMst.code!=null && !acVoucherMst.code.isEmpty()}'>
                            <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='code' text='Code'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='code'>
                                            <c:out value='${acVoucherMst.code}'/>
                                        </span>
                            </li>
                        </c:if>

                        <c:if test='${acVoucherMst.voucherDate!=null}'>
                            <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='voucherDate' text='Voucher Date'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='voucherDate'>
                                            <fmt:formatDate value='${acVoucherMst.voucherDate}' type='date'
                                                            pattern='dd/MM/yyyy'/>
                                        </span>
                            </li>
                        </c:if>

                        <c:if test='${acVoucherMst.postingDate!=null}'>
                            <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='postingDate' text='Posting Date'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='postingDate'>
                                            <fmt:formatDate value='${acVoucherMst.postingDate}' type='date'
                                                            pattern='dd/MM/yyyy'/>
                                        </span>
                            </li>
                        </c:if>

                        <c:if test='${acVoucherMst.isDeleted!=null}'>
                            <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='isDeleted' text='Is Deleted'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='isDeleted'>
                                            <c:if test='${acVoucherMst.isDeleted}'><spring:message
                                                    code='default.boolean.true' text='YES'/></c:if><c:if
                                                test='${!acVoucherMst.isDeleted}'><spring:message
                                                code='default.boolean.false' text='NO'/></c:if>
                                            </span>
                            </li>
                        </c:if>


                        <c:if test='${acVoucherMst.narration!=null && !acVoucherMst.narration.isEmpty()}'>
                            <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='narration' text='Narration'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='narration'>
                                            <c:out value='${acVoucherMst.narration}'/>
                                        </span>
                            </li>
                        </c:if>

                    </ol>
                    <div>
                        <spring:message code='acVoucherDtls' text='Ac Voucher Dtls'/>

                        <c:if test='${not empty acVoucherMst.acVoucherDtls}'>
                            <div class='box-body table-responsive no-padding'>
                                <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
                                    <thead>

                                    <th><spring:message code='sln' text='SLN'/></th>
                                    <th><spring:message code='acAccount' text='Account'/></th>
                                    <th><spring:message code='amount' text='Debit Amount'/></th>
                                    <th><spring:message code='amount' text='Credit Amount'/></th>
                                    <th><spring:message code='emCurrency' text='Currency'/></th>
                                    <th><spring:message code='exchangeRate' text='Exchange Rate'/></th>
                                    <th><spring:message code='convertedAmount' text='Debit Amount (Converted)'/></th>
                                    <th><spring:message code='convertedAmount' text='Credit Amount (Converted)'/></th>

                                    <th><spring:message code='osCostCenter' text='Bus Area'/></th>
                                    <th><spring:message code='osCostCenter' text='Cost Center'/></th>
                                    <th><spring:message code='orderNo' text='Order No'/></th>
                                    <th><spring:message code='wbsElement' text='Wbs Element'/></th>
                                        <%--<th><spring:message code='osLegalEntityEntity' text='Os Legal Entity'/></th>--%>
                                    <th><spring:message code='narration' text='Narration'/></th>

                                    </thead>
                                    <tbody>
                                    <spring:message code='default.button.delete.confirm.message'
                                                    text='Are you sure to delete?' var='confirmToDelete'/>
                                    <c:forEach items='${acVoucherMst.acVoucherDtls}' var='acVoucherDtls'
                                               varStatus='loopStatus'>
                                        <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                                            <td class='center'><c:out value='${loopStatus.index}'/></td>
                                            <td><c:out value='${acVoucherDtls.acAccount}'/></td>

                                            <c:if test="${acVoucherDtls.emPosSign=='DR'}">
                                                <td class='right'><c:out value='${acVoucherDtls.enteredAmount}'/></td>
                                                <td></td>
                                            </c:if>
                                            <c:if test="${acVoucherDtls.emPosSign=='CR'}">
                                                <td></td>
                                                <td class='right'><c:out value='${acVoucherDtls.enteredAmount}'/></td>
                                            </c:if>

                                            <td class='center'><c:out value='${acVoucherDtls.emCurrency}'/></td>
                                            <td class='right'><c:out value='${acVoucherDtls.exchangeRate}'/></td>

                                            <c:if test="${acVoucherDtls.emPosSign=='DR'}">
                                                <td class='right'><c:out value='${acVoucherDtls.convertedAmount}'/></td>
                                                <td></td>
                                            </c:if>
                                            <c:if test="${acVoucherDtls.emPosSign=='CR'}">
                                                <td></td>
                                                <td class='right'><c:out value='${acVoucherDtls.convertedAmount}'/></td>
                                            </c:if>


                                            <td><c:out value='${acVoucherDtls.osBusArea}'/></td>
                                            <td><c:out value='${acVoucherDtls.osCostCenter}'/></td>
                                            <td><c:out value='${acVoucherDtls.orderNo}'/></td>
                                            <td><c:out value='${acVoucherDtls.wbsElement}'/></td>
                                                <%--<td><c:out value='${acVoucherDtls.osLegalEntityEntity}'/></td>--%>
                                            <td><c:out value='${acVoucherDtls.narration}'/></td>

                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>
                    </div>

                </fieldset>
                <!--.show-page-->
            </div>
            <!--.box-body-->

            <div class='box-footer'>
                <sec:authorize url='/accVoucher/edit'>
                    <a href='${context}/accVoucher/edit/${acVoucherMst.id}' class='btn btn-primary'><i
                            class='fa fa-edit'></i>&nbsp;<spring:message code='default.button.edit.label'/></a>
                </sec:authorize>
                <%--<sec:authorize url='/accVoucher/copy'>
                    <a href='${context}/accVoucher/copy/${acVoucherMst.id}' class='btn btn-warning'><i class='fa fa-clone'></i>&nbsp;<spring:message code='default.button.copy.label'/></a>
                </sec:authorize>--%>
                <sec:authorize url='/accVoucher/delete'>
                    <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?'
                                    var='confirmToDelete'/>
                    <a href='${context}/accVoucher/delete/${acVoucherMst.id}' class='btn btn-danger'
                       onclick="return confirm('${confirmToDelete}');"><i class='fa fa-remove'></i>&nbsp;<spring:message
                            code='default.button.delete.label'/></a>
                </sec:authorize>
            </div>
            <!--.box-footer-->
        </div>
        <!--.box .box-primary-->
    </section>
    <!--.content-->
</div>
<!-- /.content-wrapper -->
</body>
</html>