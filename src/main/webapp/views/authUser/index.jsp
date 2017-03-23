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
        <spring:message code="authUser.label" text='Auth User' var="entityName"/>
        <title><spring:message code="default.list.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.list.label" arguments="${entityName}"/></h1>
                
                <ul class='top-links'>
                    <sec:authorize url='/authUser/create'>
                        <li>
                            <a href='${context}/authUser/create' class='btn btn-block btn-primary btn-xs'>
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
                                <th><spring:message code='username' text='Username'/></th>
                                <th><spring:message code='displayName' text='Display Name'/></th>
                                <th><spring:message code='cellNo' text='Cell No'/></th>
                                <th><spring:message code='title' text='Title'/></th>
                                <th><spring:message code='fullName' text='Full Name'/></th>
                                <th><spring:message code='firstName' text='First Name'/></th>
                                <th><spring:message code='lastName' text='Last Name'/></th>
                                <th><spring:message code='gender' text='Gender'/></th>
                                <th><spring:message code='dob' text='Dob'/></th>
                                <th><spring:message code='doj' text='Doj'/></th>
                                <th><spring:message code='email' text='Email'/></th>
                                <th><spring:message code='pic' text='Pic'/></th>
                                <th><spring:message code='language' text='Language'/></th>
                                <th><spring:message code='locale' text='Locale'/></th>
                                <th><spring:message code='country' text='Country'/></th>
                                <th><spring:message code='currency' text='Currency'/></th>
                                <th><spring:message code='menuOrientation' text='Menu Orientation'/></th>
                                <th><spring:message code='authGroups' text='Auth Groups'/></th>
                                <th><spring:message code='enabled' text='Enabled'/></th>
                                <th><spring:message code='accountNonExpired' text='Account Non Expired'/></th>
                                <th><spring:message code='accountNonLocked' text='Account Non Locked'/></th>
                                <th><spring:message code='credentialsNonExpired' text='Credentials Non Expired'/></th>
                                <th><spring:message code='openInNewPage' text='Open In New Page'/></th>
                                <th><spring:message code='params' text='Params'/></th>
                                <th><spring:message code='favorite' text='Favorite'/></th>
                                <th><spring:message code='department' text='Department'/></th>

                                <th><spring:message code='default.button.action.label' text='Action'/></th> 
                            </thead>
                            <tbody>
                                <c:if test='${not empty authUsers}'>
                                    <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>
                                    <c:forEach items='${authUsers}' var='authUser'  varStatus='loopStatus'>
                                        <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                                            <td><c:out value='${authUser.username}'/></td>
                                            <td class='center'><c:out value='${authUser.displayName}'/></td>
                                            <td><c:out value='${authUser.cellNo}'/></td>
                                            <td class='center'><c:out value='${authUser.title}'/></td>
                                            <td><c:out value='${authUser.fullName}'/></td>
                                            <td><c:out value='${authUser.firstName}'/></td>
                                            <td><c:out value='${authUser.lastName}'/></td>
                                            <td class='center'><c:out value='${authUser.gender}'/></td>
                                            <td class='center'><fmt:formatDate value='${authUser.dob}' type='date' pattern='dd/MM/yyyy'/></td>
                                            <td class='center'><fmt:formatDate value='${authUser.doj}' type='date' pattern='dd/MM/yyyy'/></td>
                                            <td><c:out value='${authUser.email}'/></td>
                                            <td><c:url var='pic' value='/authUser/getPhotoSmall/${authUser.pic}'/>
<img alt='${authUser.pic}' src='${pic}'/></td>
                                            <td class='center'><c:out value='${authUser.language}'/></td>
                                            <td class='center'><c:out value='${authUser.locale}'/></td>
                                            <td class='center'><c:out value='${authUser.country}'/></td>
                                            <td class='center'><c:out value='${authUser.currency}'/></td>
                                            <td class='center'><c:out value='${authUser.menuOrientation}'/></td>
                                            <td><c:out value='${authUser.authGroups}'/></td>
                                            <td class='center'><c:if test='${authUser.enabled}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authUser.enabled}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                                            <td class='center'><c:if test='${authUser.accountNonExpired}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authUser.accountNonExpired}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                                            <td class='center'><c:if test='${authUser.accountNonLocked}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authUser.accountNonLocked}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                                            <td class='center'><c:if test='${authUser.credentialsNonExpired}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authUser.credentialsNonExpired}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                                            <td class='center'><c:if test='${authUser.openInNewPage}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authUser.openInNewPage}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                                            <td><c:out value='${authUser.params}'/></td>
                                            <td><c:out value='${authUser.favorite}'/></td>
                                            <td><c:out value='${authUser.department}'/></td>

                                            <td class='center action'>
                                                <sec:authorize url='/authUser/show'>
                                                    <a class='btn btn-info btn-xs' href='${context}/authUser/show/${authUser.id}'>
                                                        <i class='fa fa-info-circle'></i>
                                                        <spring:message code='default.button.show.label'/>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize url='/authUser/edit'>
                                                    <a class='btn btn-primary btn-xs' href='${context}/authUser/edit/${authUser.id}'>
                                                        <i class='fa fa-edit'></i>
                                                        <spring:message code='default.button.edit.label'/>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize url='/authUser/copy'>
                                                    <a class='btn btn-warning btn-xs' href='${context}/authUser/copy/${authUser.id}'>
                                                        <i class='fa fa-clone' aria-hidden='true'></i>
                                                        <spring:message code='default.button.copy.label'/>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize url='/authUser/delete'>
                                                    <a class='btn btn-danger btn-xs' href='${context}/authUser/delete/${authUser.id}' onclick="return confirm('${confirmToDelete}');">
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