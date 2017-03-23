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
        <spring:message code="posPurchaseDetail.label" text='Pos Purchase Detail' var="entityName"/>
        <title><spring:message code="default.show.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.show.label" arguments="${entityName}"/></h1>

                <ul class='top-links'>
                    <sec:authorize url='/posPurchaseDetail/create'>
                        <li>
                            <a href='${context}/posPurchaseDetail/create' class='btn btn-block btn-primary btn-xs'>
                                <i class='fa fa-plus-circle'></i>
                                <spring:message code='default.new.label' arguments="${entityName}"/>
                            </a>
                        </li>
                    </sec:authorize>
                    <sec:authorize url='/posPurchaseDetail/index'>
                        <li>
                            <a href='${context}/posPurchaseDetail/index' class='btn btn-block btn-info btn-xs'>
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

                                <c:if test='${posPurchaseDetail.posPurchaseMaster!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='posPurchaseMaster' text='Pos Purchase Master'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='posPurchaseMaster'>
                                            <c:out value='${posPurchaseDetail.posPurchaseMaster!=null ? posPurchaseDetail.posPurchaseMaster :"N/A"}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPurchaseDetail.posProduct!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='posProduct' text='Pos Product'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='posProduct'>
                                            <c:out value='${posPurchaseDetail.posProduct!=null ? posPurchaseDetail.posProduct :"N/A"}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPurchaseDetail.quantity!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='quantity' text='Quantity'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='quantity'>
                                            <c:out value='${posPurchaseDetail.quantity}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPurchaseDetail.unitPrice!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='unitPrice' text='Unit Price'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='unitPrice'>
                                            <c:out value='${posPurchaseDetail.unitPrice}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${posPurchaseDetail.lineTotal!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='lineTotal' text='Line Total'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='lineTotal'>
                                            <c:out value='${posPurchaseDetail.lineTotal}'/>
                                        </span>
                                    </li>
                                </c:if>

                            </ol>

                        </fieldset><!--.show-page-->
                    </div><!--.box-body-->

                    <div class='box-footer'>
                        <sec:authorize url='/posPurchaseDetail/edit'>
                            <a href='${context}/posPurchaseDetail/edit/${posPurchaseDetail.id}' class='btn btn-primary'>
                                <i class='fa fa-edit'></i>
                                <spring:message code='default.button.edit.label'/>
                            </a> 
                        </sec:authorize>
                        <sec:authorize url='/posPurchaseDetail/copy'>
                            <a href='${context}/posPurchaseDetail/copy/${posPurchaseDetail.id}' class='btn btn-warning'>
                                <i class='fa fa-clone'></i>
                                <spring:message code='default.button.copy.label'/>
                            </a>             
                        </sec:authorize>
                        <sec:authorize url='/posPurchaseDetail/delete'>
                            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>                     
                            <a href='${context}/posPurchaseDetail/delete/${posPurchaseDetail.id}' class='btn btn-danger' onclick="return confirm('${confirmToDelete}');">
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