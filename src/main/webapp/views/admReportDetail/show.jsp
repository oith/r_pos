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
        <spring:message code="admReportDetail.label" text='Adm Report Detail' var="entityName"/>
        <title><spring:message code="default.show.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.show.label" arguments="${entityName}"/></h1>

                <ul class='top-links'>
                    <sec:authorize url='/admReportDetail/create'>
                        <li>
                            <a href='${context}/admReportDetail/create' class='btn btn-block btn-primary btn-xs'>
                                <i class='fa fa-plus-circle'></i>
                                <spring:message code='default.new.label' arguments="${entityName}"/>
                            </a>
                        </li>
                    </sec:authorize>
                    <sec:authorize url='/admReportDetail/index'>
                        <li>
                            <a href='${context}/admReportDetail/index' class='btn btn-block btn-info btn-xs'>
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

                                <c:if test='${admReportDetail.slNo!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='slNo' text='Sl No'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='slNo'>
                                            <c:out value='${admReportDetail.slNo}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admReportDetail.admReport!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='admReport' text='Adm Report'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='admReport'>
                                            <c:out value='${admReportDetail.admReport!=null ? admReportDetail.admReport :"N/A"}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admReportDetail.admParam!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='admParam' text='Adm Param'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='admParam'>
                                            <c:out value='${admReportDetail.admParam}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admReportDetail.isActive!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='isActive' text='Is Active'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='isActive'>
                                            <c:if test='${admReportDetail.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admReportDetail.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admReportDetail.isMandatory!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='isMandatory' text='Is Mandatory'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='isMandatory'>
                                            <c:if test='${admReportDetail.isMandatory}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admReportDetail.isMandatory}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admReportDetail.defaultVal!=null && !admReportDetail.defaultVal.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='defaultVal' text='Default Val'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='defaultVal'>
                                            <c:out value='${admReportDetail.defaultVal}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admReportDetail.helpText!=null && !admReportDetail.helpText.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='helpText' text='Help Text'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='helpText'>
                                            <c:out value='${admReportDetail.helpText}'/>
                                        </span>
                                    </li>
                                </c:if>

                            </ol>

                        </fieldset><!--.show-page-->
                    </div><!--.box-body-->

                    <div class='box-footer'>
                        <sec:authorize url='/admReportDetail/edit'>
                            <a href='${context}/admReportDetail/edit/${admReportDetail.id}' class='btn btn-primary'>
                                <i class='fa fa-edit'></i>
                                <spring:message code='default.button.edit.label'/>
                            </a> 
                        </sec:authorize>
                        <sec:authorize url='/admReportDetail/copy'>
                            <a href='${context}/admReportDetail/copy/${admReportDetail.id}' class='btn btn-warning'>
                                <i class='fa fa-clone'></i>
                                <spring:message code='default.button.copy.label'/>
                            </a>             
                        </sec:authorize>
                        <sec:authorize url='/admReportDetail/delete'>
                            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>                     
                            <a href='${context}/admReportDetail/delete/${admReportDetail.id}' class='btn btn-danger' onclick="return confirm('${confirmToDelete}');">
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