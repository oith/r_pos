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
        <spring:message code="admCodeDef.label" text='Adm Code Def' var="entityName"/>
        <title><spring:message code="default.show.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.show.label" arguments="${entityName}"/></h1>

                <ul class='top-links'>
                    <sec:authorize url='/admCodeDef/create'>
                        <li>
                            <a href='${context}/admCodeDef/create' class='btn btn-block btn-primary btn-xs'>
                                <i class='fa fa-plus-circle'></i>
                                <spring:message code='default.new.label' arguments="${entityName}"/>
                            </a>
                        </li>
                    </sec:authorize>
                    <sec:authorize url='/admCodeDef/index'>
                        <li>
                            <a href='${context}/admCodeDef/index' class='btn btn-block btn-info btn-xs'>
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

                                <c:if test='${admCodeDef.fullName!=null && !admCodeDef.fullName.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='fullName' text='Full Name'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='fullName'>
                                            <c:out value='${admCodeDef.fullName}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admCodeDef.pojoClass!=null && !admCodeDef.pojoClass.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='pojoClass' text='Pojo Class'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='pojoClass'>
                                            <c:out value='${admCodeDef.pojoClass}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admCodeDef.startWith!=null && !admCodeDef.startWith.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='startWith' text='Start With'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='startWith'>
                                            <c:out value='${admCodeDef.startWith}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admCodeDef.endWith!=null && !admCodeDef.endWith.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='endWith' text='End With'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='endWith'>
                                            <c:out value='${admCodeDef.endWith}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admCodeDef.codeLength!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='codeLength' text='Code Length'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='codeLength'>
                                            <c:out value='${admCodeDef.codeLength}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admCodeDef.nextValue!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='nextValue' text='Next Value'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='nextValue'>
                                            <c:out value='${admCodeDef.nextValue}'/>
                                        </span>
                                    </li>
                                </c:if>

                            </ol>

                        </fieldset><!--.show-page-->
                    </div><!--.box-body-->

                    <div class='box-footer'>
                        <sec:authorize url='/admCodeDef/edit'>
                            <a href='${context}/admCodeDef/edit/${admCodeDef.id}' class='btn btn-primary'>
                                <i class='fa fa-edit'></i>
                                <spring:message code='default.button.edit.label'/>
                            </a> 
                        </sec:authorize>
                        <sec:authorize url='/admCodeDef/copy'>
                            <a href='${context}/admCodeDef/copy/${admCodeDef.id}' class='btn btn-warning'>
                                <i class='fa fa-clone'></i>
                                <spring:message code='default.button.copy.label'/>
                            </a>             
                        </sec:authorize>
                        <sec:authorize url='/admCodeDef/delete'>
                            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>                     
                            <a href='${context}/admCodeDef/delete/${admCodeDef.id}' class='btn btn-danger' onclick="return confirm('${confirmToDelete}');">
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