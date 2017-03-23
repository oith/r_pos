<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8'%>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
        <meta name="description" content="Code Building">
        <meta name="keywords" content="oith,reflection,spring,java">
        <meta name="author" content="oith">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <c:set var='context' value='${pageContext.request.contextPath}'/>
        <spring:message code="posPayment.label" text='Pos Payment' var="entityName"/>
        <title><spring:message code="default.show.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.show.label" arguments="${entityName}"/></h1>

                <ul class='top-links'>
                    <sec:authorize url='/posPayment/create'>
                        <li>
                            <a href='${context}/posPayment/create' class='btn btn-block btn-primary btn-xs'>
                                <i class='fa fa-plus-circle'></i>
                                <spring:message code='default.new.label' arguments="${entityName}"/>
                            </a>
                        </li>
                    </sec:authorize>
                    <sec:authorize url='/posPayment/index'>
                        <li>
                            <a href='${context}/posPayment/index' class='btn btn-block btn-info btn-xs'>
                                <i class='fa fa-reorder'></i>
                                <spring:message code='default.list.label' arguments="${entityName}"/>
                            </a>
                        </li>
                    </sec:authorize>
                </ul>
            </section><!-- /.content-header -->

            <section class='content-messages'>
                <jsp:include page='/views/layouts/_flashMessage.jsp'/>
            </section><!-- /.flesh-message -->

            <section class='content'><!-- Main content -->
                <div class='box box-primary'>
                    <div class='box-body'>
                        <fieldset class='show-page'>
                            <form:hidden path='id'/>
                            <ol class='property-list'>

                                <c:if test='${posPayment.embdAuditable.entryBy!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='embdAuditable.entryBy' text='Entry By'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='embdAuditable.entryBy'>
                                            <c:out value='${posPayment.embdAuditable.entryBy!=null ? posPayment.embdAuditable.entryBy :"N/A"}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPayment.embdAuditable.entryDate!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='embdAuditable.entryDate' text='Entry Date'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='embdAuditable.entryDate'>
                                            <fmt:formatDate value='${posPayment.embdAuditable.entryDate}' type='date' pattern='dd/MM/yyyy'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPayment.embdAuditable.editBy!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='embdAuditable.editBy' text='Edit By'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='embdAuditable.editBy'>
                                            <c:out value='${posPayment.embdAuditable.editBy!=null ? posPayment.embdAuditable.editBy :"N/A"}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPayment.embdAuditable.editDate!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='embdAuditable.editDate' text='Edit Date'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='embdAuditable.editDate'>
                                            <fmt:formatDate value='${posPayment.embdAuditable.editDate}' type='date' pattern='dd/MM/yyyy'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPayment.embdAuditable.copyBy!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='embdAuditable.copyBy' text='Copy By'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='embdAuditable.copyBy'>
                                            <c:out value='${posPayment.embdAuditable.copyBy!=null ? posPayment.embdAuditable.copyBy :"N/A"}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPayment.embdAuditable.copyDate!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='embdAuditable.copyDate' text='Copy Date'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='embdAuditable.copyDate'>
                                            <fmt:formatDate value='${posPayment.embdAuditable.copyDate}' type='date' pattern='dd/MM/yyyy'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPayment.code!=null && !posPayment.code.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='code' text='Code'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='code'>
                                            <c:out value='${posPayment.code}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPayment.transDate!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='transDate' text='Trans Date'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='transDate'>
                                            <fmt:formatDate value='${posPayment.transDate}' type='date' pattern='dd/MM/yyyy'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPayment.originDate!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='originDate' text='Origin Date'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='originDate'>
                                            <fmt:formatDate value='${posPayment.originDate}' type='date' pattern='dd/MM/yyyy'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPayment.amount!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='amount' text='Amount'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='amount'>
                                            <c:out value='${posPayment.amount}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPayment.remarks!=null && !posPayment.remarks.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='remarks' text='Remarks'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='remarks'>
                                            <c:out value='${posPayment.remarks}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPayment.authUserTransBy!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='authUserTransBy' text='Auth User Trans By'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='authUserTransBy'>
                                            <c:out value='${posPayment.authUserTransBy!=null ? posPayment.authUserTransBy :"N/A"}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPayment.posPurchaseMaster!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='posPurchaseMaster' text='Pos Purchase Master'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='posPurchaseMaster'>
                                            <c:out value='${posPayment.posPurchaseMaster!=null ? posPayment.posPurchaseMaster :"N/A"}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPayment.posSupplier!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='posSupplier' text='Pos Supplier'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='posSupplier'>
                                            <c:out value='${posPayment.posSupplier!=null ? posPayment.posSupplier :"N/A"}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPayment.emPosPaymentType!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='emPosPaymentType' text='Em Pos Payment Type'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='emPosPaymentType'>
                                            <c:out value='${posPayment.emPosPaymentType}'/>
                                        </span>
                                    </li>
                                </c:if>

                            </ol>

                        </fieldset><!--.show-page-->
                    </div><!--.box-body-->

                    <div class='box-footer'>
                        <sec:authorize url='/posPayment/edit'>
                            <a href='${context}/posPayment/edit/${posPayment.id}' class='btn btn-primary'>
                                <i class='fa fa-edit'></i>
                                <spring:message code='default.button.edit.label'/>
                            </a> 
                        </sec:authorize>
                        <sec:authorize url='/posPayment/copy'>
                            <a href='${context}/posPayment/copy/${posPayment.id}' class='btn btn-warning'>
                                <i class='fa fa-clone'></i>
                                <spring:message code='default.button.copy.label'/>
                            </a>             
                        </sec:authorize>
                        <sec:authorize url='/posPayment/delete'>
                            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>                     
                            <a href='${context}/posPayment/delete/${posPayment.id}' class='btn btn-danger' onclick="return confirm('${confirmToDelete}');">
                                <i class='fa fa-remove'></i>
                                <spring:message code='default.button.delete.label'/>
                            </a>
                        </sec:authorize>
                    </div><!--.box-footer-->
                </div><!--.box .box-primary-->
            </section><!--.content-->
        </div><!-- /.content-wrapper -->
    </body>
</html>