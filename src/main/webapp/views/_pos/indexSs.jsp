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
            <input id='sid' type='hidden' value='${sid}'/>

            <button class='btn btn-danger' onclick='posClear()'>
                <i class='fa fa-remove' aria-hidden='true'></i>&nbsp;<spring:message code='clear' text='Clear'/>
            </button>

            <div class='box-body'>
                <div class="col-lg-4">

                    <select onchange="barcodeActionSts()"
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
                        <%--<input class='form-control' id='barcode' type='text' maxlength='14'--%>
                        <%--/>--%>

                        <div class="col-lg-3">
                            <p><spring:message code='acOne' text='AC One'/></p>
                        </div>
                        <div class="col-lg-9">
                            <select
                                    onchange="doLoad()"
                                    id='acOne'
                                    class='form-control'>
                                ${acOne}
                            </select>
                        </div>

                        <div class="col-lg-3">
                            <p><spring:message code='acTwo' text='AC Two'/></p>
                        </div>
                        <div class="col-lg-9">
                            <select
                                    onchange="doLoad()"
                                    id='acTwo'
                                    class='form-control'>
                                ${acTwo}
                            </select>
                        </div>
                        <div class="col-lg-3">
                            <p><spring:message code='acThree' text='AC Three'/></p>
                        </div>
                        <div class="col-lg-9">
                            <select
                                    onchange="doLoad()"
                                    id='acThree'
                                    class='form-control'>
                                ${acThree}
                            </select>
                        </div>

                        <div class="col-lg-3">
                            <p><spring:message code='acFour' text='AC Four'/></p>
                        </div>
                        <div class="col-lg-9">
                            <select
                                    onchange="doLoad()"
                                    id='acFour'
                                    class='form-control'>
                                ${acFour}
                            </select>
                        </div>

                        <div class="col-lg-3">
                            <p><spring:message code='acZero' text='AC Zero'/></p>
                        </div>
                        <div class="col-lg-9">
                            <select
                                    onchange="doLoadProductInfo()"
                                    id='acZero'
                                    class='form-control select-with-search'>
                                ${acZero}
                            </select>
                        </div>
                    </div>

                    <div class="cb col-lg-3">
                        <label>
                            <button class='btn btn-warning' onclick='posProductReset()'>
                                <i class='fa fa-remove' aria-hidden='true'></i>&nbsp;<spring:message code='reset' text='Reset'/>
                            </button>
                        </label>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="col-lg-6">
                                <p><spring:message code='unitPrice' text='Unit Price'/></p>
                            </div>
                            <div class="col-lg-6">
                                <input id='unitPriceMin' type='number' disabled="disabled"/>
                                <input id='unitPriceMax' type='number' disabled="disabled"/>

                                <input  id='unitPrice'
                                       type='number'
                                       onkeypress="return isNumeric(event,'unitPrice');" ondrop="return false;" onpaste="return false;"
                                       class='form-control' maxlength='7'/>

                                <span id="error-unitPrice" style="color: Red; display: none">* Input digits (0 - 9)</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-6">
                                <p><spring:message code='quantity' text='Quantity'/></p>
                            </div>
                            <div class="col-lg-6">
                                <input id='quantity' type='number'
                                       onkeypress="return isNumeric(event,'quantity');" ondrop="return false;" onpaste="return false;"
                                       class='form-control' maxlength='4'/>
                                <span id="error-quantity" style="color: Red; display: none">* Input digits (0 - 9) 9999 max</span>
                            </div>
                        </div>
                    </div>

                    <div class="cb col-lg-3">
                        <label>
                            <button class='btn btn-primary' onclick='posProductAdd()'>
                                <i class='fa fa-cart-plus' aria-hidden='true'></i>&nbsp;<spring:message code='add' text='Add'/>
                            </button>
                        </label>
                    </div>

                </div>
                <div class="cb col-lg-3">

                    <label>
                        <a href="${pageContext.request.contextPath}/index"
                           class="btn btn-primary">
                            <i class='fa fa-plus'></i>&nbsp;<spring:message code='addCustomer' text='Add Customer'/>
                        </a>
                    </label>

                    <label>
                        <button class='btn btn-info' onclick='reloadCustomer()'>
                            <i class='fa fa-refresh' aria-hidden='true'></i>&nbsp;<spring:message code='reload' text='Reload'/>
                        </button>
                    </label>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-lg-2">
                            <p><spring:message code='customer' text='Customer'/></p>
                        </div>
                        <div class="col-lg-10">
                            <select
                                    onchange="doLoadCustomerInfo()"
                                    id='customer'
                                    class='form-control'>
                                ${customers}
                            </select>
                        </div>
                    </div>
                    <%--<div class="col-lg-6">--%>
                    <%--<input id='mobile' type='text' class='form-control' maxlength='11'--%>
                    <%--/>--%>
                    <%--</div>--%>
                    <%--<div class="col-lg-6">--%>
                    <%--<input id='customerName' type='text' class='form-control' disabled=""--%>
                    <%--maxlength='30'/>--%>
                    <%--</div>--%>
                </div>

                <!-- /.box-body -->
            </div>
            <!-- /.box-body -->

            <div class='box-body'>

                <div class='box-body col-lg-8'>

                    <table id="myTable" class='table table-bordered table-striped table-hover table-condensed display nowrap th-center'>
                        <thead>
                        <th><spring:message code='load' text='Load'/></th>
                        <th><spring:message code='action' text='Action'/></th>
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
                            <input class='form-control right disabled' disabled="" id='salesTotal'
                                   type='text' maxlength='10'/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-8">
                            <p><spring:message code='paidAmount' text='Paid Amount'/></p>
                        </div>
                        <div class="col-lg-4">
                            <input class="form-control right" id='paidAmount' type='text'
                                   maxlength='14' placeholder='0.0'/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-8">
                            <p><spring:message code='changeAmount' text='Change Amount'/></p>
                        </div>
                        <div class="col-lg-4 right">
                            <input id='changeAmount' type='text' class='form-control right disabled'
                                   disabled="" maxlength='10'/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-8">
                            <button class='btn btn-primary pull-right' onclick='posSaveAndPrint()'>
                                <i class='fa fa-print'></i>&nbsp;<spring:message code='saveAndPrint' text='Save and Print'/>
                            </button>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.box box-primary -->
