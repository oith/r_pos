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
        <spring:message code="authUser.label" text='Auth User' var="entityName"/>
        <title><spring:message code="default.show.label" arguments="${entityName}"/></title>
    </head>
    <body>
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code="default.show.label" arguments="${entityName}"/></h1>

                <ul class='top-links'>
                    <sec:authorize url='/authUser/create'>
                        <li>
                            <a href='${context}/authUser/create' class='btn btn-block btn-primary btn-xs'>
                                <i class='fa fa-plus-circle'></i>
                                <spring:message code='default.new.label' arguments="${entityName}"/>
                            </a>
                        </li>
                    </sec:authorize>
                    <sec:authorize url='/authUser/index'>
                        <li>
                            <a href='${context}/authUser/index' class='btn btn-block btn-info btn-xs'>
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

                                <c:if test='${authUser.username!=null && !authUser.username.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='username' text='Username'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='username'>
                                            <c:out value='${authUser.username}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.displayName!=null && !authUser.displayName.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='displayName' text='Display Name'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='displayName'>
                                            <c:out value='${authUser.displayName}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.cellNo!=null && !authUser.cellNo.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='cellNo' text='Cell No'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='cellNo'>
                                            <c:out value='${authUser.cellNo}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.title!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='title' text='Title'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='title'>
                                            <c:out value='${authUser.title}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.fullName!=null && !authUser.fullName.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='fullName' text='Full Name'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='fullName'>
                                            <c:out value='${authUser.fullName}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.firstName!=null && !authUser.firstName.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='firstName' text='First Name'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='firstName'>
                                            <c:out value='${authUser.firstName}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.lastName!=null && !authUser.lastName.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='lastName' text='Last Name'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='lastName'>
                                            <c:out value='${authUser.lastName}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.gender!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='gender' text='Gender'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='gender'>
                                            <c:out value='${authUser.gender}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.dob!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='dob' text='Dob'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='dob'>
                                            <fmt:formatDate value='${authUser.dob}' type='date' pattern='dd/MM/yyyy'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.doj!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='doj' text='Doj'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='doj'>
                                            <fmt:formatDate value='${authUser.doj}' type='date' pattern='dd/MM/yyyy'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.email!=null && !authUser.email.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='email' text='Email'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='email'>
                                            <c:out value='${authUser.email}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.pic!=null && !authUser.pic.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='pic' text='Pic'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='pic'>
                                            <c:url var='pic' value='/authUser/getPhotoMedium/${authUser.pic}'/>
<img alt='${authUser.pic}' src='${pic}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.language!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='language' text='Language'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='language'>
                                            <c:out value='${authUser.language}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.locale!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='locale' text='Locale'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='locale'>
                                            <c:out value='${authUser.locale}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.country!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='country' text='Country'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='country'>
                                            <c:out value='${authUser.country}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.currency!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='currency' text='Currency'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='currency'>
                                            <c:out value='${authUser.currency}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.menuOrientation!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='menuOrientation' text='Menu Orientation'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='menuOrientation'>
                                            <c:out value='${authUser.menuOrientation}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.authGroups!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='authGroups' text='Auth Groups'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='authGroups'>
                                            <c:out value='${authUser.authGroups}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.enabled!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='enabled' text='Enabled'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='enabled'>
                                            <c:if test='${authUser.enabled}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authUser.enabled}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.accountNonExpired!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='accountNonExpired' text='Account Non Expired'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='accountNonExpired'>
                                            <c:if test='${authUser.accountNonExpired}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authUser.accountNonExpired}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.accountNonLocked!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='accountNonLocked' text='Account Non Locked'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='accountNonLocked'>
                                            <c:if test='${authUser.accountNonLocked}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authUser.accountNonLocked}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.credentialsNonExpired!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='credentialsNonExpired' text='Credentials Non Expired'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='credentialsNonExpired'>
                                            <c:if test='${authUser.credentialsNonExpired}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authUser.credentialsNonExpired}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.openInNewPage!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='openInNewPage' text='Open In New Page'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='openInNewPage'>
                                            <c:if test='${authUser.openInNewPage}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authUser.openInNewPage}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.params!=null && !authUser.params.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='params' text='Params'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='params'>
                                            <c:out value='${authUser.params}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.favorite!=null && !authUser.favorite.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='favorite' text='Favorite'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='favorite'>
                                            <c:out value='${authUser.favorite}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${authUser.department!=null && !authUser.department.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='department' text='Department'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='department'>
                                            <c:out value='${authUser.department}'/>
                                        </span>
                                    </li>
                                </c:if>

                            </ol>
                            <div><jsp:include page='showAuthUserAuthQuestions.jsp' /></div>
                            <div><jsp:include page='showAuthUserAuthRoles.jsp' /></div>
                            <div><jsp:include page='showAuthUserEnvKeys.jsp' /></div>

                        </fieldset><!--.show-page-->
                    </div><!--.box-body-->

                    <div class='box-footer'>
                        <sec:authorize url='/authUser/edit'>
                            <a href='${context}/authUser/edit/${authUser.id}' class='btn btn-primary'>
                                <i class='fa fa-edit'></i>
                                <spring:message code='default.button.edit.label'/>
                            </a> 
                        </sec:authorize>
                        <sec:authorize url='/authUser/copy'>
                            <a href='${context}/authUser/copy/${authUser.id}' class='btn btn-warning'>
                                <i class='fa fa-clone'></i>
                                <spring:message code='default.button.copy.label'/>
                            </a>             
                        </sec:authorize>
                        <sec:authorize url='/authUser/delete'>
                            <spring:message code='default.button.delete.confirm.message' text='Are you sure to delete?' var='confirmToDelete'/>                     
                            <a href='${context}/authUser/delete/${authUser.id}' class='btn btn-danger' onclick="return confirm('${confirmToDelete}');">
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