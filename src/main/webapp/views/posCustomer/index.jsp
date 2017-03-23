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
        <spring:message code="posCustomer.label" text='Pos Customer' var="entityName"/>
        <title><spring:message code="default.list.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.list.label" arguments="${entityName}"/></h1>
                
                <ul class='top-links'>
                    <sec:authorize url='/posCustomer/create'>
                        <li>
                            <a href='${context}/posCustomer/create' class='btn btn-block btn-primary btn-xs'>
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
                                <th><spring:message code='code' text='Code'/></th>
                                <th><spring:message code='fullName' text='Full Name'/></th>
                                <th><spring:message code='fullNameNative' text='Full Name Native'/></th>
                                <th><spring:message code='emPosAnalysisCode' text='Em Pos Analysis Code'/></th>
                                <th><spring:message code='mobile' text='Mobile'/></th>
                                <th><spring:message code='pic' text='Pic'/></th>
                                <th><spring:message code='address' text='Address'/></th>
                                <th><spring:message code='emPosCustomerGroup' text='Em Pos Customer Group'/></th>

                                <th><spring:message code='default.button.action.label' text='Action'/></th> 
                            </thead>
                            <tbody>
                                <c:if test='${not empty posCustomers}'>
                                    <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>
                                    <c:forEach items='${posCustomers}' var='posCustomer'  varStatus='loopStatus'>
                                        <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                                            <td class='center'><c:out value='${posCustomer.code}'/></td>
                                            <td><c:out value='${posCustomer.fullName}'/></td>
                                            <td><c:out value='${posCustomer.fullNameNative}'/></td>
                                            <td class='center'><c:out value='${posCustomer.emPosAnalysisCode}'/></td>
                                            <td><c:out value='${posCustomer.mobile}'/></td>
                                            <td><c:url var='pic' value='/posCustomer/getPhotoSmall/${posCustomer.pic}'/>
<img alt='${posCustomer.pic}' src='${pic}'/></td>
                                            <td><c:out value='${posCustomer.address}'/></td>
                                            <td class='center'><c:out value='${posCustomer.emPosCustomerGroup}'/></td>

                                            <td class='center action'>
                                                <sec:authorize url='/posCustomer/show'>
                                                    <a class='btn btn-info btn-xs' href='${context}/posCustomer/show/${posCustomer.id}'>
                                                        <i class='fa fa-info-circle'></i>
                                                        <spring:message code='default.button.show.label'/>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize url='/posCustomer/edit'>
                                                    <a class='btn btn-primary btn-xs' href='${context}/posCustomer/edit/${posCustomer.id}'>
                                                        <i class='fa fa-edit'></i>
                                                        <spring:message code='default.button.edit.label'/>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize url='/posCustomer/copy'>
                                                    <a class='btn btn-warning btn-xs' href='${context}/posCustomer/copy/${posCustomer.id}'>
                                                        <i class='fa fa-clone' aria-hidden='true'></i>
                                                        <spring:message code='default.button.copy.label'/>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize url='/posCustomer/delete'>
                                                    <a class='btn btn-danger btn-xs' href='${context}/posCustomer/delete/${posCustomer.id}' onclick="return confirm('${confirmToDelete}');">
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