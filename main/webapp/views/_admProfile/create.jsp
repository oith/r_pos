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
    <title><spring:message code='default.button.create.label' text='Create'/>&nbsp;<spring:message code='authUser'
                                                                                                   text='Auth User'/></title>
</head>
<body>
<div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

    <section class='content-header'><!-- Content Header (Page header) -->
        <h1><spring:message code='default.button.create.label' text='Create'/>&nbsp;<spring:message code='authUser'
                                                                                                    text='Auth User'/>&nbsp;
            <small class='disabled'>(${MENU_CODE})</small>
        </h1>
        <ul class='top-links'>
            <sec:authorize url='/authUser/index'>
                <li>
                    <a href='${pageContext.request.contextPath}/authUser/index' class='btn btn-block btn-vimeo btn-xs'>
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
        <div id='create-authUser' class='box box-primary' role='main'>
            <form:form action='${pageContext.request.contextPath}/authUser/create' enctype='multipart/form-data'
                       commandName='authUser' method='POST'>
                <div class='box-body'>
                    <jsp:include page='_form.jsp'/>
                </div>
                <!-- /.box-body -->
                <div class='box-footer'>
                    <button type='reset' class='btn btn-danger'>
                        <i class='fa fa-refresh'></i> <spring:message code='default.button.reset.label' text='Reset'/>
                    </button>
                    <sec:authorize url='/authUser/create'>
                        <button type='submit' class='btn btn-primary'>
                            <i class='fa fa-save'></i> <spring:message code='default.button.save.label' text='Save'/>
                        </button>
                    </sec:authorize>
                </div>
                <!-- /.box-footer -->
            </form:form>
        </div>
        <!-- /.create-authUser -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
</body>
</html>