<%@ page contentType='text/html;charset=utf-8' language='java' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code='pos' text='Point of Sale'/></title>
</head>
<body>
<div class='content-wrapper'><!-- Content Wrapper. Contains page content -->

    <section class='content-header'><!-- Content Header (Page header) -->
        <h1><spring:message code='pos' text='Point of Sale'/></h1>
    </section>
    <!-- /.content-header -->

    <section class='content-messages'>
        <jsp:include page='/views/layouts/_flashMessage.jsp'/>
    </section>
    <!-- /.flesh-message -->

    <section class='content'><!-- Main content -->
        <div class='box box-primary'>
            <input name='sid' id='sid' type='hidden' value='${sid}'/>


            <div class='box-body'>
                <div class="col-lg-4">

                    <select name='uuid' onchange="barcodeActionSts()"
                            id='uuid'
                            class='form-control'>
                        <c:forEach items='${uuids}' var='sss' varStatus='loopStatus'>
                            <option value='${sss}'>${sss}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class='box-body'>

                <div class="col-lg-6">

                    <div class="col-lg-7">
                        <input class='form-control' name='barcode' id='barcode' type='text' maxlength='14'
                              />
                    </div>
                    <div class="cb col-lg-3">
                        <label>
                            <input id="isAdd" name="isAdd" type="checkbox" checked='checked'/>
                            <spring:message code='addMode' text='Add Mode'/>
                        </label>
                    </div>
                    <div class="col-lg-2">
                        <input class='form-control' name='quantity' id='quantity' type='text' value="1" maxlength='6'
                               />
                    </div>

                </div>
                <div class="col-lg-6">

                    <div class="col-lg-6">
                        <input name='mobile' id='mobile' type='text' class='form-control' maxlength='11'
                               />
                    </div>

                    <div class="col-lg-6">
                        <input name='customerName' id='customerName' type='text' class='form-control' disabled=""
                               maxlength='30'/>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box-body -->

            <div class='box-body'>

                <div class='box-body col-lg-8'>
                    <table class='table table-bordered table-striped table-hover table-condensed display nowrap th-center'>
                        <thead>
                        <th><spring:message code='slNo' text='Sl No'/></th>
                        <th><spring:message code='barcode' text='Barcode'/></th>
                        <th><spring:message code='product' text='Product'/></th>
                        <th><spring:message code='unitPrice' text='Unit Price'/></th>
                        <th><spring:message code='quantity' text='Quantity'/></th>
                        <th><spring:message code='lineTotal' text='Line Total'/></th>
                        </thead>
                        <tbody id='salesLines'>
                        </tbody>
                    </table>
                </div>

                <div class='box-body col-lg-4'>

                    <div class="form-group">
                        <div class="col-lg-8">
                            <p><spring:message code='netPayableAmount' text='Net Payable Amount'/></p>
                        </div>
                        <div class="col-lg-4 right">
                            <input class='form-control right disabled' disabled="" name='salesTotal' id='salesTotal'
                                   type='text' maxlength='10'/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-8">
                            <p><spring:message code='paidAmount' text='Paid Amount'/></p>
                        </div>
                        <div class="col-lg-4">
                            <input class="form-control right" name='paidAmount' id='paidAmount' type='text'
                                   maxlength='14' placeholder='0.0'/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-8">
                            <p><spring:message code='changeAmount' text='Change Amount'/></p>
                        </div>
                        <div class="col-lg-4 right">
                            <input name='changeAmount' id='changeAmount' type='text' class='form-control right disabled'
                                   disabled="" maxlength='10'/>
                        </div>
                    </div>
                    <!--<div class='box-body'>-->

                    <!--<div class='form-group'>-->
                    <!--<div class="col-lg-6">-->

                    <button class='btn btn-warning' onclick='posClear()'>
                        <i class='fa fa-remove' aria-hidden='true'></i>&nbsp;<spring:message code='clear' text='Clear'/>
                    </button>
                    <!--</div>-->

                    <!--<div class="col-lg-6">-->

                    <button class='btn btn-primary pull-right' onclick='posSaveAndPrint()'>
                        <i class='fa fa-print'></i>&nbsp;<spring:message code='saveAndPrint' text='Save and Print'/>
                    </button>
                    <!--</div>-->
                    <!--</div>-->

                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.box box-primary -->
<object id="pdfDoc" style="position:absolute;z-index:-1;" name="pdfDoc"
        classid="clsid:CA8A9780-280D-11CF-A24D-444553540000" height="300" width="500">
    <param name="SRC" value="file:\\\C:\\Users\\mbadiuzzaman\\Desktop\\aaaa.pdf"/>
</object>
<!--</div> /.content-wrapper -->

