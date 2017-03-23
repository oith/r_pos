<%@ page contentType='text/html;charset=utf-8' language='java' %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="process" text="Process"/></title>
</head>
<body>
<div class="content-wrapper"><!-- Content Wrapper. Contains page content -->

    <section class="content-header"><!-- Content Header (Page header) -->
        <h1><spring:message code="process" text="Process"/></h1>
    </section>
    <!-- /.content-header -->

    <section class="content-messages">
        <jsp:include page="/views/layouts/_flashMessage.jsp"/>
    </section>
    <!-- /.flesh-message -->

    <section class="content"><!-- Main content -->
        <div class="box box-primary">

            <div class="box-body">
                <div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'>
                    <div class='form-group'>
                        <label for='module'>
                            <span><spring:message code='module' text='Module'/></span>
                        </label>
                        <select name='module'
                                id='module'
                                onchange='getProcess()'
                                class='form-control'>
                            <option value='${null}'><spring:message code='default.combo.select.one.label'
                                                                    text='Select One'/></option>
                            <c:forEach items='${admModules}' var='sss' varStatus='loopStatus'>
                                <option value='${sss.id}'>${sss}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'>
                    <div class='form-group'>
                        <label class='required' for='processId'>
                            <span><spring:message code='process' text='Process'/></span>
                        </label>
                        <select name='processId'
                                id='processId'
                                required='required'
                                onchange='getDynamicContent()'
                                class='form-control'>
                            <option value='${null}'><spring:message code='default.combo.select.one.label'
                                                                    text='Select One'/></option>
                            <c:forEach items='${admProcesss}' var='sss' varStatus='loopStatus'>
                                <option value='${sss.id}'>${sss}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

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

    function hideAjaxLoadingImageProc() {
        $('#LoadingImageSrch').hide();
        $('#LoadingImageLoadProcess').hide();
        $('#LoadingImageExecuteProcess').hide();
    }

    function getProcess() {
        hideAjaxLoadingImageProc();
        $('#LoadingImageLoadProcess').show();
        $('#buttonContent').empty();
        $('#errMsg').empty();
        $('#error').empty();
        $('#fixedParam').empty();
        $('#fixedParameterHeader').empty();
        $('#outputMsg').empty();
        $('#qparams').empty();
        $('#searchButton').empty();
        $('#searchButtonContent').empty();
        $('#searchContent').empty();
        $('#searchParameterHeader').empty();
        $('#tableContent').empty();
        $('#totalRecordDiv').empty();

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/processCenter/getProcess',
            data: {
                module: $('#module').val()
            },
            async: false,
            success: function (data) {
                hideAjaxLoadingImageProc();
                $('#processId').empty();
                $('#processId').append(data);
            },
            error: function (err) {
                alert('err getProcess: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    }
</script>
</body>
</html>
