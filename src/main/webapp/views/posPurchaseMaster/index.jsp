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
        <spring:message code="posPurchaseMaster.label" text='Pos Purchase Master' var="entityName"/>
        <title><spring:message code="default.list.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.list.label" arguments="${entityName}"/></h1>
                
                <ul class='top-links'>
                    <sec:authorize url='/posPurchaseMaster/create'>
                        <li>
                            <a href='${context}/posPurchaseMaster/create' class='btn btn-block btn-primary btn-xs'>
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
                                <th><spring:message code='embdAuditable.entryBy' text='Entry By'/></th>
                                <th><spring:message code='embdAuditable.entryDate' text='Entry Date'/></th>
                                <th><spring:message code='embdAuditable.editBy' text='Edit By'/></th>
                                <th><spring:message code='embdAuditable.editDate' text='Edit Date'/></th>
                                <th><spring:message code='embdAuditable.copyBy' text='Copy By'/></th>
                                <th><spring:message code='embdAuditable.copyDate' text='Copy Date'/></th>
                                <th><spring:message code='code' text='Code'/></th>
                                <th><spring:message code='transDate' text='Trans Date'/></th>
                                <th><spring:message code='originDate' text='Origin Date'/></th>
                                <th><spring:message code='amount' text='Amount'/></th>
                                <th><spring:message code='remarks' text='Remarks'/></th>
                                <th><spring:message code='authUserTransBy' text='Auth User Trans By'/></th>
                                <th><spring:message code='posSupplier' text='Pos Supplier'/></th>
                                <th><spring:message code='paidAmount' text='Paid Amount'/></th>

                                <th><spring:message code='default.button.action.label' text='Action'/></th> 
                            </thead>
                            <tbody>
                                <c:if test='${not empty posPurchaseMasters}'>
                                    <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>
                                    <c:forEach items='${posPurchaseMasters}' var='posPurchaseMaster'  varStatus='loopStatus'>
                                        <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                                            <td><c:out value='${posPurchaseMaster.embdAuditable.entryBy!=null ? posPurchaseMaster.embdAuditable.entryBy :"N/A"}'/></td>
                                            <td class='center'><fmt:formatDate value='${posPurchaseMaster.embdAuditable.entryDate}' type='date' pattern='dd/MM/yyyy'/></td>
                                            <td><c:out value='${posPurchaseMaster.embdAuditable.editBy!=null ? posPurchaseMaster.embdAuditable.editBy :"N/A"}'/></td>
                                            <td class='center'><fmt:formatDate value='${posPurchaseMaster.embdAuditable.editDate}' type='date' pattern='dd/MM/yyyy'/></td>
                                            <td><c:out value='${posPurchaseMaster.embdAuditable.copyBy!=null ? posPurchaseMaster.embdAuditable.copyBy :"N/A"}'/></td>
                                            <td class='center'><fmt:formatDate value='${posPurchaseMaster.embdAuditable.copyDate}' type='date' pattern='dd/MM/yyyy'/></td>
                                            <td class='center'><c:out value='${posPurchaseMaster.code}'/></td>
                                            <td class='center'><fmt:formatDate value='${posPurchaseMaster.transDate}' type='date' pattern='dd/MM/yyyy'/></td>
                                            <td class='center'><fmt:formatDate value='${posPurchaseMaster.originDate}' type='date' pattern='dd/MM/yyyy'/></td>
                                            <td class='right'><c:out value='${posPurchaseMaster.amount}'/></td>
                                            <td><c:out value='${posPurchaseMaster.remarks}'/></td>
                                            <td><c:out value='${posPurchaseMaster.authUserTransBy!=null ? posPurchaseMaster.authUserTransBy :"N/A"}'/></td>
                                            <td><c:out value='${posPurchaseMaster.posSupplier!=null ? posPurchaseMaster.posSupplier :"N/A"}'/></td>
                                            <td class='right'><c:out value='${posPurchaseMaster.paidAmount}'/></td>

                                            <td class='center action'>
                                                <sec:authorize url='/posPurchaseMaster/show'>
                                                    <a class='btn btn-info btn-xs' href='${context}/posPurchaseMaster/show/${posPurchaseMaster.id}'>
                                                        <i class='fa fa-info-circle'></i>
                                                        <spring:message code='default.button.show.label'/>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize url='/posPurchaseMaster/edit'>
                                                    <a class='btn btn-primary btn-xs' href='${context}/posPurchaseMaster/edit/${posPurchaseMaster.id}'>
                                                        <i class='fa fa-edit'></i>
                                                        <spring:message code='default.button.edit.label'/>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize url='/posPurchaseMaster/copy'>
                                                    <a class='btn btn-warning btn-xs' href='${context}/posPurchaseMaster/copy/${posPurchaseMaster.id}'>
                                                        <i class='fa fa-clone' aria-hidden='true'></i>
                                                        <spring:message code='default.button.copy.label'/>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize url='/posPurchaseMaster/delete'>
                                                    <a class='btn btn-danger btn-xs' href='${context}/posPurchaseMaster/delete/${posPurchaseMaster.id}' onclick="return confirm('${confirmToDelete}');">
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