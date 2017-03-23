<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code='default.button.edit.label' text='Edit'/>&nbsp;<spring:message code='authUser'
                                                                                               text='Auth User'/></title>
</head>
<body>
<div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

    <section class='content-header'><!-- Content Header (Page header) -->
        <h1><spring:message code='default.button.edit.label' text='Edit'/>&nbsp;<spring:message code='authUser'
                                                                                                text='Auth User'/></h1>
        <ul class='top-links'>
            <sec:authorize url='/authUser/create'>
                <li>
                    <a href='${pageContext.request.contextPath}/authUser/create'
                       class='btn btn-block btn-primary btn-xs'>
                        <i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label'
                                                                          text='Create'/>
                    </a>
                </li>
            </sec:authorize>
            <sec:authorize url='/authUser/index'>
                <li>
                    <a href='${pageContext.request.contextPath}/authUser/index' class='btn btn-block btn-info btn-xs'>
                        <i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/>
                    </a>
                </li>
            </sec:authorize>
        </ul>
    </section>
    <!-- /.content-header -->

    <section class='content-messages'>
        <%--<jsp:include page='../layouts/_flashMessage.jsp'/>--%>
    </section>
    <!-- /.flesh-message -->

    <section class='content'><!-- Main content -->
        <div class='box box-primary'>
            <form:form action='${pageContext.request.contextPath}/authUser/edit/${authUser.id}'
                       enctype='multipart/form-data' commandName='authUser' method='POST'>
                <div class='box-body'>
                    <jsp:include page='_form.jsp'/>
                </div>
                <div class='box-footer'>
                    <sec:authorize url='/authUser/show'>
                        <a href='${pageContext.request.contextPath}/authUser/show/${authUser.id}'
                           class='btn btn-primary'><i class='fa fa-info-circle'></i>&nbsp;<spring:message
                                code='default.button.show.label' text='Show'/></a>
                    </sec:authorize>
                    <sec:authorize url='/authUser/edit/${authUser.id}'>
                        <button type='submit' class='btn btn-warning'>
                            <i class='fa fa-save'></i>&nbsp;<spring:message code='default.button.update.label'
                                                                            text='Update'/>
                        </button>
                    </sec:authorize>
                </div>
            </form:form>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
</body>
</html>