<object id="pdfDoc" style="position:absolute;z-index:-1;"
        classid="clsid:CA8A9780-280D-11CF-A24D-444553540000" height="300" width="500">
    <param name="SRC" value="file:\\\C:\\Users\\mbadiuzzaman\\Desktop\\aaaa.pdf"/>
</object>
<!--</div> /.content-wrapper -->

<script type='text/javascript'>

    $(document).ready(function () {
        doLoad();
    });

    var specialKeys = new Array();
    specialKeys.push(8);    //Backspace
    specialKeys.push(46);   //Dot

    function isNumeric(e, itemx) {
        // alert("hjhjhj"+itemx);
        var keyCode = e.which ? e.which : e.keyCode
        var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
        document.getElementById("error-" + itemx).style.display = ret ? "none" : "inline";
        return ret;
    }

    function posProductLoad(product) {

        if (!product) {
            return;
        }
        $('#acZero').val(product);

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/posSs/posProductLoad',
            data: {
                uuid: $('#uuid').val(),
                sid: $('#sid').val(),
                product: product
            },
            async: false,
            success: function (d) {
//                alert('yyyyyyyyyy: '+d);
                    document.getElementById('unitPriceMin').value = d.unitPriceMin;
                    document.getElementById('unitPriceMax').value = d.unitPriceMax;
                    document.getElementById('quantity').value = d.quantity;
                    document.getElementById('unitPrice').value = d.unitPrice;

            }
            ,
            error: function (err) {
                alert('No Process Is Configured 278: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    }
    function posProductOmit(product) {

        if (!product) {
            return;
        }

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/posSs/posProductAction',
            data: {
                uuid: $('#uuid').val(),
                sid: $('#sid').val(),
                isOmit: true,
                product: product,
                unitPrice: unitPrice,
                quantity: quantity
            },
            async: false,
            success: function (d) {
                //hideAjaxLoadingImageProc();
                if (d.status === 'ok') {
                    $('#salesLines').empty().append(d.salesLines.toString());
                    document.getElementById('salesTotal').value = d.salesTotal;
                }
            }
            ,
            error: function (err) {
                alert('No Process Is Configured 278: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    }

    function posProductAdd() {
        product = $('#acZero').val().trim();
        unitPrice = $('#unitPrice').val().trim();
        quantity = $('#quantity').val().trim();
        unitPriceMin = $('#unitPriceMin').val().trim();
        unitPriceMax = $('#unitPriceMax').val().trim();

        if (!product || !unitPrice || !quantity) {
            return;
        }

        if (unitPrice<= unitPriceMin || unitPrice>= unitPriceMax ) {
            alert("unit price is not in range.");
            return;
        }
        if (quantity<= 0) {
            alert("quantity cannot be zero or minus.");
            return;
        }

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/posSs/posProductAction',
            data: {
                uuid: $('#uuid').val(),
                sid: $('#sid').val(),
                isOmit: false,
                product: product,
                unitPrice: unitPrice,
                quantity: quantity
            },
            async: false,
            success: function (d) {
                //hideAjaxLoadingImageProc();
                if (d.status === 'ok') {
                    $('#salesLines').empty().append(d.salesLines.toString());
                    document.getElementById('salesTotal').value = d.salesTotal;
                }
                //reste
                //document.getElementById('barcode').value = '';
                //document.getElementById('barcode').focus();
            }
            ,
            error: function (err) {
                alert('No Process Is Configured 278: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    }

    function macCalc() {

    }

    function doLoadProductInfo() {

        product = $('#acZero').val().trim();

        if (!product) {
            return;
        }

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/posSs/posProductInfo',
            data: {
                product: product
            },
            async: false,
            success: function (d) {
                //alert(d);
                document.getElementById('unitPriceMin').value = d.unitPriceSalesMin;
                document.getElementById('unitPriceMax').value = d.unitPriceSalesMax;
                document.getElementById('unitPrice').value = d.unitPriceSalesStd;
                document.getElementById('quantity').value = 1;
            }
            ,
            error: function (err) {
                alert('No Process Is Configured 271: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    }
    function doLoadCustomerInfo() {

    }

    function cls() {
        $('#salesLines').empty();

//        document.getElementById('mobile').value = '';
//        document.getElementById('customerName').value = '';
//        document.getElementById('customerName').disabled = 'true';

        document.getElementById('customer').value = '30000';
        document.getElementById('unitPrice').value = '';
        document.getElementById('quantity').value = '';
        document.getElementById('paidAmount').value = '';
        document.getElementById('salesTotal').value = '';
        document.getElementById('changeAmount').value = '';
        posProductReset();
    }

    function posProductReset() {
        $('#acOne').val('');
        $('#acTwo').val('');
        $('#acThree').val('');
        $('#acFour').val('');
        $('#acZero').val('');
        $('#unitPrice').val('');
        $('#quantity').val('');
        $('#unitPriceMin').val('');
        $('#unitPriceMax').val('');
    }

    function reloadCustomer() {

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/posSs/customers',
            success: function (dat) {
                $("#customer").empty().html(dat);
            }
            ,
            error: function (err) {
                alert('No Process Is Configured 712: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    }

    function posClear() {

        isClear = confirm('You really want to clear.');

        if (!isClear)
            return;

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/posSs/clear',
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
            url: '${pageContext.request.contextPath}/posSs/saveAndPrint',
            data: {
                uuid: $('#uuid').val(),
                sid: $('#sid').val(),
                customer: $('#customer').val(),
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

    document.getElementById('paidAmount')
            .addEventListener('keyup', function (event) {
                event.preventDefault();
                if (event.keyCode === 13) {
                    chngAmnt = $('#paidAmount').val() - $('#salesTotal').val();
                    document.getElementById('changeAmount').value = chngAmnt;
                    document.getElementById('paidAmount').focus();
                }
            });

    function barcodeActionSts() {
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/posSs/barcodeActionSts',
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

    function doLoad() {
        doAllComboSetup('acOne');
        doAllComboSetup('acTwo');
        doAllComboSetup('acThree');
        doAllComboSetup('acFour');
        doAllComboSetup('acZero');
    }

    function doAllComboSetup(valx) {

        if ($("#" + valx).val() !== '' && $('#' + valx).val() !== null) {
            return;
        }

        var whersx = "";

        if ($('#acOne').val() !== '' && $('#acOne').val() !== null) whersx += " and m.acOne = " + $('#acOne').val() + "";
        if ($('#acTwo').val() !== '' && $('#acTwo').val() !== null) whersx += " and m.acTwo = " + $('#acTwo').val() + "";
        if ($('#acThree').val() !== '' && $('#acThree').val() !== null) whersx += " and m.acThree = " + $('#acThree').val() + "";
        if ($('#acFour').val() !== '' && $('#acFour').val() !== null) whersx += " and m.acFour = " + $('#acFour').val() + "";
//        if ($('#acZero').val() !== '' && $('#acZero').val()!==null) whersx += " and m.id = " + $('#ac4').val() + "";

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/posSs/getDbText',
            data: {
                colid: valx,
                wheres: whersx
            },
            success: function (dat) {
                $("#" + valx).empty().html(dat[valx]);
                $("#acZero").empty().html(dat.products);

            },
            error: function (error) {
                //alert("error: " + error + " hhhhhhhhhfff: " + valx + " where: " + whersx);
                //document.getElementById("loader").style.display = "none";
            }
        });
    }
</script>

<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</body>
</html>