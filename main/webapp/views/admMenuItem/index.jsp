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
        <spring:message code="admMenuItem.label" text='Adm Menu Item' var="entityName"/>
        <title><spring:message code="default.list.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.list.label" arguments="${entityName}"/></h1>
                
                <ul class='top-links'>
                    <sec:authorize url='/admMenuItem/create'>
                        <li>
                            <a href='${context}/admMenuItem/create' class='btn btn-block btn-primary btn-xs'>
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
                                <th><spring:message code='isActive' text='Is Active'/></th>
                                <th><spring:message code='slNo' text='Sl No'/></th>
                                <th><spring:message code='description' text='Description'/></th>
                                <th><spring:message code='tooltip' text='Tooltip'/></th>
                                <th><spring:message code='displayIconClass' text='Display Icon Class'/></th>
                                <th><spring:message code='isExternal' text='Is External'/></th>
                                <th><spring:message code='isOpenInNewTab' text='Is Open In New Tab'/></th>
                                <th><spring:message code='admSubModule' text='Adm Sub Module'/></th>
                                <th><spring:message code='admMenu' text='Adm Menu'/></th>
                                <th><spring:message code='urlPath' text='Url Path'/></th>

                                <th><spring:message code='default.button.action.label' text='Action'/></th> 
                            </thead>
                            <tbody>
                                <c:if test='${not empty admMenuItems}'>
                                    <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>
                                    <c:forEach items='${admMenuItems}' var='admMenuItem'  varStatus='loopStatus'>
                                        <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                                            <td><c:out value='${admMenuItem.code}'/></td>
                                            <td><c:out value='${admMenuItem.fullName}'/></td>
                                            <td><c:out value='${admMenuItem.fullNameNative}'/></td>
                                            <td class='center'><c:if test='${admMenuItem.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admMenuItem.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                                            <td class='right'><c:out value='${admMenuItem.slNo}'/></td>
                                            <td><c:out value='${admMenuItem.description}'/></td>
                                            <td><c:out value='${admMenuItem.tooltip}'/></td>
                                            <td><c:out value='${admMenuItem.displayIconClass}'/></td>
                                            <td class='center'><c:if test='${admMenuItem.isExternal}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admMenuItem.isExternal}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                                            <td class='center'><c:if test='${admMenuItem.isOpenInNewTab}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admMenuItem.isOpenInNewTab}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                                            <td><c:out value='${admMenuItem.admSubModule}'/></td>
                                            <td><c:out value='${admMenuItem.admMenu!=null ? admMenuItem.admMenu :"N/A"}'/></td>
                                            <td><c:out value='${admMenuItem.urlPath}'/></td>

                                            <td class='center action'>
                                                <sec:authorize url='/admMenuItem/show'>
                                                    <a class='btn btn-info btn-xs' href='${context}/admMenuItem/show/${admMenuItem.id}'>
                                                        <i class='fa fa-info-circle'></i>
                                                        <spring:message code='default.button.show.label'/>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize url='/admMenuItem/edit'>
                                                    <a class='btn btn-primary btn-xs' href='${context}/admMenuItem/edit/${admMenuItem.id}'>
                                                        <i class='fa fa-edit'></i>
                                                        <spring:message code='default.button.edit.label'/>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize url='/admMenuItem/copy'>
                                                    <a class='btn btn-warning btn-xs' href='${context}/admMenuItem/copy/${admMenuItem.id}'>
                                                        <i class='fa fa-clone' aria-hidden='true'></i>
                                                        <spring:message code='default.button.copy.label'/>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize url='/admMenuItem/delete'>
                                                    <a class='btn btn-danger btn-xs' href='${context}/admMenuItem/delete/${admMenuItem.id}' onclick="return confirm('${confirmToDelete}');">
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