<%@ page contentType='text/html;charset=utf-8' language='java' %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id='searchParameterHeader' class='col-xs-12 col-sm-12 col-md-12 col-lg-12'>
</div>
<div id='searchContent'></div>
<div id='searchButton' class="col-xs-12 col-sm-12 col-md-12 col-lg-12"></div>

<div id="fixedParameterHeader" class='col-xs-12 col-sm-12 col-md-12 col-lg-12'>
</div>

<div id='fixedParam'></div>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <form id='oith' name='oith' action='executeProcess'>
        <div class='btn-group' id='buttonContent'></div>
    </form>
</div>

<div id='qparams' style='display: none'></div>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div id='outputMsg'></div>
</div>

<div id='tableContent'></div>
<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div id='totalRecordDiv'></div>
    <div id='errMsg'></div>
</div>
<div id='LoadingImageLoadProcess' style='display: none;'>
    <img src='${pageContext.request.contextPath}/resources/images/spinner.gif' alt='Loading'>
</div>

<script type='text/javascript'>
    $(document).on('click', '#checkAll', function () {
        if ($(this).is(':checked')) {
            $('.checkBoxTouchAll').each(function () {
                $(this).prop('checked', true);
            });
        } else {
            $('.checkBoxTouchAll').each(function () {
                $(this).prop('checked', false);
            });
        }
    });

    function hideAjaxLoadingImageProc() {
        $('#LoadingImageSrch').hide();
        $('#LoadingImageLoadProcess').hide();
        $('#LoadingImageExecuteProcess').hide();
    }

    function getDynamicContent() {

        hideAjaxLoadingImageProc();
        $('#LoadingImageLoadProcess').show();
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
        $('#searchParameterHeader').empty();
        $('#tableContent').empty();
        $('#totalRecordDiv').empty();

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/processCenter/getDynamicContent',
            data: {
                processId: $('#processId').val()
            },
            async: false,
            success: function (d) {

                hideAjaxLoadingImageProc();

                if (d.searchContent !== null && d.searchContent !== '') {
                    $('#searchParameterHeader').append("<div class='form-group'><h4><spring:message code='searchParameter' text='Search Parameter'/></h4></div>");
                    $('#searchContent').append(d.searchContent.toString());
                    $('#searchButton').append("<button onclick='getDynamicTable()' class='btn btn-info' title='Press to Search' type='button' name='search' id='search' ><i class = 'glyphicon glyphicon-search'></i><spring:message code='search.form.submit.label' text='Search'/></button>");
                }

                if (d.fixedParam !== null && d.fixedParam !== '') {
                    $('#fixedParameterHeader').append("<div class='form - group'><h4><spring:message code='fixedParameter' text='Fixed Parameter'/></h4></div>");
                    $('#fixedParam').append(d.fixedParam.toString());
                }

                if (d.processButton !== null && d.processButton !== '') {
                    $('#buttonContent').append(d.processButton.toString());
                }
                //todo qu param need to handle same as for report, dynamictFormProcess need to optimize
                if (d.qparams !== null && d.qparams !== '') {
                    $('#qparams').append(d.qparams.toString());
                }

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
                alert('No Process Is Configured DP: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    }

    function getDynamicTable() {
        hideAjaxLoadingImageProc();
        $('#LoadingImageSrch').show();
        var my_cars = {};
        $('#searchContent').find(':required').each(function () {
            if ($(this).val() === '') {
                $(this).focus();
                hideAjaxLoadingImageProc();
                return;
            }
        });
        $('#searchContent').find(':input').each(function () {
            my_cars[$(this).attr('id')] = $('#' + $(this).attr('id')).val();
        });
        var strKeyVal = JSON.stringify(my_cars);
        $('#tableContent').empty();
        $('#totalRecordDiv').empty();
        $('#outputMsg').empty();
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/processCenter/search',
            data: {
                strKeyVal: strKeyVal,
                processId: $('#processId').val()
            },
            success: function (d) {
                hideAjaxLoadingImageProc();
                $('#tableContent').append(d.DATA_TABLE.toString());
                $('#totalRecordDiv').append('<b>Total Records Found: ' + d.TOTAL_RECORD.toString() + '</b>');
                $('#tableContent').attr('class', 'fieldcontain');
                $('#totalRecordDiv').attr('class', 'fieldcontain');
                $('#search').prop('disabled', false);
            },
            error: function (err) {
                alert('No Process: ' + err);
                hideAjaxLoadingImageProc();
                $('#search').prop('disabled', false);
            }
        });
    }

    function executeProcess(btnId) {
        hideAjaxLoadingImageProc();
        $('#' + btnId).prop('disabled', true);
        var myParam = {};
        var isReturn = false;
        $('#errMsg').empty();
        if ($('.checkBoxTouchAll').length !== 0 && $('.checkBoxTouchAll:checked').length === 0) {
            alert('Select at least one.');
            $('#' + btnId).prop('disabled', false);
            return;
        }
        var r = confirm('Are you really want to execute the process');
        if (r === true) {
            x = 'You pressed OK!';
            $('#LoadingImageExecuteProcess').show();
        } else {
            return;
        }

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

        $('#fixedParam').find(':input').each(function () {
            myParam[$(this).attr('id')] = $('#' + $(this).attr('id')).val();
        });
        var porcTitleMApxLOC = {};
        porcTitleMApxLOC['PROC_BTN_ID'] = btnId;
        porcTitleMApxLOC['FIXED_PARAM_VAL'] = myParam;
        porcTitleMApxLOC['QU_PARAM_VAL'] = $('#qparams').text();
        var spltqparams = $('#qparams').text(); //'ID,OITH_ID,MAC_REMARKSX';

        var outSpltqparams = spltqparams.split(',');
        var arrKeyVal = {};
        var i = 1;
        $('.checkBoxTouchAll').each(function () {
            if (this.checked) {
                var smy_cars = {};
                for (var j = 0; j < outSpltqparams.length; j++) {
                    smy_cars[outSpltqparams[j]] = $('#' + outSpltqparams[j] + '' + i).val();
                }
                arrKeyVal[i] = smy_cars;
            }
            i++;
        });
        var strKeyVal = JSON.stringify(arrKeyVal);
        var porcTitleMApx = JSON.stringify(porcTitleMApxLOC);
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/processCenter/executeProcess',
            data: {
                strKeyVal: strKeyVal,
                porcTitleMApx: porcTitleMApx,
                processId: $('#processId').val()
            },
            success: function (d) {

                hideAjaxLoadingImageProc();
                $('#outputMsg').empty();
                $('#outputMsg').append('Process executed on ' + new Date() + '<br/>');
                $('#' + btnId).prop('disabled', false);
                $('#outputMsg').append('No of Successful Process:  ' + d.countsPass.toString() + '<br/>');
                $('#outputMsg').append('No of Failed Process:  ' + d.countsFail.toString() + '<br/>');
                $('#outputMsg').append(d.procOutLink.toString());
                $('#errMsg').append(d.errMsg.toString());
                $('#outputMsg').attr('class', 'fieldcontain');
                $('#errMsg').attr('class', 'fieldcontain');
            },
            error: function (dd) {
                alert('error' + dd);
                hideAjaxLoadingImageProc();
                $('#' + btnId).prop('disabled', false);
            }
        });
    }
</script>

