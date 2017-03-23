<%@ page contentType='text/html;charset=utf-8' language='java' %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id='searchParameterHeader' class='col-xs-12 col-sm-12 col-md-12 col-lg-12'>
</div>
<div id='searchContent'></div>
<div id="fixedParameterHeader" class='col-xs-12 col-sm-12 col-md-12 col-lg-12'>
</div>
<div id='fixedParam'></div>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <form id='oith' name='oith' action='executeReport'>
        <div class='btn-group' id='buttonContent'></div>
    </form>
</div>

<div id='qparams' style='display: none'></div>
<div id='outputMsg'></div>
<div id='tableContent'></div>
<div id='totalRecordDiv'></div>
<div id='errMsg'></div>

<div id='LoadingImageLoadReport' style='display: none;'>
    <img src='${pageContext.request.contextPath}/resources/images/spinner.gif' alt='Loading'>
</div>

<script type='text/javascript'>

    function hideAjaxLoadingImageProc() {
        $('#LoadingImageSrch').hide();
        $('#LoadingImageLoadReport').hide();
        $('#LoadingImageExecuteReport').hide();
    }

    function getDynamicContent() {

        hideAjaxLoadingImageProc();
        $('#LoadingImageLoadReport').show();
        $('#buttonContent').empty();
        $('#errMsg').empty();
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
            url: '${pageContext.request.contextPath}/reportCenter/getDynamicContent',
            data: {
                reportId: $('#reportId').val()
            },
            async: false,
            success: function (d) {
                hideAjaxLoadingImageProc();

                if (d.searchContent !== null && d.searchContent !== '') {
                    $('#searchParameterHeader').append("<div class='form-group'><h4><spring:message code='reportParameter' text='Report Parameter'/></h4></div>");
                    $('#searchContent').append(d.searchContent.toString());
                    $('#searchButton').append("<button onclick='getDynamicTable()' class='btn btn-info' title='Press to Search' type='button' name='search' id='search' ><i class = 'glyphicon glyphicon-search'></i><spring:message code='search.form.submit.label' text='Search'/></button>");
                }

                if (d.fixedParam !== null && d.fixedParam !== '') {
                    $('#fixedParameterHeader').append("<div class='form - group'><h4><spring:message code='fixedParameter' text='Fixed Parameter'/></h4></div>");
                    $('#fixedParam').append(d.fixedParam.toString());
                }

                $('#buttonContent').append(d.reportButton.toString());
                $('#qparams').append(d.qparams.toString());

                var fd = "dd/mm/yyyy";
                $('.dtp-date').datepicker({
                    autoclose: true,
                    format: fd,
                    clearBtn: true,
                    keyboardNavigation: true,
                    todayHighlight: true,
                    weekStart: 0
                }).prop('placeholder', fd);
            },
            error: function (err) {
                alert('No Report Is Configured: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    }

    function executeReport(btnId) {
        hideAjaxLoadingImageProc();
        $('#' + btnId).prop('disabled', true);
        var isReturn = false;
        $('#errMsg').empty();
        if ($('.checkBoxTouchAll').length !== 0 && $('.checkBoxTouchAll:checked').length === 0) {
            alert('Select at least one.');
            $('#' + btnId).prop('disabled', false);
            return;
        }
//        var r = confirm('Are you really want to execute the report');
//        if (r === true) {
//            x = 'You pressed OK!';
//            $('#LoadingImageExecuteReport').show();
//        } else {
//            return;
//        }

        $('#fixedParam').find(':required').each(function () {
            if ($(this).val() === '') {
                alert('Enter required field');
                hideAjaxLoadingImageProc();
                isReturn = true;
                return false;
            }
        });

        if (isReturn) {
            return;
        }

        var reportParamsTemp = {};
        $('#searchContent').find(':input').each(function () {
            reportParamsTemp[$(this).attr('id')] = $('#' + $(this).attr('id')).val();
        });
        var reportParams = JSON.stringify(reportParamsTemp);

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/reportCenter/executeReport',
            data: {
                reportFormat: btnId,
                reportParams: reportParams,
                reportId: $('#reportId').val()
            },
            success: function (d) {

                hideAjaxLoadingImageProc();
                // $('#outputMsg').empty();
                // $('#outputMsg').append('Report executed on ' + new Date() + '<br/>');
                $('#' + btnId).prop('disabled', false);
                // $('#outputMsg').append('No of Successful Report:  ' + d.countsPass.toString() + '<br/>');
                //  $('#outputMsg').append('No of Failed Report:  ' + d.countsFail.toString() + '<br/>');
                //  $('#outputMsg').append(d.procOutLink.toString());
                //  $('#errMsg').append(d.errMsg.toString());
                //  $('#outputMsg').attr('class', 'fieldcontain');
                // $('#errMsg').attr('class', 'fieldcontain');
            },
            error: function (dd) {
                alert('error 7394:' + dd);
                hideAjaxLoadingImageProc();
                $('#' + btnId).prop('disabled', false);
            }
        });
    }
</script>

