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
        <spring:message code="admReport.label" text='Adm Report' var="entityName"/>
        <title><spring:message code="default.show.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.show.label" arguments="${entityName}"/></h1>

                <ul class='top-links'>
                    <sec:authorize url='/admReport/create'>
                        <li>
                            <a href='${context}/admReport/create' class='btn btn-block btn-primary btn-xs'>
                                <i class='fa fa-plus-circle'></i>
                                <spring:message code='default.new.label' arguments="${entityName}"/>
                            </a>
                        </li>
                    </sec:authorize>
                    <sec:authorize url='/admReport/index'>
                        <li>
                            <a href='${context}/admReport/index' class='btn btn-block btn-info btn-xs'>
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

                                <c:if test='${admReport.code!=null && !admReport.code.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='code' text='Code'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='code'>
                                            <c:out value='${admReport.code}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admReport.fullName!=null && !admReport.fullName.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='fullName' text='Full Name'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='fullName'>
                                            <c:out value='${admReport.fullName}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admReport.fullNameNative!=null && !admReport.fullNameNative.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='fullNameNative' text='Full Name Native'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='fullNameNative'>
                                            <c:out value='${admReport.fullNameNative}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admReport.isActive!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='isActive' text='Is Active'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='isActive'>
                                            <c:if test='${admReport.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admReport.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admReport.slNo!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='slNo' text='Sl No'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='slNo'>
                                            <c:out value='${admReport.slNo}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admReport.description!=null && !admReport.description.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='description' text='Description'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='description'>
                                            <c:out value='${admReport.description}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admReport.admModule!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='admModule' text='Adm Sub Module'/>:
                                        </span>
                                        <span class='property-value' aria-labelledby='admModule'>
                                            <c:out value='${admReport.admModule}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admReport.fileName!=null && !admReport.fileName.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='fileName' text='File Name'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='fileName'>
                                            <c:out value='${admReport.fileName}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admReport.supportFormats!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='supportFormats' text='Support Formats'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='supportFormats'>
                                            <c:out value='${admReport.supportFormats}'/>
                                        </span>
                                    </li>
                                </c:if>

                            </ol>
                            <div><jsp:include page='showAdmReportDetails.jsp' /></div>

                        </fieldset><!--.show-page-->
                    </div><!--.box-body-->

                    <div class='box-footer'>
                        <sec:authorize url='/admReport/edit'>
                            <a href='${context}/admReport/edit/${admReport.id}' class='btn btn-primary'>
                                <i class='fa fa-edit'></i>
                                <spring:message code='default.button.edit.label'/>
                            </a> 
                        </sec:authorize>
                        <sec:authorize url='/admReport/copy'>
                            <a href='${context}/admReport/copy/${admReport.id}' class='btn btn-warning'>
                                <i class='fa fa-clone'></i>
                                <spring:message code='default.button.copy.label'/>
                            </a>             
                        </sec:authorize>
                        <sec:authorize url='/admReport/delete'>
                            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>                     
                            <a href='${context}/admReport/delete/${admReport.id}' class='btn btn-danger' onclick="return confirm('${confirmToDelete}');">
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