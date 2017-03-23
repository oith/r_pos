<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8'%>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

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
        <title><spring:message code="default.list.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.list.label" arguments="${entityName}"/></h1>
                
                <ul class='top-links'>
                    <sec:authorize url='/posPurchaseDetail/create'>
                        <li>
                            <a href='${context}/posPurchaseDetail/create' class='btn btn-block btn-primary btn-xs'>
                    <i class='fa fa-plus-circle'></i>
                    <spring:message code='default.new.label' arguments="${entityName}"/>
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
                    <!--<div class='box-body' style='overflow-x: auto'>-->
                    <div class='box-body'>
                        <table class='table dt-default table-bordered table-striped table-hover table-condensed display nowrap th-center'>

                            <!--<table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>-->
                            <thead>
                                <th><spring:message code='posPurchaseMaster' text='Pos Purchase Master'/></th>
                                <th><spring:message code='posProduct' text='Pos Product'/></th>
                                <th><spring:message code='quantity' text='Quantity'/></th>
                                <th><spring:message code='unitPrice' text='Unit Price'/></th>
                                <th><spring:message code='lineTotal' text='Line Total'/></th>

                                <th><spring:message code='default.button.action.label' text='Action'/></th> 
                            </thead>
                            <tbody>
                                <c:if test='${not empty posPurchaseDetails}'>
                                    <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>
                                    <c:forEach items='${posPurchaseDetails}' var='posPurchaseDetail'  varStatus='loopStatus'>
                                        <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                                            <td><c:out value='${posPurchaseDetail.posPurchaseMaster!=null ? posPurchaseDetail.posPurchaseMaster :"N/A"}'/></td>
                                            <td><c:out value='${posPurchaseDetail.posProduct!=null ? posPurchaseDetail.posProduct :"N/A"}'/></td>
                                            <td class='right'><c:out value='${posPurchaseDetail.quantity}'/></td>
                                            <td class='right'><c:out value='${posPurchaseDetail.unitPrice}'/></td>
                                            <td class='right'><c:out value='${posPurchaseDetail.lineTotal}'/></td>

                                            <td class='center action'>
                                                <sec:authorize url='/posPurchaseDetail/show'>
                                                    <a class='btn btn-info btn-xs' href='${context}/posPurchaseDetail/show/${posPurchaseDetail.id}'>
                                                        <i class='fa fa-info-circle'></i>
                                                        <spring:message code='default.button.show.label'/>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize url='/posPurchaseDetail/edit'>
                                                    <a class='btn btn-primary btn-xs' href='${context}/posPurchaseDetail/edit/${posPurchaseDetail.id}'>
                                                        <i class='fa fa-edit'></i>
                                                        <spring:message code='default.button.edit.label'/>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize url='/posPurchaseDetail/copy'>
                                                    <a class='btn btn-warning btn-xs' href='${context}/posPurchaseDetail/copy/${posPurchaseDetail.id}'>
                                                        <i class='fa fa-clone' aria-hidden='true'></i>
                                                        <spring:message code='default.button.copy.label'/>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize url='/posPurchaseDetail/delete'>
                                                    <a class='btn btn-danger btn-xs' href='${context}/posPurchaseDetail/delete/${posPurchaseDetail.id}' onclick="return confirm('${confirmToDelete}');">
                                                       <i class='fa fa-remove'></i>
                                                       <spring:message code='default.button.delete.label'/>
                                                    </a>
                                                </sec:authorize>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if> 
                            </tbody>
                        </table>
                        <!--<div class='pagination'>-->
                            <!--<g:paginate total='{testInstanceCount ?: 0}'/>-->
                        <!--</div>-->
                    </div><!-- /.box-body table-responsive no-padding -->
                </div><!-- /.box box-primary -->
            </section><!-- /.content -->
        </div><!-- /.content-wrapper -->
    </body>
</html>