<%@ page contentType='text/html;charset=utf-8' language='java' %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="${processFullName}" text="${processFullName}"/></title>
</head>
<body>
<div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

    <section class="content-header"><!-- Content Header (Page header) -->
        <h1>${processFullName}</h1>
    </section>
    <!-- /.content-header -->

    <section class="content-messages">
        <jsp:include page="/views/layouts/_flashMessage.jsp"/>
    </section>
    <!-- /.flesh-message -->

    <section class="content"><!-- Main content -->
        <div class="box box-primary">
            <div class="box-body">
                <input name="processId" id="processId" type="hidden" value="${processId}"/>
                <jsp:include page='_dynamicProcess.jsp'/>
            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box box-primary -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<script type='text/javascript'>
    window.onload = getDynamicContent;
    //$(document).ready(getDynamicContent);
</script>
</body>
</html>