<script type='text/javascript'>

    function cls() {
        $('#salesLines').empty();

        if (!document.getElementById('isAdd').checked)
            document.getElementById('isAdd').checked = true;

        document.getElementById('mobile').value = '';
        document.getElementById('customerName').value = '';
        document.getElementById('customerName').disabled = 'true';
        document.getElementById('paidAmount').value = '';
        document.getElementById('salesTotal').value = '';
        document.getElementById('changeAmount').value = '';
        document.getElementById('quantity').value = 1;
        document.getElementById('barcode').value = '';
        document.getElementById('barcode').focus();
    }

    function posClear() {

        isClear = confirm('You really want to clear.');

        if (!isClear)
            return;

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/posBc/clear',
            data: {
                uuid: $('#uuid').val(),
                sid: $('#sid').val()
            },
            async: false,
            success: function (d) {
                if (d.status === 'ok')
                    cls();
                else
                    alert(d.message);
            }
            ,
            error: function (err) {
                alert('No Process Is Configured 712: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    }

    function posSaveAndPrint() {
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/posBc/saveAndPrint',
            data: {
                uuid: $('#uuid').val(),
                sid: $('#sid').val(),
                mobile: $('#mobile').val(),
                customerName: $('#customerName').val(),
                paidAmount: $('#paidAmount').val()
            },
            async: false,
            success: function (d) {
                if (d.status === 'ok') {
                    cls();
                    pdfDoc.printAll();
                } else
                    alert(d.message);
            }
            ,
            error: function (err) {
                alert('No Process Is Configured 187: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    }

    document.getElementById('mobile')
            .addEventListener('keyup', function (event) {
                event.preventDefault();
                if (event.keyCode === 13) {
                    $.ajax({
                        type: 'GET',
                        url: '${pageContext.request.contextPath}/posBc/getCustomer',
                        data: {
                            mobile: $('#mobile').val().trim()
                        },
                        async: false,
                        success: function (d) {
                            if (d === null || d === '') {
                                document.getElementById('customerName').value = '';
                                document.getElementById('customerName').disabled = '';
                                document.getElementById('customerName').focus();
                            } else {
                                document.getElementById('customerName').value = d.toString();
                                document.getElementById('customerName').disabled = 'true';
                            }
                        }
                        ,
                        error: function (err) {
                            alert('No Process Is Configured 523: ' + err);
                            hideAjaxLoadingImageProc();
                        }
                    });
                }
            });

    document.getElementById('paidAmount')
            .addEventListener('keyup', function (event) {
                event.preventDefault();
                if (event.keyCode === 13) {
                    chngAmnt = $('#paidAmount').val() - $('#salesTotal').val();
                    document.getElementById('changeAmount').value = chngAmnt;
                    document.getElementById('paidAmount').focus();
                }
            });

    document.getElementById('barcode')
            .addEventListener('keyup', function (event) {
                event.preventDefault();
                if (event.keyCode === 13) {
                    barcodeAction();
                }
            });

    window.onload = codeAddress;
    //$(document).ready(codeAddress);
    function barcodeActionSts() {
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/posBc/barcodeActionSts',
            data: {
                uuid: $('#uuid').val(),
                sid: $('#sid').val()
            },
            async: false,
            success: function (d) {
                //                        hideAjaxLoadingImageProc();

                if (d.status === 'ok') {
                    $('#salesLines').empty();

                    $('#salesLines').append(d.salesLines.toString());

                    document.getElementById('salesTotal').value = d.salesTotal;
                }
                if (!document.getElementById('isAdd').checked) {
                    document.getElementById('isAdd').checked = true;
                }
                document.getElementById('quantity').value = 1;
                document.getElementById('barcode').value = '';
                document.getElementById('barcode').focus();
            }
            ,
            error: function (err) {
                alert('No Process Is Configured 359: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    }

    function barcodeAction() {
        barcode = $('#barcode').val().trim();
        quantity = $('#quantity').val().trim();

        if (!barcode) {
            return;
        }

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/posBc/barcodeAction',
            data: {
                uuid: $('#uuid').val(),
                sid: $('#sid').val(),
                barcode: barcode,
                isAdd: document.getElementById('isAdd').checked,
                quantity: quantity
            },
            async: false,
            success: function (d) {
                //                        hideAjaxLoadingImageProc();
                if (d.status === 'ok') {
                    $('#salesLines').empty();
                    $('#salesLines').append(d.salesLines.toString());
                    document.getElementById('salesTotal').value = d.salesTotal;

                }
                if (!document.getElementById('isAdd').checked) {
                    document.getElementById('isAdd').checked = true;
                }
                document.getElementById('quantity').value = 1;
                document.getElementById('barcode').value = '';
                document.getElementById('barcode').focus();
            }
            ,
            error: function (err) {
                alert('No Process Is Configured 278: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    }
</script>
</body>
</html>