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
        <spring:message code="admMenu.label" text='Adm Menu' var="entityName"/>
        <title><spring:message code="default.show.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.show.label" arguments="${entityName}"/></h1>

                <ul class='top-links'>
                    <sec:authorize url='/admMenu/create'>
                        <li>
                            <a href='${context}/admMenu/create' class='btn btn-block btn-primary btn-xs'>
                                <i class='fa fa-plus-circle'></i>
                                <spring:message code='default.new.label' arguments="${entityName}"/>
                            </a>
                        </li>
                    </sec:authorize>
                    <sec:authorize url='/admMenu/index'>
                        <li>
                            <a href='${context}/admMenu/index' class='btn btn-block btn-info btn-xs'>
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

                                <c:if test='${admMenu.code!=null && !admMenu.code.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='code' text='Code'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='code'>
                                            <c:out value='${admMenu.code}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenu.fullName!=null && !admMenu.fullName.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='fullName' text='Full Name'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='fullName'>
                                            <c:out value='${admMenu.fullName}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenu.fullNameNative!=null && !admMenu.fullNameNative.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='fullNameNative' text='Full Name Native'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='fullNameNative'>
                                            <c:out value='${admMenu.fullNameNative}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenu.isActive!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='isActive' text='Is Active'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='isActive'>
                                            <c:if test='${admMenu.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admMenu.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenu.slNo!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='slNo' text='Sl No'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='slNo'>
                                            <c:out value='${admMenu.slNo}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenu.description!=null && !admMenu.description.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='description' text='Description'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='description'>
                                            <c:out value='${admMenu.description}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenu.tooltip!=null && !admMenu.tooltip.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='tooltip' text='Tooltip'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='tooltip'>
                                            <c:out value='${admMenu.tooltip}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenu.displayIconClass!=null && !admMenu.displayIconClass.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='displayIconClass' text='Display Icon Class'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='displayIconClass'>
                                            <c:out value='${admMenu.displayIconClass}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenu.isExternal!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='isExternal' text='Is External'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='isExternal'>
                                            <c:if test='${admMenu.isExternal}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admMenu.isExternal}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenu.isOpenInNewTab!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='isOpenInNewTab' text='Is Open In New Tab'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='isOpenInNewTab'>
                                            <c:if test='${admMenu.isOpenInNewTab}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admMenu.isOpenInNewTab}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenu.admSubModule!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='admSubModule' text='Adm Sub Module'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='admSubModule'>
                                            <c:out value='${admMenu.admSubModule}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${admMenu.urlPath!=null && !admMenu.urlPath.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='urlPath' text='Url Path'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='urlPath'>
                                            <c:out value='${admMenu.urlPath}'/>
                                        </span>
                                    </li>
                                </c:if>

                            </ol>
                            <div><jsp:include page='showAdmMenuItems.jsp' /></div>

                        </fieldset><!--.show-page-->
                    </div><!--.box-body-->

                    <div class='box-footer'>
                        <sec:authorize url='/admMenu/edit'>
                            <a href='${context}/admMenu/edit/${admMenu.id}' class='btn btn-primary'>
                                <i class='fa fa-edit'></i>
                                <spring:message code='default.button.edit.label'/>
                            </a> 
                        </sec:authorize>
                        <sec:authorize url='/admMenu/copy'>
                            <a href='${context}/admMenu/copy/${admMenu.id}' class='btn btn-warning'>
                                <i class='fa fa-clone'></i>
                                <spring:message code='default.button.copy.label'/>
                            </a>             
                        </sec:authorize>
                        <sec:authorize url='/admMenu/delete'>
                            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>                     
                            <a href='${context}/admMenu/delete/${admMenu.id}' class='btn btn-danger' onclick="return confirm('${confirmToDelete}');">
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