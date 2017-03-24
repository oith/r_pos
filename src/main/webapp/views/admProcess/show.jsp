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
        <spring:message code="admProcess.label" text='Adm Process' var="entityName"/>
        <title><spring:message code="default.show.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.show.label" arguments="${entityName}"/></h1>

                <ul class='top-links'>
                    <sec:authorize url='/admProcess/create'>
                        <li>
                            <a href='${context}/admProcess/create' class='btn btn-block btn-primary btn-xs'>
                                <i class='fa fa-plus-circle'></i>
                                <spring:message code='default.new.label' arguments="${entityName}"/>
                            </a>
                        </li>
                    </sec:authorize>
                    <sec:authorize url='/admProcess/index'>
                        <li>
                            <a href='${context}/admProcess/index' class='btn btn-block btn-info btn-xs'>
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

                                <c:if test='${admProcess.code!=null && !admProcess.code.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='code' text='Code'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='code'>
                                            <c:out value='${admProcess.code}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admProcess.fullName!=null && !admProcess.fullName.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='fullName' text='Full Name'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='fullName'>
                                            <c:out value='${admProcess.fullName}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admProcess.fullNameNative!=null && !admProcess.fullNameNative.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='fullNameNative' text='Full Name Native'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='fullNameNative'>
                                            <c:out value='${admProcess.fullNameNative}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admProcess.isActive!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='isActive' text='Is Active'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='isActive'>
                                            <c:if test='${admProcess.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admProcess.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admProcess.slNo!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='slNo' text='Sl No'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='slNo'>
                                            <c:out value='${admProcess.slNo}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admProcess.description!=null && !admProcess.description.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='description' text='Description'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='description'>
                                            <c:out value='${admProcess.description}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admProcess.admModule!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='admModule' text='Adm Sub Module'/>:
                                        </span>
                                        <span class='property-value' aria-labelledby='admModule'>
                                            <c:out value='${admProcess.admModule}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admProcess.cmd!=null && !admProcess.cmd.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='cmd' text='Cmd'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='cmd'>
                                            <c:out value='${admProcess.cmd}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admProcess.query!=null && !admProcess.query.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='query' text='Query'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='query'>
                                            <c:out value='${admProcess.query}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admProcess.queryAlias!=null && !admProcess.queryAlias.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='queryAlias' text='Query Alias'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='queryAlias'>
                                            <c:out value='${admProcess.queryAlias}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admProcess.processBtns!=null && !admProcess.processBtns.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='processBtns' text='Process Btns'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='processBtns'>
                                            <c:out value='${admProcess.processBtns}'/>
                                        </span>
                                    </li>
                                </c:if>

                            </ol>
                            <div><jsp:include page='showAdmProcessDetails.jsp' /></div>

                        </fieldset><!--.show-page-->
                    </div><!--.box-body-->

                    <div class='box-footer'>
                        <sec:authorize url='/admProcess/edit'>
                            <a href='${context}/admProcess/edit/${admProcess.id}' class='btn btn-primary'>
                                <i class='fa fa-edit'></i>
                                <spring:message code='default.button.edit.label'/>
                            </a> 
                        </sec:authorize>
                        <sec:authorize url='/admProcess/copy'>
                            <a href='${context}/admProcess/copy/${admProcess.id}' class='btn btn-warning'>
                                <i class='fa fa-clone'></i>
                                <spring:message code='default.button.copy.label'/>
                            </a>             
                        </sec:authorize>
                        <sec:authorize url='/admProcess/delete'>
                            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>                     
                            <a href='${context}/admProcess/delete/${admProcess.id}' class='btn btn-danger' onclick="return confirm('${confirmToDelete}');">
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