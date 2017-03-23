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
        <spring:message code="admMenuItem.label" text='Adm Menu Item' var="entityName"/>
        <title><spring:message code="default.show.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.show.label" arguments="${entityName}"/></h1>

                <ul class='top-links'>
                    <sec:authorize url='/admMenuItem/create'>
                        <li>
                            <a href='${context}/admMenuItem/create' class='btn btn-block btn-primary btn-xs'>
                                <i class='fa fa-plus-circle'></i>
                                <spring:message code='default.new.label' arguments="${entityName}"/>
                            </a>
                        </li>
                    </sec:authorize>
                    <sec:authorize url='/admMenuItem/index'>
                        <li>
                            <a href='${context}/admMenuItem/index' class='btn btn-block btn-info btn-xs'>
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

                                <c:if test='${admMenuItem.code!=null && !admMenuItem.code.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='code' text='Code'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='code'>
                                            <c:out value='${admMenuItem.code}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenuItem.fullName!=null && !admMenuItem.fullName.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='fullName' text='Full Name'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='fullName'>
                                            <c:out value='${admMenuItem.fullName}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenuItem.fullNameNative!=null && !admMenuItem.fullNameNative.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='fullNameNative' text='Full Name Native'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='fullNameNative'>
                                            <c:out value='${admMenuItem.fullNameNative}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenuItem.isActive!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='isActive' text='Is Active'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='isActive'>
                                            <c:if test='${admMenuItem.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admMenuItem.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenuItem.slNo!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='slNo' text='Sl No'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='slNo'>
                                            <c:out value='${admMenuItem.slNo}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenuItem.description!=null && !admMenuItem.description.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='description' text='Description'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='description'>
                                            <c:out value='${admMenuItem.description}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenuItem.tooltip!=null && !admMenuItem.tooltip.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='tooltip' text='Tooltip'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='tooltip'>
                                            <c:out value='${admMenuItem.tooltip}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenuItem.displayIconClass!=null && !admMenuItem.displayIconClass.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='displayIconClass' text='Display Icon Class'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='displayIconClass'>
                                            <c:out value='${admMenuItem.displayIconClass}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenuItem.isExternal!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='isExternal' text='Is External'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='isExternal'>
                                            <c:if test='${admMenuItem.isExternal}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admMenuItem.isExternal}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenuItem.isOpenInNewTab!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='isOpenInNewTab' text='Is Open In New Tab'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='isOpenInNewTab'>
                                            <c:if test='${admMenuItem.isOpenInNewTab}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admMenuItem.isOpenInNewTab}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenuItem.admSubModule!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='admSubModule' text='Adm Sub Module'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='admSubModule'>
                                            <c:out value='${admMenuItem.admSubModule}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenuItem.admMenu!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='admMenu' text='Adm Menu'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='admMenu'>
                                            <c:out value='${admMenuItem.admMenu!=null ? admMenuItem.admMenu :"N/A"}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenuItem.urlPath!=null && !admMenuItem.urlPath.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='urlPath' text='Url Path'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='urlPath'>
                                            <c:out value='${admMenuItem.urlPath}'/>
                                        </span>
                                    </li>
                                </c:if>

                            </ol>

                        </fieldset><!--.show-page-->
                    </div><!--.box-body-->

                    <div class='box-footer'>
                        <sec:authorize url='/admMenuItem/edit'>
                            <a href='${context}/admMenuItem/edit/${admMenuItem.id}' class='btn btn-primary'>
                                <i class='fa fa-edit'></i>
                                <spring:message code='default.button.edit.label'/>
                            </a> 
                        </sec:authorize>
                        <sec:authorize url='/admMenuItem/copy'>
                            <a href='${context}/admMenuItem/copy/${admMenuItem.id}' class='btn btn-warning'>
                                <i class='fa fa-clone'></i>
                                <spring:message code='default.button.copy.label'/>
                            </a>             
                        </sec:authorize>
                        <sec:authorize url='/admMenuItem/delete'>
                            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>                     
                            <a href='${context}/admMenuItem/delete/${admMenuItem.id}' class='btn btn-danger' onclick="return confirm('${confirmToDelete}');">
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