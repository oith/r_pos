<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding="UTF-8" %>
<%@ page session="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <title>
        <spring:message code="xmlFileUpload" text="Xml File Upload"/>
    </title>
</head>
<body>
<div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

    <section class="content-header"><!-- Content Header (Page header) -->
        <h1><spring:message code="xmlFileUpload" text="Xml File Upload"/></h1>
    </section>
    <!-- /.content-header -->

    <section class="content-messages">
        <%--<jsp:include page="../layouts/_flashMessage.jsp"/>--%>
    </section>
    <!-- /.flesh-message -->

    <section class="content"><!-- Main content -->
        <div id="create-zxLookup" class="box box-primary" role="main">
            <form:form action="${pageContext.request.contextPath}/xmlFileUpload" enctype="multipart/form-data"
                       method="POST">

                <div class="box-body">

                        ${result}

                    <div class="input-group">
                                <span class="input-group-btn">
                                    <span class="btn btn-primary btn-file">
                                        Browse &hellip;
                                        <input type="file" id="file" name="file" class="fl"/>
                                        <!--accept="application/vnd.ms-xml,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"-->
                                    </span>
                                </span>

                        <input type="text" class="form-control" readonly="readonly" id="file"
                               placeholder="Browse your file">

                    </div>

                </div>
                <!-- /.box-body -->
                <div class="box-footer">
                    <button type="submit" class="btn btn-danger">
                        <i class="fa fa-save"></i> <spring:message code="upload" text="Upload"/>
                    </button>
                </div>
                <!-- /.box-footer -->
            </form:form>
        </div>
        <!-- /.create-zxLookup -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
</body>
</html>

